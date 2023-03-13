package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.CategoryMapper;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.vo.CategoryVo;

@Mapper
public interface SysCategoryMapper {
    @Delete({
        "delete from sys_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_category (category_name, create_by_id, ",
        "create_datetime, update_by_id, ",
        "update_datetime)",
        "values (#{categoryName,jdbcType=VARCHAR}, #{createById,jdbcType=BIGINT}, ",
        "#{createDatetime,jdbcType=TIMESTAMP}, #{updateById,jdbcType=BIGINT}, ",
        "#{updateDatetime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysCategory record);

    @Select({
        "select",
        "id, category_name, create_by_id, create_datetime, update_by_id, update_datetime",
        "from sys_category",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysCategory selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, category_name, create_by_id, create_datetime, update_by_id, update_datetime",
        "from sys_category"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysCategory> selectAll();

    @Update({
        "update sys_category",
        "set category_name = #{categoryName,jdbcType=VARCHAR},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysCategory record);

    @UpdateProvider(type = CategoryMapper.class,method = "updateCategory")
    int updateSomeByKey(SysCategory record);

    @Select({
            "select",
            "id, category_name, create_by_id, create_datetime, update_by_id, update_datetime",
            "from sys_category",
            "where category_name like concat('%',#{name,jdbcType=VARCHAR},'%') "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysCategory> getBodyByName(String name);


    @Select({
            "select",
            " * ",
            "from sys_category",
            "where category_name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysCategory> getOneByName(String name);

    //多表联查(多对多)
    @Select({
            "select",
            "id, category_name, create_by_id, create_datetime, update_by_id, update_datetime",
            "from sys_category"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(property = "products",column = "id",
                    many = @Many(select = "person.johnson.hardware.dao.SysProductMapper.findProductByCid",fetchType = FetchType.LAZY))
    })
    List<CategoryVo> getAll(Long id);

    @Select({
            "select * from sys_category where id in (select pc.category_id from sys_product_category as pc where pc.product_id = #{pid})"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="category_name", property="categoryName", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CategoryVo> getCategoryByPid(Long pid);

}