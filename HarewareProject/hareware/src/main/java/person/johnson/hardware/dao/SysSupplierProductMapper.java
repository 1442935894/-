package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.ProductMapper;
import person.johnson.hardware.model.SysProductCategory;
import person.johnson.hardware.model.SysSupplierProduct;

@Mapper
public interface SysSupplierProductMapper<findSPBySid> {
    @Delete({
        "delete from sys_supplier_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_supplier_product (supplier_id, product_id, ",
        "price, cost_price, ",
        "`number`, create_by_id, ",
        "create_datetime, update_by_id, ",
        "update_datetime)",
        "values (#{supplierId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, ",
        "#{price,jdbcType=DECIMAL}, #{costPrice,jdbcType=DECIMAL}, ",
        "#{number,jdbcType=BIGINT}, #{createById,jdbcType=BIGINT}, ",
        "#{createDatetime,jdbcType=TIMESTAMP}, #{updateById,jdbcType=BIGINT}, ",
        "#{updateDatetime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysSupplierProduct record);

    @Select({
        "select",
        "id, supplier_id, product_id, price, cost_price, `number`, create_by_id, create_datetime, ",
        "update_by_id, update_datetime",
        "from sys_supplier_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="number", property="number", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysSupplierProduct selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, supplier_id, product_id, price, cost_price, `number`, create_by_id, create_datetime, ",
        "update_by_id, update_datetime",
        "from sys_supplier_product"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="number", property="number", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysSupplierProduct> selectAll();

    @Update({
        "update sys_supplier_product",
        "set supplier_id = #{supplierId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "price = #{price,jdbcType=DECIMAL},",
          "cost_price = #{costPrice,jdbcType=DECIMAL},",
          "`number` = #{number,jdbcType=BIGINT},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysSupplierProduct record);


    @Select({
            "select",
            "id, supplier_id, product_id, price, cost_price, `number`, create_by_id, create_datetime, ",
            "update_by_id, update_datetime",
            "from sys_supplier_product",
            "where supplier_id = #{sid,jdbcType=BIGINT} and product_id = #{pid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
            @Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="number", property="number", jdbcType=JdbcType.BIGINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysSupplierProduct selectBySidAndPid(Long sid,Long pid);

    //非空字段更新
    @UpdateProvider(type = ProductMapper.class,method = "updateSupplierProduct")
    int updateSomeByKey(SysSupplierProduct supplierProduct);


    @Update({
            "update sys_supplier_product",
            "set ",
            "`number` = #{number,jdbcType=BIGINT},",
            "update_by_id = #{updateById,jdbcType=BIGINT},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateNumberByKey(SysSupplierProduct record);

    @Delete({
            "delete from sys_supplier_product",
            "where supplier_id = #{sid,jdbcType=BIGINT} and product_id = #{pid,jdbcType=BIGINT}"
    })
    int deleteSupplierProduct(Long sid,Long pid);

    @Select({
            "select",
            "id, supplier_id, product_id, price, cost_price, `number`, create_by_id, create_datetime, ",
            "update_by_id, update_datetime",
            "from sys_supplier_product",
            "where product_id = #{pid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="supplier_id", property="supplierId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
            @Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="number", property="number", jdbcType=JdbcType.BIGINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysSupplierProduct> selectByPid(Long pid);

    @Select({
            "select",
            "count(*) ",
            "from sys_supplier_product",
            "where supplier_id = #{sid,jdbcType=BIGINT} and product_id = #{pid,jdbcType=BIGINT}"
    })
    int selectNumBySidAndPid(Long sid,Long pid);
}