package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import person.johnson.hardware.model.vo.OrderItemVo;
import person.johnson.hardware.model.vo.OrderVo;
import person.johnson.hardware.service.BillService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/11
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class BillServiceImpl implements BillService {
    @Override
    public void exportExcel(List<OrderVo> orderVos, HttpServletResponse response) throws Exception {
        /* 数据库无记录 */
        if(orderVos.isEmpty()){
            throw new Exception("No Data");
        }

        /* 设置导出Excel信息 */
        HSSFWorkbook workbook = new HSSFWorkbook();          // 创建workbook
        HSSFSheet sheet = workbook.createSheet("订单");   // 创建sheet, 并设置sheet名
        sheet.autoSizeColumn(1);
        String fileName = "财务报表.xls";                   // 导出Excel文件名
        String[] headers ={"","总订单序号", "总订单号", "买家名", "买家电话","送货地址","支付价格","总订单价格","销售员名","子订单序号"
                ,"产品名","供应商","产品售价","产品成本","产品数量","产品总成本","订单原价收入","订单现利润","订单完成时间"};    // Excel中表头

        HSSFRow headerRow = sheet.createRow(0);            // 创建表头
        for(int i = 0; i < headers.length; i ++){
            HSSFCell cellTemp = headerRow.createCell(i);
            HSSFRichTextString headerText = new HSSFRichTextString(headers[i]);
            cellTemp.setCellValue(headerText);
        }

        List<OrderItemVo> orderItemVos = new ArrayList<>();
        int itemsNumber = 0;
        //总收入，总成本
        double amount = 0;
        double costAmount = 0;
        /* 插入数据 */
        int rowNum = 1; //从第二行(表头下一行开始插入)
        for(OrderVo person : orderVos){
            HSSFRow rowTemp = sheet.createRow(rowNum);
            orderItemVos = person.getItems();
            itemsNumber = itemsNumber + orderItemVos.size();
            //公共
            rowTemp.createCell(1).setCellValue(person.getOrderId());
            rowTemp.createCell(2).setCellValue(person.getOrderNo().toString());
            rowTemp.createCell(3).setCellValue(person.getBuyerName());
            rowTemp.createCell(4).setCellValue(person.getPhone());
            rowTemp.createCell(5).setCellValue(person.getAddress());
            rowTemp.createCell(6).setCellValue(person.getPayAmount().doubleValue());
            rowTemp.createCell(7).setCellValue(person.getOrderAmount().doubleValue());
            rowTemp.createCell(8).setCellValue(person.getCreateName());
            rowTemp.createCell(18).setCellValue((Date)person.getOrderFinishTime());
            double payAmount = person.getPayAmount().doubleValue();
            double orderAmount = person.getOrderAmount().doubleValue();
            amount = amount + payAmount;
            double percent = payAmount/orderAmount;
            rowNum += 1;
            //当子订单数量不为0，则需循环子订单数组，取得其中值
            if(orderItemVos.size()>0){
                for(OrderItemVo orderItemVo:orderItemVos){
                    HSSFRow rowItem = sheet.createRow(rowNum);
                    //String[] headers ={"总订单序号", "总订单号", "买家名", "买家电话","送货地址","总订单价格","子订单序号","产品名",
                    // "产品数量","","","",""};
                    double cost_price = orderItemVo.getProductCostPrice().doubleValue();
                    double price = orderItemVo.getProductPrice().doubleValue();
                    double number = orderItemVo.getProductNumber();
                    //子单成本及原价
                    double itemCostAmount = number * cost_price;
                    double itemPriceAmount = number * price;
                    double itemProfit = itemPriceAmount * percent - itemCostAmount;
                    //总成本
                    costAmount = costAmount + itemCostAmount;
                    rowItem.createCell(9).setCellValue(orderItemVo.getItemId());
                    rowItem.createCell(10).setCellValue(orderItemVo.getProductName());
                    rowItem.createCell(11).setCellValue(orderItemVo.getSupplierName());
                    rowItem.createCell(12).setCellValue(orderItemVo.getProductPrice().doubleValue());
                    rowItem.createCell(13).setCellValue(orderItemVo.getProductCostPrice().doubleValue());
                    rowItem.createCell(14).setCellValue(orderItemVo.getProductNumber());
                    rowItem.createCell(15).setCellValue(cost_price * number);
                    rowItem.createCell(16).setCellValue(itemPriceAmount);
                    rowItem.createCell(17).setCellValue(itemProfit);

                    rowNum += 1;
                }
            }
        }
        HSSFRow rowTemp = sheet.createRow(rowNum);
        double profit = amount - costAmount;
        rowTemp.createCell(0).setCellValue("总和");
        rowTemp.createCell(1).setCellValue("总订单量:" + orderVos.size());
        rowTemp.createCell(6).setCellValue("总收入:" + amount);
        rowTemp.createCell(9).setCellValue("子订单量:" + itemsNumber);
        rowTemp.createCell(14).setCellValue("");
        rowTemp.createCell(15).setCellValue("");
        //原价
        rowTemp.createCell(16).setCellValue("");
        rowTemp.createCell(17).setCellValue("实际利润:" + profit);
        //原利润
//        rowTemp.createCell(17).setCellValue(sumProfit);
        //保留两位数
        double oldComission = profit * 0.05;
        BigDecimal translation = new BigDecimal(oldComission);
        double newComission = translation.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        rowTemp.createCell(8).setCellValue("提成:"+newComission);

        String fileNameURL = URLEncoder.encode(fileName, "UTF-8");
        /* 设置response的header */
        response.setHeader("Content-disposition", "attachment; filename=" +fileNameURL+";"+"filename*=utf-8''"+fileNameURL);
        response.setContentType("application/Vnd.ms-excel;charset=UTF-8");
        // 请求或响应消息不能走缓存
        response.setHeader("Cache-Control", "no-cache");
        // 将Content-Disposition暴露出去，这样就可以用过JS获取到了
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        try{
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e){
            throw new Exception("RESPONSE_STREAM_ERROR");
        }
    }
}
