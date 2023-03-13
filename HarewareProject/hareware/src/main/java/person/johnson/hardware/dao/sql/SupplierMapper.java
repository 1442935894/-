package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.SysSupplier;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/26
 */
public class SupplierMapper extends SQL {
    private static final String TABLE_NAME_SUPPLIER = "sys_supplier";
    public String updateSupplier(SysSupplier record) {
        return new SQL(){{
            UPDATE(TABLE_NAME_SUPPLIER);
            if (!StringUtils.isEmpty(record.getName())) {
                SET("name= #{name,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getPhone())) {
                SET("phone= #{phone,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getEmail())) {
                SET("email= #{email,javaType=String,jdbcType=VARCHAR}");
            }
            if (!StringUtils.isEmpty(record.getAddress())) {
                SET("address= #{address,javaType=String,jdbcType=VARCHAR}");
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
