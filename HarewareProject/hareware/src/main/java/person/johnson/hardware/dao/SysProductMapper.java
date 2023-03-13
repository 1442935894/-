package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.ProductMapper;
import person.johnson.hardware.dao.sql.UserMapper;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.ProductVo;

@Mapper
public interface SysProductMapper {
    @Delete({
        "delete from sys_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_product (`name`, description, ",
        "image_url, create_by_id, ",
        "create_datetime, update_by_id, ",
        "update_datetime, if_delete)",
        "values (#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{imageUrl,jdbcType=VARCHAR}, #{createById,jdbcType=BIGINT}, ",
        "#{createDatetime,jdbcType=TIMESTAMP}, #{updateById,jdbcType=BIGINT}, ",
        "#{updateDatetime,jdbcType=TIMESTAMP}, #{ifDelete,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysProduct record);

    @Select({
        "select",
        "id, `name`, description, image_url, create_by_id, create_datetime, update_by_id, ",
        "update_datetime",
        "from sys_product",
        "where id = #{id,jdbcType=BIGINT} and if_delete = 0"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysProduct selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, `name`, description, image_url, create_by_id, create_datetime, update_by_id, ",
        "update_datetime",
        "from sys_product where if_delete = 0"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysProduct> selectAll();

    @Update({
        "update sys_product",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "image_url = #{imageUrl,jdbcType=VARCHAR},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
          "if_delete = #{ifDelete,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysProduct record);

    //多表联查(列表=产品表+分类表+供应商表)
    @Select({
            "select",
            "id, `name`, description, image_url, create_by_id, create_datetime, update_by_id, ",
            "update_datetime",
            "from sys_product where if_delete = 0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "categories",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysCategoryMapper.getCategoryByPid",fetchType = FetchType.LAZY)),
            @Result(property = "suppliers",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysSupplierMapper.getSupplierByPid",fetchType = FetchType.LAZY))
    })
    List<ProductVo> getAll();

    //模糊查询（添加通过name模糊查询）
    @Select({
            "select",
            "id, `name`, description, image_url, create_by_id, create_datetime, update_by_id, ",
            "update_datetime",
            "from sys_product ",
            "where `name` like concat('%',#{name,jdbcType=VARCHAR},'%') and if_delete = 0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "categories",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysCategoryMapper.getCategoryByPid",fetchType = FetchType.LAZY)),
            @Result(property = "suppliers",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysSupplierMapper.getSupplierByPid",fetchType = FetchType.LAZY))
    })
    List<ProductVo> getBodyByName(String name);

    //单个产品信息查询
    @Select({
            "select",
            "id, `name`, description, image_url, create_by_id, create_datetime, update_by_id, ",
            "update_datetime",
            "from sys_product ",
            "where id = #{id,jdbcType=BIGINT} and if_delete = 0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "categories",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysCategoryMapper.getCategoryByPid",fetchType = FetchType.LAZY)),
            @Result(property = "suppliers",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysSupplierMapper.getSupplierByPid",fetchType = FetchType.LAZY))
    })
    ProductVo getOne(Long id);

    @Select({
            "select",
            "p.id, p.`name`, p.description, p.image_url, p.create_by_id, p.create_datetime, p.update_by_id, ",
            "p.update_datetime",
            "from sys_product as p",
            "left join sys_product_category as pc ",
            "on p.id = pc.product_id ",
            "where pc.category_id = #{cid}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "categories",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysCategoryMapper.getCategoryByPid",fetchType = FetchType.LAZY)),
            @Result(property = "suppliers",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysSupplierMapper.getSupplierByPid",fetchType = FetchType.LAZY))
    })
    List<ProductVo> findProductByCid(Long cid);
//    List<ProductVo> findProductBySid(Long sid);

    //非空字段更新
    @UpdateProvider(type = ProductMapper.class,method = "updateProduct")
    int updateSomeByKey(SysProduct product);
}