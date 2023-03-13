package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysSupplier;
import person.johnson.hardware.model.vo.CustomerVo;
import person.johnson.hardware.service.SupplierService;

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
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired(required = false)
    private SupplierService supplierServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;
    List<SysSupplier> suppliers=new ArrayList<SysSupplier>();
    //查询供应商列表
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        suppliers = supplierServiceImpl.getAll();
        PageInfo<SysSupplier> pageInfo = new PageInfo<>(suppliers);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //模糊查询
    @GetMapping("/list/{name}")
    public RestResult getBodyByName(@PathVariable("name") String name,
                                    @RequestParam("pageNum")int pageNum,
                                    @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        suppliers = supplierServiceImpl.getBodyByName(name);
        PageInfo<SysSupplier> pageInfo = new PageInfo<>(suppliers);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //查询单个供应商
    @GetMapping("/{id}")
    public RestResult getOne(@PathVariable("id")Long id){
        return RestResult.success(supplierServiceImpl.getOneSupplier(id));
    }

    //添加供应商
    @PostMapping("")
    public RestResult insert(@RequestBody SysSupplier sysSupplier) throws Exception {
        userId = tokenUtil.getUserIdByToken();
        sysSupplier.setCreateById(userId);
        sysSupplier.setCreateDatetime(new Date());
        return RestResult.success(supplierServiceImpl.insertSupplier(sysSupplier));
    }

    //更新供应商信息
    @PutMapping("/{id}")
    public RestResult update(@RequestBody SysSupplier sysSupplier){
        userId = tokenUtil.getUserIdByToken();
        sysSupplier.setUpdateById(userId);
        sysSupplier.setUpdateDatetime(new Date());
        return RestResult.success(supplierServiceImpl.updateSupplier(sysSupplier));
    }

    //删除供应商信息
    @DeleteMapping("/{ids}")
    public RestResult delete(@PathVariable("ids") Long[] ids){
        return RestResult.success(supplierServiceImpl.delete(Arrays.asList(ids)));
    }

}
