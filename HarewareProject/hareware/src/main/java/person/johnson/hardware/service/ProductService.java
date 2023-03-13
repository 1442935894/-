package person.johnson.hardware.service;

import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductCategory;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.vo.ProductVo;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/2
 */
public interface ProductService {
    //查询商品基本信息(所有信息，包括分类)
    List<ProductVo> getAll();
    //模糊查询商品基本信息
    List<ProductVo> getBodyByName(String name);
    //查询单个商品基本信息
    ProductVo getOne(Long id);
    //按照商品分类查询商品信息
    List<ProductVo> getBodyByCid(Long cid);
    //添加商品信息
    int insertProduct(SysProduct product);
    //添加该商品的供应商
    int insertSuppProduct(SysSupplierProduct supplierProduct) throws Exception;
    //添加该商品的分类
    int insertProductCategory(SysProductCategory productCategory) throws Exception;
    //修改商品信息
    int updateProduct(SysProduct product);
    //修改库存/成本、售价、供应商信息
    int updateSupplierProduct(SysSupplierProduct supplierProduct) throws Exception;
    //修改该商品的分类信息
    int updateProductCategory(SysProductCategory productCategory);
    //删除商品来源
    int deleteSupplierProduct(SysSupplierProduct supplierProduct) throws Exception;
    //删除商品分类信息
    int deleteProductCategory(SysProductCategory productCategory);
    //删除商品信息(如果商品有其他连接表，则改为冻结状态)
    int deleteProduct(List<Object> ids);
}
