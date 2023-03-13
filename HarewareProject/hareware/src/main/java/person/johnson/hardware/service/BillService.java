package person.johnson.hardware.service;

import person.johnson.hardware.model.vo.OrderVo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/11
 */
public interface BillService {
    void exportExcel(List<OrderVo> orderVos,HttpServletResponse response) throws Exception;
}
