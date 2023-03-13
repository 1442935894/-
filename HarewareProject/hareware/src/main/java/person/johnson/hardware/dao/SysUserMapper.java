package person.johnson.hardware.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.dao.sql.CustomerMapper;
import person.johnson.hardware.dao.sql.UserMapper;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.UserVo;

@Mapper
public interface SysUserMapper {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_user (create_datetime, create_by_id, ",
        "update_datetime, update_by_id, ",
        "if_delete, username, `password`, ",
        "last_login_datetime, sex, ",
        "birthtime, avatar_url, ",
        "address, email, ",
        "`status`)",
        "values (#{createDatetime,jdbcType=TIMESTAMP}, #{createById,jdbcType=BIGINT}, ",
        "#{updateDatetime,jdbcType=TIMESTAMP}, #{updateById,jdbcType=BIGINT}, ",
        "#{ifDelete,jdbcType=BIT}, #{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{lastLoginDatetime,jdbcType=TIMESTAMP}, #{sex,jdbcType=BIT}, ",
        "#{birthtime,jdbcType=TIMESTAMP}, #{avatarUrl,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysUser record);

    @Select({
        "select",
        "id, create_datetime, create_by_id, update_datetime, update_by_id, if_delete, ",
        "username, `password`, last_login_datetime, sex, birthtime, avatar_url, address, ",
        "email, `status`",
        "from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
        @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.BIT)
    })
    SysUser selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, create_datetime, create_by_id, update_datetime, update_by_id, if_delete, ",
        "username, `password`, last_login_datetime, sex, birthtime, avatar_url, address, ",
        "email, `status`",
        "from sys_user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
        @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
        @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
        @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.BIT)
    })
    List<SysUser> selectAll();

    @Update({
        "update sys_user",
        "set create_datetime = #{createDatetime,jdbcType=TIMESTAMP},",
          "create_by_id = #{createById,jdbcType=VARCHAR},",
          "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP},",
          "update_by_id = #{updateById,jdbcType=VARCHAR},",
          "if_delete = #{ifDelete,jdbcType=BIT},",
          "username = #{username,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR},",
          "last_login_datetime = #{lastLoginDatetime,jdbcType=TIMESTAMP},",
          "sex = #{sex,jdbcType=BIT},",
          "birthtime = #{birthtime,jdbcType=TIMESTAMP},",
          "avatar_url = #{avatarUrl,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);

    @Select({
            "select u.id,u.username, u.`password`, u.last_login_datetime, u.sex, u.birthtime, u.avatar_url, u.address,",
            "u.email, u.`status`, u.if_delete, u.create_datetime, u.create_by_id, u.update_datetime,u.update_by_id,",
            "r.id as role_id,r.role_name",
            "from sys_user as u,sys_role as r,sys_role_user as ru",
            "where r.id = ru.role_id and u.id=ru.user_id and u.if_delete=0"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.BIT),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    List<UserVo> getAll();

    @Select({
            "select u.id,u.username, u.`password`, u.last_login_datetime, u.sex, u.birthtime, u.avatar_url, u.address,",
            "u.email, u.`status`, u.if_delete, u.create_datetime, u.create_by_id, u.update_datetime,u.update_by_id,",
            "r.id as role_id,r.role_name",
            "from sys_user as u,sys_role as r,sys_role_user as ru",
            "where r.id = ru.role_id and u.id=ru.user_id and  u.if_delete=0",
            "and u.username like concat('%',#{username,jdbcType=VARCHAR},'%')"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.BIT),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    List<UserVo> getBodyByUsername(String username);

    @Select({
            "select u.id,u.username, u.`password`, u.last_login_datetime, u.sex, u.birthtime, u.avatar_url, u.address,",
            "u.email, u.`status`, u.if_delete, u.create_datetime, u.create_by_id, u.update_datetime,u.update_by_id,",
            "r.id as role_id,r.role_name",
            "from sys_user as u,sys_role as r,sys_role_user as ru",
            "where r.id = ru.role_id and u.id=ru.user_id and  u.if_delete=0",
            "and u.id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.BIT),
            @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    UserVo getOneByKey(Long id);

    @UpdateProvider(type = UserMapper.class,method = "updateUser")
    int updateSomeByKey(SysUser user);

    @Update({
            "update sys_user",
            "set `password` = #{password,jdbcType=VARCHAR},",
            "update_by_id = #{updateById,jdbcType=VARCHAR},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int resetPwd(SysUser record);

    @Update({
            "update sys_user",
            "set `status` = #{status,jdbcType=BIT},",
            "update_by_id = #{updateById,jdbcType=VARCHAR},",
            "update_datetime = #{updateDatetime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int frozenUser(SysUser record);

    @Select({
            "select",
            "id, create_datetime, create_by_id, update_datetime, update_by_id, if_delete, ",
            "username, `password`, last_login_datetime, sex, birthtime, avatar_url, address, ",
            "email, `status`",
            "from sys_user",
            "where username = #{username}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="create_datetime", property="createDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_by_id", property="createById", jdbcType=JdbcType.BIGINT),
            @Result(column="update_datetime", property="updateDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_by_id", property="updateById", jdbcType=JdbcType.BIGINT),
            @Result(column="if_delete", property="ifDelete", jdbcType=JdbcType.BIT),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_login_datetime", property="lastLoginDatetime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="sex", property="sex", jdbcType=JdbcType.BIT),
            @Result(column="birthtime", property="birthtime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.BIT)
    })
    SysUser selectByUsername(String username);


    @Update({
            "update sys_user",
            "set ",
            "last_login_datetime = #{datetime}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateLastLogintime(Long id, Date datetime);


    @Select({
            "select",
            "id,username,avatar_url ",
            "from sys_user",
            "where if_delete = 0 and status = 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar_url", property="avatarUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUser> selectSales();
}