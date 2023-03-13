package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysPurchaseOrder;
import person.johnson.hardware.model.vo.OrderVo;
import person.johnson.hardware.model.vo.PurchaseVo;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.OrderService;
import person.johnson.hardware.service.PurchaseService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/10
 */

@RestController
@CrossOrigin
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseServiceImpl;
    @Autowired
    private OrderService orderServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;
    private List<PurchaseVo> purchaseVos=new ArrayList<PurchaseVo>();

    //全部采购单订单列表
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        purchaseVos = purchaseServiceImpl.getAll();
        PageInfo<PurchaseVo> pageInfo = new PageInfo<PurchaseVo>(purchaseVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //根据采购员查询采购员负责的订单
    @GetMapping("/list/{id}")
    public RestResult getAllByName(@PathVariable("id")Long id,@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        purchaseVos = purchaseServiceImpl.getBodyById(id);
        PageInfo<PurchaseVo> pageInfo = new PageInfo<PurchaseVo>(purchaseVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    @PostMapping("")
    //添加采购单(可以是单独购买、也可以是根据订单情况购买)
    public RestResult insertPurchase(@RequestBody List<SysPurchaseOrder> orders) throws Exception {
        try {
            return RestResult.success(purchaseServiceImpl.insertPurchaseOrder(orders));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    //修改采购单数量
    public RestResult updatePurchaseNumber(@PathVariable("id")Long id,@RequestBody SysPurchaseOrder order) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            order.setUpdateById(userId);
            order.setUpdateDatetime(new Date());
            return RestResult.success(purchaseServiceImpl.updateNumber(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public RestResult cancelPurchase(@PathVariable("id")Long id,@RequestBody SysPurchaseOrder order) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            order.setUpdateById(userId);
            order.setUpdateDatetime(new Date());
            return RestResult.success(purchaseServiceImpl.deletePurchase(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    @PutMapping("/buy/{id}")
    public RestResult updateOrderStatus(@PathVariable("id")Long id,@RequestBody SysPurchaseOrder order) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            order.setUpdateById(userId);
            order.setUpdateDatetime(new Date());
            return RestResult.success(purchaseServiceImpl.updateOrderStatus(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    @PutMapping("/finish/{id}")
    public RestResult finishOrder(@PathVariable("id")Long id,@RequestBody SysPurchaseOrder order) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            order.setUpdateById(userId);
            order.setUpdateDatetime(new Date());
            return RestResult.success(purchaseServiceImpl.finishPurchaseOrder(id,order));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //查询需要添置的客单数据
    @GetMapping("/order/list")
    public RestResult findOrders(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize){
        List<OrderVo> orderVos = new ArrayList<>();
        orderVos = orderServiceImpl.getWarnOrder();
        PageInfo<OrderVo> pageInfo = new PageInfo<OrderVo>(orderVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

}
