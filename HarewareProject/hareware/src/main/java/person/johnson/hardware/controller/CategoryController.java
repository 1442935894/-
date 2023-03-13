package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.vo.CategoryVo;
import person.johnson.hardware.service.CategoryService;

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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    List<SysCategory> categories =new ArrayList<SysCategory>();
    Long userId = null;

    //查询列表
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        categories = categoryServiceImpl.getAll();
        PageInfo<SysCategory> pageInfo = new PageInfo<>(categories);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //模糊查询商品分类目录
    @GetMapping("/list/{name}")
    public RestResult getBodyByName(@PathVariable("name")String name,
                                    @RequestParam("pageNum")int pageNum,
                                    @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        categories = categoryServiceImpl.getBodyByName(name);
        PageInfo<SysCategory> pageInfo = new PageInfo<>(categories);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //查询单条列表信息
    @GetMapping("/{id}")
    public RestResult getOneCategory(@PathVariable("id") Long id) {
        return RestResult.success(categoryServiceImpl.getOneCategory(id));
    }
    //添加种类信息
    @PostMapping("")
    public RestResult insertCategory(@RequestBody SysCategory category) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            category.setCreateById(userId);
            category.setCreateDatetime(new Date());
            return RestResult.success(categoryServiceImpl.insertCategory(category));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }
    //修改种类信息
    @PutMapping("/{id}")
    public RestResult updateCategory(@RequestBody SysCategory category){
        userId = tokenUtil.getUserIdByToken();
        category.setUpdateById(userId);
        category.setUpdateDatetime(new Date());
        return RestResult.success(categoryServiceImpl.updateCategory(category));
    }

    //删除种类信息
    @DeleteMapping("/{ids}")
    public RestResult deleteCategory(@PathVariable("ids") Long[] ids){
        return RestResult.success(categoryServiceImpl.delete(Arrays.asList(ids)));
    }

    //查询分类下的产品
    @GetMapping("/product/{cid}")
    public RestResult findProductByCid(@PathVariable("cid")Long cid,
                                       @RequestParam("pageNum")int pageNum,
                                       @RequestParam("pageSize")int pageSize){
        List<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
        PageHelper.startPage(pageNum,pageSize);
        categoryVos = categoryServiceImpl.getProductByCid(cid);
        PageInfo<CategoryVo> pageInfo = new PageInfo<>(categoryVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

}