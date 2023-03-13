package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.model.SysRole;

@Mapper
public interface SysRoleMapper {
    @Delete({
        "delete from sys_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_role (role_name, description, ",
        "create_by_id, update_by_id, ",
        "create_datetime, update_datetime, ",
        "role_power)",
        "values (#{roleName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createById,jdbcType=BIGINT}, #{updateById,jdbcType=BIGINT}, ",
        "#{createDatetime,jdbcType=TIMESTAMP}, #{updateDatetime,jdbcType=TIMESTAMP}, ",
        "#{rolePower,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysRole record);

    @Select({
        "select",
        "id, role_name, description, create_by_id, update_by_id, create_datetime, update_datetime, ",
        "role_power",
        "from sys_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="role_power", property="rolePower", jdbcType=JdbcType.BIGINT)
    })
    SysRole selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, role_name, description, create_by_id, update_by_id, create_datetime, update_datetime, ",
        "role_power",
        "from sys_role"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="role_power", property="rolePower", jdbcType=JdbcType.BIGINT)
    })
    List<SysRole> selectAll();

    @Update({
        "update sys_role",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "create_by_id = #{createById,jdbcType=BIGINT},",
          "update_by_id = #{updateById,jdbcType=BIGINT},",
          "create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP},",
          "role_power = #{rolePower,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRole record);
}