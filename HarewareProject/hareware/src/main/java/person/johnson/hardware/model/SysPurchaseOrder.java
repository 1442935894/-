package person.johnson.hardware.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysPurchaseOrder implements Serializable {
    private Long purchaseId;
    private Long purchaseNo;
    private Long orderId;
    private Long orderItemId;
    private Long supplierId;
    private Long productId;
    private BigDecimal productCostPrice;
    private BigDecimal productTotalCost;
    private Long productNumber;
    private Date orderFinishTime;
    private Byte orderStatus;
    private Long createById;
    private Date createDatetime;
    private Long updateById;
    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "SysPurchaseOrder{" +
                "purchaseId=" + purchaseId +
                ", purchaseNo=" + purchaseNo +
                ", orderId=" + orderId +
                ", orderItemId=" + orderItemId +
                ", supplierId=" + supplierId +
                ", productId=" + productId +
                ", productCostPrice=" + productCostPrice +
                ", productTotalCost=" + productTotalCost +
                ", productNumber=" + productNumber +
                ", orderFinishTime=" + orderFinishTime +
                ", orderStatus=" + orderStatus +
                ", createById=" + createById +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateDatetime=" + updateDatetime +
                '}';
    }
}