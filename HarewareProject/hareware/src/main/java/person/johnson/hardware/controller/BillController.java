package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.OrderVo;
import person.johnson.hardware.model.vo.PurchaseVo;
import person.johnson.hardware.service.BillService;
import person.johnson.hardware.service.OrderService;
import person.johnson.hardware.service.PurchaseService;
import person.johnson.hardware.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/11
 */
@RestController
@CrossOrigin
@PreAuthorize("hasAnyAuthority('system:treasurer')")
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billServiceImpl;
    @Autowired
    private OrderService orderServiceImpl;
    @Autowired
    private PurchaseService purchaseServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/down")
    public void downloadTemplate(@RequestParam("startDate")String startDate,
                                 @RequestParam("endDate")String endDate,
                                 @RequestParam("id")Long id,
                                 HttpServletResponse response) throws Exception {
        List<OrderVo> orderVos=orderServiceImpl.getBodyByDate(startDate,endDate,id);
        billServiceImpl.exportExcel(orderVos,response);
    }

    @GetMapping("/order/list")
    public RestResult getAll(@RequestParam("startDate")String startDate,
                             @RequestParam("endDate")String endDate,
                             @RequestParam("id")Long id,
                             @RequestParam("pageNum")int pageNum,
                             @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OrderVo> orderVos = orderServiceImpl.getBodyByDate(startDate,endDate,id);
        PageInfo<OrderVo> pageInfo = new PageInfo<OrderVo>(orderVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

//    @GetMapping("/purchase/list")
//    @ResponseBody
//    public RestResult getAll(@RequestParam("pageNum")int pageNum,
//                            @RequestParam("pageSize")int pageSize,
//                             @RequestParam("username")String username){
//        purchaseServiceImpl.getAll()
//    }

    @GetMapping("/user/sales")
    public RestResult getSales(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> users = userServiceImpl.getSales();
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(users);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

}
