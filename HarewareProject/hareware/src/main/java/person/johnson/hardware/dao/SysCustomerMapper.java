package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.CategoryMapper;
import person.johnson.hardware.dao.sql.CustomerMapper;
import person.johnson.hardware.model.SysCustomer;
import person.johnson.hardware.model.vo.CustomerVo;

@Mapper
public interface SysCustomerMapper {
    @Delete({
        "delete from sys_customer",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_customer (`name`, phone, ",
        "sex, email, vip_level, ",
        "create_by_id, create_datetime, ",
        "update_by_id, update_datetime , address)",
        "values (#{name,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=BIT}, #{email,jdbcType=VARCHAR}, #{vipLevel,jdbcType=TINYINT}, ",
        "#{createById,jdbcType=BIGINT}, #{createDatetime,jdbcType=TIMESTAMP}, ",
        "#{updateById,jdbcType=BIGINT}, #{updateDatetime,jdbcType=TIMESTAMP},",
        "#{address,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysCustomer record);

    @Select({
        "select",
        "id, `name`, phone, sex, email, vip_level, create_by_id, create_datetime, update_by_id, ",
        "update_datetime,address",
        "from sys_customer",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    SysCustomer selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, `name`, phone, sex, email, vip_level, create_by_id, create_datetime, update_by_id, ",
        "update_datetime,address",
        "from sys_customer"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    List<SysCustomer> selectAll();

    @Select({
            "select",
            "c.id, c.`name`, c.phone, c.sex, c.email, c.vip_level, c.create_by_id, c.create_datetime, c.update_by_id, ",
            "c.update_datetime,c.address,u.username as create_name ,ut.username as update_name",
            "from sys_customer as c ",
            "left join sys_user as u ",
            "on u.id = c.create_by_id ",
            "left join sys_user as ut ",
            "on ut.id = c.update_by_id ",
            "where c.`name` like concat('%',#{name,jdbcType=VARCHAR},'%')"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_name", property="updateName", jdbcType=JdbcType.VARCHAR)
    })
    List<CustomerVo> getBodyByName(String name);

    @Update({
        "update sys_customer",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=BIT},",
          "email = #{email,jdbcType=VARCHAR},",
          "vip_level = #{vipLevel,jdbcType=TINYINT},",
          "address = #{address,jdbcType=VARCHAR},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysCustomer record);

    @UpdateProvider(type = CustomerMapper.class,method = "updateCustomer")
    int updateSomeByKey(SysCustomer record);

    @Select({
            "select",
            "c.id, c.`name`, c.phone, c.sex, c.email, c.vip_level, c.create_by_id, c.create_datetime, c.update_by_id, ",
            "c.update_datetime,c.address,u.username as create_name ,ut.username as update_name",
            "from sys_customer as c",
            "left join sys_user as u",
            "on u.id = c.create_by_id",
            "left join sys_user as ut",
            "on ut.id = c.update_by_id"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="vip_level", property="vipLevel", jdbcType=JdbcType.TINYINT),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_name", property="createName", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_name", property="updateName", jdbcType=JdbcType.VARCHAR)
    })
    List<CustomerVo> getAll();

}