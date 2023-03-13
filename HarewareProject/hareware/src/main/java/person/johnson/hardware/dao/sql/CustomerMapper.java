package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.SysCustomer;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/26
 */
public class CustomerMapper extends SQL {
    private static final String TABLE_NAME = "sys_customer";
    public String updateCustomer(SysCustomer record) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (!StringUtils.isEmpty(record.getName())) {
                SET("name= #{name,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getPhone())) {
                SET("phone= #{phone,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getSex())) {
                SET("sex= #{sex,javaType=Boolean,jdbcType=TINYINT}");
            }
            if (!StringUtils.isEmpty(record.getEmail())) {
                SET("email= #{email,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getVipLevel())) {
                SET("vip_level= #{vipLevel,javaType=Byte,jdbcType=TINYINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateById())) {
                SET("update_by_id= #{updateById,javaType=Long,jdbcType=BIGINT}");
            }
            if (!StringUtils.isEmpty(record.getUpdateDatetime())) {
                SET("update_datetime= #{updateDatetime,javaType=Date,jdbcType=TIMESTAMP}");
            }
            if (!StringUtils.isEmpty(record.getAddress())) {
                SET("address= #{address,javaType=String,jdbcType=VARCHAR}");
            }
            WHERE("id = #{id,javaType=Long,jdbcType=BIGINT}" );
        }}.toString();
    }
}
