package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import person.johnson.hardware.dao.SysProductOrderMapper;
import person.johnson.hardware.dao.SysPurchaseOrderMapper;
import person.johnson.hardware.dao.SysSupplierProductMapper;
import person.johnson.hardware.dao.sql.ProductMapper;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductOrder;
import person.johnson.hardware.model.SysPurchaseOrder;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.vo.PurchaseVo;
import person.johnson.hardware.service.PurchaseService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired(required = false)
    private SysPurchaseOrderMapper purchaseOrderMapper;
    @Autowired(required = false)
    private SysSupplierProductMapper supplierProductMapper;
    @Autowired(required = false)
    private SysProductOrderMapper productOrderMapper;

    @Resource
    TokenUtil tokenUtil;
    Long userId = null;

    @Override
    public List<PurchaseVo> getAll() {
        return purchaseOrderMapper.getAll();
    }

    @Override
    public List<PurchaseVo> getBodyById(Long id) {
        return purchaseOrderMapper.getBodyByUid(id);
    }

    @Override
    public int insertPurchaseOrder(List<SysPurchaseOrder> sysPurchaseOrders) throws Exception {
        //设置订单号（时间戳）- 13位数的时间戳
        Long timeNew = System.currentTimeMillis();
        userId = tokenUtil.getUserIdByToken();
        Long productCorderId = null;
        int sum = 0;
        for(SysPurchaseOrder order:sysPurchaseOrders){
            if(!StringUtils.isEmpty(order.getOrderId())){
                productCorderId = order.getOrderId();
            }
            order.setPurchaseNo(timeNew);
            order.setCreateById(userId);
            order.setCreateDatetime(new Date());
            //添加订单-订单状态转变为0.已下单
            order.setOrderStatus((byte)0);
            sum = sum + purchaseOrderMapper.insert(order);
        }
        if(sum != sysPurchaseOrders.size()){
            throw new Exception("添加订单数量有误");
        }else{
            //将客单状态设置为已采购
            if(productCorderId != null){
                productOrderMapper.updateWarnStatus(productCorderId,(byte)0);
            }
            return sum;
        }
    }

    //修改购买库存量
    @Override
    public int updateNumber(Long id, SysPurchaseOrder order) throws Exception {

        SysPurchaseOrder purchase = purchaseOrderMapper.selectByPrimaryKey(id);
        if(!StringUtils.isEmpty(purchase.getPurchaseId()) && purchase.getOrderStatus()==0){
            order.setUpdateDatetime(new Date());
            BigDecimal costPrice = purchase.getProductCostPrice();
            BigDecimal number = new BigDecimal(order.getProductNumber());
            BigDecimal sum= costPrice.multiply(number);
            order.setProductTotalCost(sum);
            return purchaseOrderMapper.updateNumberById(order);
        }
        if(id!=order.getPurchaseId()||StringUtils.isEmpty(order.getPurchaseId()) || order.getProductNumber()<1){
            throw new Exception("参数出错！");
        }
        throw new Exception("订单状态异常！");
    }

    //取消订单
    @Override
    public int deletePurchase(Long id, SysPurchaseOrder order) throws Exception {
        SysPurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(id);
        userId = tokenUtil.getUserIdByToken();
        //获取同一客单的id
        //订单状态在已下单-未采购中
        if(purchaseOrder.getOrderStatus()==0){
            //将来自同一客单的采购单全部取消，客单的警告状态恢复到1
            if(!StringUtils.isEmpty(purchaseOrder.getOrderId())){
                Long corderId = purchaseOrder.getOrderId();
                productOrderMapper.updateWarnStatus(corderId,(byte)1);
                SysPurchaseOrder new_order = new SysPurchaseOrder();
                new_order.setOrderStatus((byte)3);
                new_order.setOrderId(corderId);
                new_order.setUpdateById(userId);
                new_order.setUpdateDatetime(new Date());
                return purchaseOrderMapper.updateStatusByOrderId(new_order);
            }else{
                purchaseOrder.setOrderStatus((byte)3);
                purchaseOrder.setUpdateById(userId);
                purchaseOrder.setUpdateDatetime(new Date());
                return purchaseOrderMapper.updateStatusById(purchaseOrder);
            }
        }
        if(id!=order.getPurchaseId()||StringUtils.isEmpty(order.getPurchaseId())){
            throw new Exception("参数出错！");
        }
        throw new Exception("订单状态异常！");
    }

    //确认采购
    @Override
    public int updateOrderStatus(Long id, SysPurchaseOrder order) throws Exception {
        SysPurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(id);
        //订单状态在已下单-未采购中
        if(purchaseOrder.getOrderStatus()==0){
            purchaseOrder.setOrderStatus((byte)1);
            purchaseOrder.setUpdateDatetime(new Date());
            return purchaseOrderMapper.updateStatusById(purchaseOrder);
        }
        if(id!=order.getPurchaseId()||StringUtils.isEmpty(order.getPurchaseId())){
            throw new Exception("参数出错！");
        }
        throw new Exception("订单状态异常！");
    }

    //确认订单完成-产品的库存数量添加
    @Override
    public int finishPurchaseOrder(Long id, SysPurchaseOrder order) throws Exception {
        SysPurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(id);
        if(purchaseOrder.getOrderStatus()==1){
            purchaseOrder.setOrderStatus((byte)2);
            purchaseOrder.setUpdateById(order.getUpdateById());
            purchaseOrder.setUpdateDatetime(new Date());
            //修改订单状态
            purchaseOrderMapper.updateStatusById(purchaseOrder);
            Long sid=purchaseOrder.getSupplierId();
            Long pid=purchaseOrder.getProductId();
            //添加库存量
            SysSupplierProduct supplierProduct=supplierProductMapper.selectBySidAndPid(sid,pid);
            Long number = supplierProduct.getNumber();
            Long sum = purchaseOrder.getProductNumber() + number;
            supplierProduct.setNumber(sum);
            supplierProduct.setUpdateById(order.getUpdateById());
            supplierProduct.setUpdateDatetime(new Date());
            return supplierProductMapper.updateNumberByKey(supplierProduct);
        }
        if(id!=order.getPurchaseId()||StringUtils.isEmpty(order.getPurchaseId())){
            throw new Exception("参数出错！");
        }
        throw new Exception("订单状态异常！");
    }
}
