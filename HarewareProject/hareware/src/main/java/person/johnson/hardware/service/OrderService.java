package person.johnson.hardware.service;

import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductOrder;
import person.johnson.hardware.model.vo.OrderVo;

import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
public interface OrderService {
    //订单列表展示
    List<OrderVo> getAll();
    //模糊查询名字，查询客户订单/销售员订单
    List<OrderVo> getBodyByName(String name,Long userId);
    //根据销售员id搜索客户订单
    List<OrderVo> getBodyByUid(Long id);
    //根据客人id及当前系统用户id查询客户订单
    List<OrderVo> getOrderByCidAndUid(Long cid,Long uid);
    //添加订单
    int insertOrder(OrderVo orderVo) throws Exception;
    //修改订单地址（未支付前）
    int updateOrder(Long id,SysProductOrder order) throws Exception;
    //取消订单
    int deleteOrder(Long id) throws Exception;
    //支付订单
    int updatePayStatus(Long id,SysProductOrder order) throws Exception;
    //商品发货
    int updateSendOrder(Long id) throws Exception;
    //订单确认收货
    int updateRecevieOrder(Long id) throws Exception;
    //订单回访
    int finishOrderRemark(Long id, SysProductOrder order) throws Exception;
    //订单库存提醒
    int warnOrder(Long id)throws Exception;
    //查询处于库存提示的订单
    List<OrderVo> getWarnOrder();
    //根据起始时间或id查询客户订单信息
    List<OrderVo> getBodyByDate(String startDate,String endDate,Long id);

}
