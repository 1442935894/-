package person.johnson.hardware.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.model.SysRoleUser;

@Mapper
public interface SysRoleUserMapper {
    @Insert({
        "insert into sys_role_user (user_id, role_id, ",
        "create_datetime, create_by_id, ",
        "update_datetime, update_by_id)",
        "values (#{userId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT}, ",
        "#{createDatetime,jdbcType=TIMESTAMP}, #{createById,jdbcType=BIGINT}, ",
        "#{updateDatetime,jdbcType=TIMESTAMP}, #{updateById,jdbcType=BIGINT})"
    })
    int insert(SysRoleUser record);

    @Select({
        "select",
        "user_id, role_id, create_datetime, create_by_id, update_datetime, update_by_id",
        "from sys_role_user"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT)
    })
    List<SysRoleUser> selectAll();

    @Select({
            "select",
            "user_id, role_id, create_datetime, create_by_id, update_datetime, update_by_id",
            "from sys_role_user ",
            "where user_id=#{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT)
    })
    SysRoleUser getOne(Long id);


    @Update({
            "update sys_role_user",
            "set role_id= #{roleId,jdbcType=BIGINT},",
            "update_by_id = #{updateById,jdbcType=BIGINT},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateRoleUser(SysRoleUser record);
}