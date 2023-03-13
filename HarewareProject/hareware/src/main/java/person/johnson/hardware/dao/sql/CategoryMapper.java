package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysCategory;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/26
 */
public class CategoryMapper extends SQL {
    private static final String TABLE_NAME = "sys_category";
    public String updateCategory(SysCategory record) {
        return new SQL(){{
            UPDATE(TABLE_NAME);
            if (!StringUtils.isEmpty(record.getCategoryName())) {
                SET("category_name= #{categoryName,javaType=String,jdbcType=VARCHAR}");
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
