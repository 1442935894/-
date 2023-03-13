package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductOrder;
import person.johnson.hardware.model.vo.OrderVo;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/7
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;

    List<OrderVo> orders = new ArrayList<>();

    //订单查询
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        orders = orderServiceImpl.getAll();
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orders);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //模糊查询订单信息
    @GetMapping("/list/{name}")
    public RestResult getBodyByName(@PathVariable("name")String name,
                                    @RequestParam("pageNum")int pageNum,
                                    @RequestParam("pageSize")int pageSize){
        userId = tokenUtil.getUserIdByToken();
        PageHelper.startPage(pageNum,pageSize);
        orders = orderServiceImpl.getBodyByName(name,userId);
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orders);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //通过销售员ID查询订单信息
    @GetMapping("/list/user")
    public RestResult getBodyByUId(@PathVariable("id")Long  id,
                                   @RequestParam("pageNum")int pageNum,
                                   @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        orders = orderServiceImpl.getBodyByUid(id);
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orders);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }


    //通过系统用户ID查询订单信息
    @GetMapping("/list/customer/user/{cid}")
    public RestResult getOrderByCidAndUid(@PathVariable("cid")Long  cid,
                                          @RequestParam("pageNum")int pageNum,
                                          @RequestParam("pageSize")int pageSize){
        userId = tokenUtil.getUserIdByToken();
        PageHelper.startPage(pageNum,pageSize);
        orders = orderServiceImpl.getOrderByCidAndUid(cid,userId);
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orders);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //添加订单
    @PostMapping("")
    public RestResult insertOrder(@RequestBody OrderVo orderVo) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            orderVo.setCreateById(userId);
            orderVo.setCreateDatetime(new Date());
            return RestResult.success(orderServiceImpl.insertOrder(orderVo));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //修改订单
    @PutMapping("/{id}")
    public RestResult updateOrder(@PathVariable("id")Long id,@RequestBody SysProductOrder order){
        try {
            int flag = 0;
            flag = orderServiceImpl.updateOrder(id,order);
            return RestResult.success(flag);
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }

    }

    //取消订单
    @DeleteMapping("/{id}")
    public RestResult deleteOrder(@PathVariable("id")Long id){
        try {
            int flag = 0;
            flag = orderServiceImpl.deleteOrder(id);
            if(flag > 0){
                return RestResult.success(flag);
            }else{
                return RestResult.failure("请检查订单状态是否异常");
            }
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //订单支付
    @PutMapping("/pay/{id}")
    public RestResult payOrder(@PathVariable("id")Long id,@RequestBody SysProductOrder order) throws Exception {
        try {
            return RestResult.success(orderServiceImpl.updatePayStatus(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //订单出库（开始发货）-库存数量减少-客单状态改变
    @PutMapping("/send/{id}")
    public RestResult OutProduct(@PathVariable("id")Long id) throws Exception {
        try {
            return RestResult.success(orderServiceImpl.updateSendOrder(id));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //订单确定收货
    @PutMapping("/receive/{id}")
    public RestResult recevieOrder(@PathVariable("id")Long id) throws Exception {
        try {
            return RestResult.success(orderServiceImpl.updateRecevieOrder(id));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //订单回访
    @PutMapping("/finish/{id}")
    public RestResult finishOrder(@PathVariable("id")Long id,@RequestBody SysProductOrder order) throws Exception {
        try {
            return RestResult.success(orderServiceImpl.finishOrderRemark(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //订单采购提醒
    @PutMapping("/warn/{id}")
    public RestResult warnOrder(@PathVariable("id")Long id) throws Exception{
        try {
            return RestResult.success(orderServiceImpl.warnOrder(id));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

}
