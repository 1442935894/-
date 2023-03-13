package person.johnson.hardware.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/8
 */
@JsonIgnoreProperties(value = "handler")
public class OrderItemVo {
    private Long itemId;
    private Long orderId;                   //父订单序号
    private Long supplierId;                //供应商id
    private String supplierName;            //供应商名称
    private Long productId;                 //商品id
    private String productName;             //商品名称
    private BigDecimal productCostPrice;    //商品成本
    private BigDecimal productPrice;        //商品售价
    private Long productNumber;             //商品购买数量
    private String remark;                  //顾客留言
    private String createById;              //销售员id
    private Date createDatetime;            //订单创建时间
    private List<ProductVo> productVos;     //商品具体信息

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getProductCostPrice() {
        return productCostPrice;
    }

    public void setProductCostPrice(BigDecimal productCostPrice) {
        this.productCostPrice = productCostPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public List<ProductVo> getProductVos() {
        return productVos;
    }

    public void setProductVos(List<ProductVo> productVos) {
        this.productVos = productVos;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderItemVo{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCostPrice=" + productCostPrice +
                ", productPrice=" + productPrice +
                ", productNumber=" + productNumber +
                ", remark='" + remark + '\'' +
                ", createById='" + createById + '\'' +
                ", createDatetime=" + createDatetime +
                ", productVos=" + productVos +
                '}';
    }
}
