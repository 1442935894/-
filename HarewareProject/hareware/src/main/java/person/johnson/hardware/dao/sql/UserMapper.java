package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysSupplier;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.UserVo;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/27
 */
public class UserMapper extends SQL {
    private static final String TABLE_NAME = "sys_user";
    public String updateUser(SysUser record) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (!StringUtils.isEmpty(record.getUsername())) {
                SET("username= #{username,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getAvatarUrl())) {
                SET("avatar_url= #{avatarUrl,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getPassword())) {
                SET("password= #{password,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getSex())) {
                SET("sex= #{sex,javaType=Boolean,jdbcType=BIT}");
            }
            if (!StringUtils.isEmpty(record.getBirthtime())) {
                SET("birthtime= #{birthtime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            if (!StringUtils.isEmpty(record.getEmail())) {
                SET("email= #{email,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getAddress())) {
                SET("address= #{address,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getStatus())) {
                SET("status= #{status,javaType=Boolean,jdbcType=BIT}");
            }
            if (!StringUtils.isEmpty(record.getIfDelete())) {
                SET("if_delete= #{ifDelete,javaType=Boolean,jdbcType=BIT}");
            }
            if (!StringUtils.isEmpty(record.getLastLoginDatetime())) {
                SET("last_login_datatime= #{lastLoginDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            if (!StringUtils.isEmpty(record.getUpdateById())) {
                SET("update_by_id= #{updateById,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateDatetime())) {
                SET("update_datetime= #{updateDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            WHERE("id = #{id,javaType=Long,jdbcType=BIGINT}" );
        }}.toString();
    }
}
