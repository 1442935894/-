package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import person.johnson.hardware.dao.SysCustomerMapper;
import person.johnson.hardware.dao.SysOrderItemMapper;
import person.johnson.hardware.dao.SysProductOrderMapper;
import person.johnson.hardware.dao.SysSupplierProductMapper;
import person.johnson.hardware.model.*;
import person.johnson.hardware.model.vo.OrderItemVo;
import person.johnson.hardware.model.vo.OrderVo;
import person.johnson.hardware.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/8
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    private SysProductOrderMapper orderMapper;
    @Autowired(required = false)
    private SysOrderItemMapper orderItemMapper;
    @Autowired(required = false)
    private SysCustomerMapper customerMapper;
    @Autowired(required = false)
    private SysSupplierProductMapper supplierProductMapper;

    @Override
    public List<OrderVo> getAll() {
        return orderMapper.getAll();
    }

    @Override
    public List<OrderVo> getBodyByName(String name,Long userId) {
        return orderMapper.getBodyByNameAndUid(name,userId);
    }

    @Override
    public List<OrderVo> getBodyByUid(Long id) {
        return orderMapper.getBodyByUid(id);
    }

    @Override
    public List<OrderVo> getOrderByCidAndUid(Long cid, Long uid) {
        return orderMapper.getOrdersByCidAndUid(cid,uid);
    }

    @Override
    public int insertOrder(OrderVo orderVo) throws Exception {
        SysProductOrder order = new SysProductOrder();
        int sum = 0;
        //设置订单号（时间戳）- 13位数的时间戳
        Long timeNew = System.currentTimeMillis();
        //注入对象属性
        BeanUtils.copyProperties(orderVo,order);
        order.setOrderNo(timeNew);
        //订单状态设置为0.进行中
        order.setOrderStatus((byte) 0);
        //订单支付状态设置为1.未支付
        order.setPayStatus((byte) 1);
        order.setCreateDatetime(new Date());
        //插入父订单
        sum = orderMapper.insert(order);
        List<OrderItemVo> items= orderVo.getItems();
        if(items.size()<1){
            throw new Exception("该订单没有上交的子订单！");
        }
        for(OrderItemVo item:items){
            SysOrderItem orderItem = new SysOrderItem();
            BeanUtils.copyProperties(item,orderItem);
            if(StringUtils.isEmpty(item.getSupplierId()) || StringUtils.isEmpty(item.getProductId())){
                throw new Exception("请确认子单中是否传入供应商参数和产品参数");
            }
            //获取父订单自增主键,添加子订单
            orderItem.setOrderId(order.getOrderId());
            orderItem.setCreateById(order.getCreateById());
            orderItem.setCreateDatetime(order.getCreateDatetime());
            orderItemMapper.insert(orderItem);
        }
        return sum;
    }

    @Override
    public int updateOrder(Long id, SysProductOrder order) throws Exception {
        SysProductOrder productOrder = new SysProductOrder();
        productOrder = orderMapper.selectByPrimaryKey(order.getOrderId());
        //当满足订单在进行中，且订单并未支付的状态，则可修改订单地址和联系电话
        if(productOrder.getOrderStatus()==0 && productOrder.getPayStatus()==1){
            return orderMapper.updatePhoneAddressById(order);
        }
        throw new Exception("订单状态处理异常");
    }

    @Override
    public int deleteOrder(Long id) throws Exception {
        SysProductOrder order = orderMapper.selectByPrimaryKey(id);
        //当满足订单在进行中，且订单并未支付的状态，则可取消订单
        if(order.getOrderStatus()==0 && order.getPayStatus()==1){
            //将订单设置为取消交易
            return orderMapper.changeOrderStatusById(id, (byte) 3);
        }
        throw new Exception("订单状态异常");
    }

    @Override
    public int updatePayStatus(Long id,SysProductOrder order) throws Exception {
        SysProductOrder oldOrder = orderMapper.selectByPrimaryKey(id);
        if(order.getPayAmount()==null || id!=order.getOrderId() || StringUtils.isEmpty(order.getOrderId())){
            throw new Exception("参数错误！");
        }
        //判断订单是否未未支付状态，且订单在进行中
        if(oldOrder.getOrderStatus() == 0 && oldOrder.getPayStatus() == 1){
            //更改支付状态:2.已支付 和 物流状态:0.采购中
            int a = orderMapper.changePayStatusById(id,(byte)2,order.getPayAmount(),new Date());
            int b = orderMapper.changeDeliverStatusById(id,(byte)0);
            if(a != b){
                throw new Exception("订单状态异常");
            }
            return a;
        }
        throw new Exception("订单状态异常");
    }

    //先减少库存数量，改变客单信息
    @Override
    public int updateSendOrder(Long id) throws Exception {
        OrderVo orderVo = orderMapper.getBodyByOid(id);
        List<OrderItemVo> items = orderVo.getItems();
        int sum=0;
        for(OrderItemVo item:items){
            Long sid=item.getSupplierId();
            Long pid=item.getProductId();
            SysSupplierProduct supplierProduct = supplierProductMapper.selectBySidAndPid(sid,pid);
            if(StringUtils.isEmpty(supplierProduct.getNumber())){
                throw new Exception("库存有误");
            }
            Long number = supplierProduct.getNumber();
            Long pnumber = item.getProductNumber();
            if(number<pnumber){
                throw new Exception("库存不足");
            }
            Long lastnumber = number-pnumber;
            supplierProduct.setNumber(lastnumber);
            sum= sum+supplierProductMapper.updateNumberByKey(supplierProduct);
        }

        if(sum!=items.size()){
            throw new Exception("库存量不足-详细表单无法扣除完毕");
        }
        return orderMapper.changeDeliverStatusById(id,(byte)1);
    }


    @Override
    public int updateRecevieOrder(Long id) throws Exception {
        SysProductOrder order = orderMapper.selectByPrimaryKey(id);
        //支付状态：2.已支付  订单状态：0.进行中  物流状态：1.送货中
        if(order.getPayStatus()==2 && order.getOrderStatus()==0 && order.getDeliverStatus()==1){
            //设置物流状态:2.已收货,订单状态:1.已结算
            int a = orderMapper.changeDeliverStatusById(id,(byte)2);
            int b = orderMapper.changeBuyerFinishTimeById(id,(byte)1,new Date());
            if(a != b){
                throw new Exception("订单状态异常");
            }
            //假设订单已到货，则判断该用户的所有订单量，如果订单量的总价达到多少，则对客户进行升级
//            Long customerId = order.getBuyerId();
//            SysCustomer customer=customerMapper.selectByPrimaryKey(customerId);
//            Byte level = customer.getVipLevel();
//            List<SysProductOrder> productOrders = new ArrayList<SysProductOrder>();
//            productOrders = orderMapper.selectByCustomerId(customerId);
//            if(){
//
//            }
            return a;
        }
        throw new Exception("订单状态异常");

    }

    @Override
    public int finishOrderRemark(Long id, SysProductOrder order) throws Exception {
        SysProductOrder checkorder = orderMapper.selectByPrimaryKey(id);
        if(id!=order.getOrderId()||StringUtils.isEmpty(order.getOrderId())||StringUtils.isEmpty(order.getRemark())){
            throw new Exception("参数错误");
        }
        //支付状态：2.已支付 物流状态：2.已收货 订单状态：1.已结算
        if(checkorder.getDeliverStatus()==2 && checkorder.getPayStatus()==2 && checkorder.getOrderStatus()==1){
            int a = orderMapper.changeOrderStatusById(id,(byte)2);
            int b = orderMapper.updateRemarkById(id,order.getRemark(),new Date());
            if(a != b){
                throw new Exception("订单状态异常");
            }
            return a ;
        }
        throw new Exception("订单状态异常");
    }

    @Override
    public int warnOrder(Long id) throws Exception {
        SysProductOrder oldOrder = orderMapper.selectByPrimaryKey(id);
        int sum = 0;
        //假设订单处于进行中，且支付状态为已支付
        if(oldOrder.getOrderStatus() == 0 && oldOrder.getPayStatus() == 2){
            sum = orderMapper.updateWarnStatus(id,(byte)1);
            return sum;
        }else{
            throw new Exception("订单状态异常");
        }
    }

    @Override
    public List<OrderVo> getWarnOrder() {
        return orderMapper.getWarnOrders();
    }

    @Override
    public List<OrderVo> getBodyByDate(String startDate, String endDate,Long id) {
        return orderMapper.getBodyByDate(startDate,endDate,id);
    }

}
