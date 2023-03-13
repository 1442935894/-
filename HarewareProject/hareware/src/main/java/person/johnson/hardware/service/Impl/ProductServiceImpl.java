package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.dao.SysProductCategoryMapper;
import person.johnson.hardware.dao.SysProductMapper;
import person.johnson.hardware.dao.SysSupplierProductMapper;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductCategory;
import person.johnson.hardware.model.SysSupplier;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.vo.ProductVo;
import person.johnson.hardware.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/2
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired(required = false)
    private SysProductMapper productMapper;
    @Autowired(required = false)
    private SysProductCategoryMapper productCategoryMapper;
    @Autowired(required = false)
    private SysSupplierProductMapper supplierProductMapper;

    @Override
    public List<ProductVo> getAll() {
        return productMapper.getAll();
    }

    @Override
    public List<ProductVo> getBodyByName(String name) {
        return productMapper.getBodyByName(name);
    }

    @Override
    public ProductVo getOne(Long id) {
        return productMapper.getOne(id);
    }

    @Override
    public List<ProductVo> getBodyByCid(Long cid) {
        return null;
    }

    @Override
    public int insertProduct(SysProduct product) {
        product.setIfDelete(false);
        return productMapper.insert(product);
    }

    @Override
    public int insertSuppProduct(SysSupplierProduct supplierProduct) throws Exception {
        supplierProduct.setNumber((long) 0);
//        BigDecimal costPrice = supplierProduct.getCostPrice();
//        BigDecimal price = supplierProduct.getPrice();
//        if(costPrice.compareTo(price) > -1){
//            throw new Exception("商品添加失败，成本价不能大于销售单价");
//        }
        Long sid = supplierProduct.getSupplierId();
        Long pid = supplierProduct.getProductId();
        int sum = 0;
        sum = supplierProductMapper.selectNumBySidAndPid(sid,pid);
        if(sum > 0){
            throw new Exception("该商品已有此供应商");
        }
        return supplierProductMapper.insert(supplierProduct);
    }

    @Override
    public int insertProductCategory(SysProductCategory productCategory) throws Exception {
        Long pid = productCategory.getProductId();
        Long cid = productCategory.getCategoryId();
        int sum = 0;
        sum = productCategoryMapper.selectNumByCidAndPid(cid,pid);
        if(sum > 0){
            throw new Exception("已存在该分类");
        }
        return productCategoryMapper.insert(productCategory);
    }

    @Override
    public int updateProduct(SysProduct product) {
        return productMapper.updateSomeByKey(product);
    }

    @Override
    public int updateSupplierProduct(SysSupplierProduct supplierProduct) throws Exception {
        if(!StringUtils.isEmpty(supplierProduct.getProductId())&&!StringUtils.isEmpty(supplierProduct.getSupplierId())){
            BigDecimal costPrice = supplierProduct.getCostPrice();
            BigDecimal price = supplierProduct.getPrice();
            if(costPrice.compareTo(price) > -1){
                throw new Exception("商品添加失败，成本价不能大于销售单价");
            }
            Long sid = supplierProduct.getSupplierId();
            Long pid = supplierProduct.getProductId();
            SysSupplierProduct newSupplierProduct=supplierProductMapper.selectBySidAndPid(sid,pid);
            supplierProduct.setId(newSupplierProduct.getId());
            return supplierProductMapper.updateSomeByKey(supplierProduct);
        }else{
            return -1;
        }
    }

    @Override
    public int updateProductCategory(SysProductCategory productCategory) {
        if(!StringUtils.isEmpty(productCategory.getProductId())&&!StringUtils.isEmpty(productCategory.getCategoryId())){
            Long cid = productCategory.getCategoryId();
            Long pid = productCategory.getProductId();
            SysProductCategory newProductCategory=productCategoryMapper.selectByCidAndPid(cid,pid);
            productCategory.setId(newProductCategory.getId());
            return productCategoryMapper.updateSomeByKey(productCategory);
        }else{
            return -1;
        }

    }

    @Override
    public int deleteSupplierProduct(SysSupplierProduct supplierProduct) throws Exception {
        if(!StringUtils.isEmpty(supplierProduct.getProductId())&&!StringUtils.isEmpty(supplierProduct.getSupplierId())) {

            Long sid = supplierProduct.getSupplierId();
            Long pid = supplierProduct.getProductId();
            SysSupplierProduct product = supplierProductMapper.selectBySidAndPid(sid,pid);
            if(product.getNumber()>0){
                throw new Exception("存在库存数量，无法删除");
            }
            return supplierProductMapper.deleteSupplierProduct(sid,pid);
        }
        throw new Exception("传递参数错误！");
    }

    @Override
    public int deleteProductCategory(SysProductCategory productCategory) {
        if(!StringUtils.isEmpty(productCategory.getProductId())&&!StringUtils.isEmpty(productCategory.getCategoryId())) {
            Long cid = productCategory.getCategoryId();
            Long pid = productCategory.getProductId();
            return productCategoryMapper.deleteProductCategory(cid, pid);
        }else{
            return -1;
        }
    }

    @Override
    public int deleteProduct(List<Object> ids) {
        int sum = 0;
        for(Object id:ids){
            SysProduct product=new SysProduct();
            product.setIfDelete(true);
            product.setId((Long) id);
            sum= sum + productMapper.updateSomeByKey(product);
        }
        return sum;
    }
}
