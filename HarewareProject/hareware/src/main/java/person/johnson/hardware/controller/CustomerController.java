package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.SysCustomer;
import person.johnson.hardware.model.vo.CustomerVo;
import person.johnson.hardware.service.CustomerService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;
    private List<CustomerVo> customers = new ArrayList<CustomerVo>();

    //添加客户信息
    @PostMapping("")
    public RestResult insertCustomer(@RequestBody SysCustomer customer){
        userId = tokenUtil.getUserIdByToken();
        customer.setCreateById(userId);
        customer.setCreateDatetime(new Date());
        return RestResult.success(customerServiceImpl.insertCustomer(customer));
    }

    //查询单个客户信息
    @GetMapping("/{id}")
    public RestResult getOneCustomer(@PathVariable("id") Long id) {
        return RestResult.success(customerServiceImpl.getOneCustomer(id));
    }

    //查询所有客户信息
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        customers = customerServiceImpl.getAll();
        PageInfo<CustomerVo> pageInfo = new PageInfo<>(customers);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //模糊查询客户信息
    @GetMapping("/list/{name}")
    public RestResult getBodyByName(@PathVariable("name") String name,
                                    @RequestParam("pageNum")int pageNum,
                                    @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        customers = customerServiceImpl.getBodyByName(name);
        PageInfo<CustomerVo> pageInfo = new PageInfo<>(customers);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //修改客户信息
    @PutMapping("/{id}")
    public RestResult updateCustomer(@RequestBody SysCustomer customer){
        userId = tokenUtil.getUserIdByToken();
        customer.setUpdateById(userId);
        customer.setUpdateDatetime(new Date());
        return RestResult.success(customerServiceImpl.updateCustomer(customer));
    }

    //删除客户信息
    @DeleteMapping("/{ids}")
    public RestResult deleteCustomer(@PathVariable("ids") Long[] ids){
        return RestResult.success(customerServiceImpl.delete(Arrays.asList(ids)));
    }
}
