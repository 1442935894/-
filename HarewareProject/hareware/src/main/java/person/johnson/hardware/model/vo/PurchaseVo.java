package person.johnson.hardware.model.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/10
 */
public class PurchaseVo {
    private Long purchaseId;
    private Long purchaseNo;
    private Long orderId;
    private Long orderItemId;
    private OrderItemVo orderItemVo;
    private Long supplierId;
    private String supplierName;
    private Long productId;
    private String productName;
    private BigDecimal productCostPrice;
    private BigDecimal productTotalCost;
    private Long productNumber;
    private Date orderFinishTime;
    private Byte orderStatus;
    private Long createById;
    private String createName;
    private Date createDatetime;
    private Long updateById;
    private String updateName;
    private Date updateDatetime;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(Long purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderItemVo getOrderItemVo() {
        return orderItemVo;
    }

    public void setOrderItemVo(OrderItemVo orderItemVo) {
        this.orderItemVo = orderItemVo;
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

    public BigDecimal getProductTotalCost() {
        return productTotalCost;
    }

    public void setProductTotalCost(BigDecimal productTotalCost) {
        this.productTotalCost = productTotalCost;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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
        return "PurchaseVo{" +
                "purchaseId=" + purchaseId +
                ", purchaseNo=" + purchaseNo +
                ", orderId=" + orderId +
                ", orderItemId=" + orderItemId +
                ", orderItemVo=" + orderItemVo +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCostPrice=" + productCostPrice +
                ", productTotalCost=" + productTotalCost +
                ", productNumber=" + productNumber +
                ", orderFinishTime=" + orderFinishTime +
                ", orderStatus=" + orderStatus +
                ", createById=" + createById +
                ", createName='" + createName + '\'' +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateName='" + updateName + '\'' +
                ", updateDatetime=" + updateDatetime +
                '}';
    }

    public PurchaseVo() {
    }

    public PurchaseVo(Long purchaseId, Long purchaseNo, Long orderId, Long orderItemId, OrderItemVo orderItemVo, Long supplierId, String supplierName, Long productId, String productName, BigDecimal productCostPrice, BigDecimal productTotalCost, Long productNumber, Date orderFinishTime, Byte orderStatus, Long createById, String createName, Date createDatetime, Long updateById, String updateName, Date updateDatetime) {
        this.purchaseId = purchaseId;
        this.purchaseNo = purchaseNo;
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.orderItemVo = orderItemVo;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.productId = productId;
        this.productName = productName;
        this.productCostPrice = productCostPrice;
        this.productTotalCost = productTotalCost;
        this.productNumber = productNumber;
        this.orderFinishTime = orderFinishTime;
        this.orderStatus = orderStatus;
        this.createById = createById;
        this.createName = createName;
        this.createDatetime = createDatetime;
        this.updateById = updateById;
        this.updateName = updateName;
        this.updateDatetime = updateDatetime;
    }
}
