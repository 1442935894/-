package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductCategory;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.SysUser;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/3
 */
public class ProductMapper extends SQL{
    private static final String TABLE_NAME_PRODUCT = "sys_product";
    private static final String TABLE_NAME_SUPPLIER_PRODUCT = "sys_supplier_product";
    private static final String TABLE_NAME_PRODUCT_CATEGORY = "sys_product_category";
    public String updateProduct(SysProduct record) {
        return new SQL(){{
            UPDATE(TABLE_NAME_PRODUCT);
            if (!StringUtils.isEmpty(record.getName())) {
                SET("name= #{name,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getImageUrl())) {
                SET("image_url= #{imageUrl,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getDescription())) {
                SET("description= #{description,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getUpdateById())) {
                SET("update_by_id= #{updateById,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateDatetime())) {
                SET("update_datetime= #{updateDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            if(!StringUtils.isEmpty(record.getIfDelete())){
                SET("if_delete= #{ifDelete,javaType=Boolean,jdbcType=BIT}");
            }
            WHERE("id = #{id,javaType=Long,jdbcType=BIGINT}" );
        }}.toString();
    }
    public String updateSupplierProduct(SysSupplierProduct record){
        return new SQL(){{
            UPDATE(TABLE_NAME_SUPPLIER_PRODUCT);
            if (!StringUtils.isEmpty(record.getSupplierId())) {
                SET("supplier_id= #{supplierId,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getProductId())) {
                SET("product_id= #{productId,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getCostPrice())) {
                SET("cost_price= #{costPrice,javaType=BigDecimal,jdbcType=DECIMAL}");
            }
            if (!StringUtils.isEmpty(record.getPrice())) {
                SET("price= #{price,javaType=BigDecimal,jdbcType=DECIMAL}");
            }
            if (!StringUtils.isEmpty(record.getUpdateById())) {
                SET("update_by_id= #{updateById,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateDatetime())) {
                SET("update_datetime= #{updateDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            WHERE("id = #{id,javaType=Long,jdbcType=BIGINT}" );
        }}.toString();
    }
    public String updateProductCategory(SysProductCategory record){
        return new SQL(){{
            UPDATE(TABLE_NAME_PRODUCT_CATEGORY);
            if (!StringUtils.isEmpty(record.getCategoryId())) {
                SET("category_id= #{categoryId,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getProductId())) {
                SET("product_id= #{productId,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateById())) {
                SET("update_by_id= #{updateById,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateDatetime())) {
                SET("update_datetime= #{updateDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            WHERE("id = #{id,javaType=Long,jdbcType=BIGINT}" );
        }}.toString();
    }
}
