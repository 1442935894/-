package person.johnson.hardware.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import person.johnson.hardware.login.model.SysPermission;
import person.johnson.hardware.model.SysCategory;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */

@Mapper
public interface SysPermissionMapper {
    @Select({
            "select p.id,p.permission from sys_permission as p",
            "LEFT JOIN sys_role_permission as rp",
            "on p.id = rp.premission_id",
            "LEFT JOIN sys_role as r",
            "on r.id = rp.role_id",
            "where rp.role_id = #{id}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
            @Result(column="permission", property="permission", jdbcType=JdbcType.VARCHAR)
    })
    List<SysPermission> selectByRoleId(Long id);
}
