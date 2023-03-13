package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.SupplierMapper;
import person.johnson.hardware.model.SysSupplier;
import person.johnson.hardware.model.SysSupplierProduct;
import person.johnson.hardware.model.vo.SupplierVo;

@Mapper
public interface SysSupplierMapper {
    @Delete({
        "delete from sys_supplier",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_supplier (`name`, phone, ",
        "email, address, ",
        "create_by_id, create_datetime, ",
        "update_by_id, update_datetime)",
        "values (#{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{createById,jdbcType=BIGINT}, #{createDatetime,jdbcType=TIMESTAMP}, ",
        "#{updateById,jdbcType=BIGINT}, #{updateDatetime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysSupplier record);

    @Select({
        "select",
        "id, `name`, phone, email, address, create_by_id, create_datetime, update_by_id, ",
        "update_datetime",
        "from sys_supplier",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysSupplier selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, `name`, phone, email, address, create_by_id, create_datetime, update_by_id, ",
        "update_datetime",
        "from sys_supplier"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysSupplier> selectAll();

    @Select({
            "select",
            "id, `name`, phone, email, address, create_by_id, create_datetime, update_by_id, ",
            "update_datetime",
            "from sys_supplier ",
            "where `name` = #{name}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysSupplier> selectByName(String name);

    @Update({
        "update sys_supplier",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysSupplier record);

    @UpdateProvider(type = SupplierMapper.class,method = "updateSupplier")
    int updateSomeByKey(SysSupplier record);

    @Select({
            "select",
            "id, `name`, phone, email, address, create_by_id, create_datetime, update_by_id, ",
            "update_datetime",
            "from sys_supplier",
            "where `name` like concat('%',#{name,jdbcType=VARCHAR},'%') "
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysSupplier> getBodyByName(String name);

    //多表联查(一对多)
    @Select({
            "select * from sys_supplier"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP)
//            @Result(property = "sysSupplierProduct",column = "id",javaType = SysSupplierProduct.class,
//                    one = @One(select = "person.johnson.hardware.dao.SysSupplierProductMapper.findSPBySid",fetchType = FetchType.LAZY)),
//            @Result(property = "products",column = "id",
//                    many = @Many(select = "person.johnson.hardware.dao.SysProduct.findProductBySid",fetchType = FetchType.LAZY))
    })
    List<SupplierVo> getAll();

//    @Select("select * from sys_supplier where id in (select sp.supplier_id from sys_supplier_product as sp where sp.product_id = #{pid})")
    @Select({"select s.*,sp.id as sp_id,sp.cost_price,sp.price,sp.number from sys_supplier as s",
            "left join sys_supplier_product as sp",
            "on sp.supplier_id = s.id",
            "where sp.product_id = #{pid}"})
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
            @Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="number", property="number", jdbcType=JdbcType.BIGINT),
            @Result(column="sp_id", property="spId", jdbcType=JdbcType.BIGINT)

    })
    List<SupplierVo> getSupplierByPid(Long pid);
}