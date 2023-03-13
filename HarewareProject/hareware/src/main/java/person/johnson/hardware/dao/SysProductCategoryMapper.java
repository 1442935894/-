package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.ProductMapper;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductCategory;

@Mapper
public interface SysProductCategoryMapper {
    @Delete({
        "delete from sys_product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_product_category (category_id, product_id, ",
        "create_by_id, create_datetime, ",
        "update_by_id, update_datetime)",
        "values (#{categoryId,jdbcType=BIGINT}, #{productId,jdbcType=BIGINT}, ",
        "#{createById,jdbcType=BIGINT}, #{createDatetime,jdbcType=BIGINT}, ",
        "#{updateById,jdbcType=VARCHAR}, #{updateDatetime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysProductCategory record);

    @Select({
        "select",
        "id, category_id, product_id, create_by_id, create_datetime, update_by_id, update_datetime",
        "from sys_product_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysProductCategory selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, category_id, product_id, create_by_id, create_datetime, update_by_id, update_datetime",
        "from sys_product_category"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysProductCategory> selectAll();

    @Update({
        "update sys_product_category",
        "set category_id = #{categoryId,jdbcType=BIGINT},",
          "product_id = #{productId,jdbcType=BIGINT},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysProductCategory record);

    @Select({
            "select",
            "id, category_id, product_id, create_by_id, create_datetime, update_by_id, update_datetime",
            "from sys_product_category",
            "where category_id = #{cid,jdbcType=BIGINT} and product_id=#{pid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysProductCategory selectByCidAndPid(Long cid,Long pid);


    //非空字段更新
    @UpdateProvider(type = ProductMapper.class,method = "updateProductCategory")
    int updateSomeByKey(SysProductCategory productCategory);

    @Delete({
            "delete from sys_product_category",
            "where category_id = #{cid,jdbcType=BIGINT} and product_id=#{pid,jdbcType=BIGINT}"
    })
    int deleteProductCategory(Long cid,Long pid);

    @Select({
            "select",
            "id, category_id, product_id, create_by_id, create_datetime, update_by_id, update_datetime",
            "from sys_product_category",
            "where product_id=#{pid,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT),
            @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysProductCategory> selectByPid(Long pid);

    @Select({
            "select",
            "count(*)",
            "from sys_product_category",
            "where category_id = #{cid,jdbcType=BIGINT} and product_id=#{pid,jdbcType=BIGINT}"
    })
    int selectNumByCidAndPid(Long cid,Long pid);
}