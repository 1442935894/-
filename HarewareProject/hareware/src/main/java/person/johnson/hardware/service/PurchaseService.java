package person.johnson.hardware.service;

import person.johnson.hardware.model.SysProductOrder;
import person.johnson.hardware.model.SysPurchaseOrder;
import person.johnson.hardware.model.vo.PurchaseVo;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/10
 */
public interface PurchaseService {
    //查询采购单列表
    List<PurchaseVo> getAll();
    //根据跟单员id查询采购单信息
    List<PurchaseVo> getBodyById(Long id);
    //添加采购单(可以是单独购买、也可以是根据订单情况购买)
    int insertPurchaseOrder(List<SysPurchaseOrder> sysPurchaseOrders) throws Exception;
    //修改采购单信息（修改购买量）
    int updateNumber(Long id,SysPurchaseOrder order) throws Exception;
    //取消采购单
    int deletePurchase(Long id, SysPurchaseOrder order) throws Exception;
    //确认采购
    int updateOrderStatus(Long id, SysPurchaseOrder order) throws Exception;
    //确认收货-确认订单完成-产品的库存数量添加
    int finishPurchaseOrder(Long id,SysPurchaseOrder purchaseOrder) throws Exception;
}
