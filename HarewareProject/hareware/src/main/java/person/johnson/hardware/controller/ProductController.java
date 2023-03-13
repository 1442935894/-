package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductCategory;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.vo.ProductVo;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.ProductService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/2
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;
    List<ProductVo> productVoList = new ArrayList<ProductVo>();


    //查询商品基本信息列表
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        productVoList = productServiceImpl.getAll();
        PageInfo<ProductVo> pageInfo = new PageInfo<>(productVoList);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //模糊查询商品基本信息列表
    @GetMapping("/list/{name}")
    public RestResult getBodyByName(@PathVariable("name")String name,
                                    @RequestParam("pageNum")int pageNum,
                                    @RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        productVoList = productServiceImpl.getBodyByName(name);
        PageInfo<ProductVo> pageInfo = new PageInfo<>(productVoList);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //查询单个商品的基本信息
    @GetMapping("/{id}")
    public RestResult getBodyByName(@PathVariable("id")Long id){
        return RestResult.success(productServiceImpl.getOne(id));
    }

    //添加商品基本信息
    @PostMapping("")
    public RestResult insertProduct(@RequestBody SysProduct product){
        userId = tokenUtil.getUserIdByToken();
        product.setCreateById(userId);
        product.setCreateDatetime(new Date());
        return RestResult.success(productServiceImpl.insertProduct(product));
    }

    //添加该商品的供应商(添加商品对应供应商的成本及售价)
    @PostMapping("/supplier")
    public RestResult insertSupplierProduct(@RequestBody SysSupplierProduct supplierProduct) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            supplierProduct.setCreateById(userId);
            supplierProduct.setCreateDatetime(new Date());
            System.out.println("------->"+userId);
            return RestResult.success(productServiceImpl.insertSuppProduct(supplierProduct));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //添加该商品分类
    @PostMapping("/category")
    public RestResult insertProductCategory(@RequestBody SysProductCategory productCategory) throws Exception {
        userId = tokenUtil.getUserIdByToken();
        productCategory.setCreateById(userId);
        productCategory.setCreateDatetime(new Date());
        return RestResult.success(productServiceImpl.insertProductCategory(productCategory));
    }

    //修改商品基本信息
    @PutMapping("/{id}")
    public RestResult updateProduct(@RequestBody SysProduct product){
        userId = tokenUtil.getUserIdByToken();
        product.setUpdateById(userId);
        product.setUpdateDatetime(new Date());
        return RestResult.success(productServiceImpl.updateProduct(product));
    }

    //修改商品库存/售价成本供应商信息
    @PutMapping("/supplier/{id}")
    public RestResult updateSupplierProduct(@PathVariable("id")Long id,@RequestBody SysSupplierProduct supplierProduct) throws Exception {
        userId = tokenUtil.getUserIdByToken();
        supplierProduct.setUpdateById(userId);
        supplierProduct.setUpdateDatetime(new Date());
        return RestResult.success(productServiceImpl.updateSupplierProduct(supplierProduct));
    }

    //修改商品分类信息
    @PutMapping("/category/{id}")
    public RestResult updateProductCategory(@PathVariable("id")Long id,@RequestBody SysProductCategory productCategory){
        userId = tokenUtil.getUserIdByToken();
        productCategory.setUpdateById(userId);
        productCategory.setUpdateDatetime(new Date());
        return RestResult.success(productServiceImpl.updateProductCategory(productCategory));
    }

    //删除商品分类信息
    @DeleteMapping("/category/{id}")
    public RestResult deleteProductCategory(@PathVariable("id")Long id,@RequestBody SysProductCategory productCategory){

        return  RestResult.success(productServiceImpl.deleteProductCategory(productCategory));
    }
    //删除商品来源及成本信息
    @DeleteMapping("/supplier/{id}")
    public RestResult deleteSupplierProduct(@PathVariable("id")Long id,@RequestBody SysSupplierProduct supplierProduct) throws Exception {

        return RestResult.success(productServiceImpl.deleteSupplierProduct(supplierProduct));
    }

    //删除商品基本信息
    @DeleteMapping("/{ids}")
    public RestResult deleteProduct(@PathVariable("ids")Long[] ids){
        return RestResult.success(productServiceImpl.deleteProduct(Arrays.asList(ids)));
    }
}
