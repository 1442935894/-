package person.johnson.hardware.dao.sql;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysProductOrder;

import java.util.Date;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/11
 */
public class OrderMapper extends SQL {
    private static final String TABLE_NAME = "sys_product_order";
    public String selectOrder(String startDate,String endDate,String username) {
        return new SQL(){{
        }}.toString();
    }
}
