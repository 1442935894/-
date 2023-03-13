package person.johnson.hardware.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysProductOrder implements Serializable {
    private Long orderId;

    private Long orderNo;

    private Long buyerId;

    private String phone;

    private String address;

    private Byte orderStatus;

    private Byte payStatus;

    private Byte deliverStatus;

    private Date createDatetime;

    private Date payTime;

    private Date buyerFinishTime;

    private Date orderFinishTime;

    private Long createById;

    private BigDecimal orderAmount;

    private BigDecimal payAmount;

    private String remark;

    private Byte warnStatus;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(Byte deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getBuyerFinishTime() {
        return buyerFinishTime;
    }

    public void setBuyerFinishTime(Date buyerFinishTime) {
        this.buyerFinishTime = buyerFinishTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(Byte warnStatus) {
        this.warnStatus = warnStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", deliverStatus=").append(deliverStatus);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", payTime=").append(payTime);
        sb.append(", buyerFinishTime=").append(buyerFinishTime);
        sb.append(", orderFinishTime=").append(orderFinishTime);
        sb.append(", createById=").append(createById);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", remark=").append(remark);
        sb.append(", warnStatus=").append(warnStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}