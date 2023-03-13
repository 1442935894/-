package person.johnson.hardware.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/7
 */
@JsonIgnoreProperties(value = "handler")
public class OrderVo {
    private Long orderId;
    private Long orderNo;           //时间戳生成订单号
    private Long buyerId;           //买家id-customerId
    private String buyerName;       //买家名称
    private Byte vipLevel;          //买家等级
    private String phone;           //收货电话
    private String address;         //收货地址
    private Byte orderStatus;       //订单状态：0进行中，1已完成，2取消交易，3已结算
    private Date orderFinishTime;   //订单完成时间
    private Byte payStatus;         //支付状态：1未付款，2已付款
    private BigDecimal payAmount;   //支付金额
    private Date payTime;           //支付时间
    private Byte deliverStatus;     //物流状态：0未收货，1送货中，2已收货
    private Date buyerFinishTime;   //买家完成时间（确认收货）
    private BigDecimal orderAmount; //订单总金额
    private String remark;          //客户备注
    private Long createById;        //销售员id
    private String createName;      //销售员名称
    private Date createDatetime;    //订单创建时间
    private Byte warnStatus;        //提醒库存


    public Byte getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Byte vipLevel) {
        this.vipLevel = vipLevel;
    }

    private List<OrderItemVo> items;

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
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBuyerFinishTime() {
        return buyerFinishTime;
    }

    public void setBuyerFinishTime(Date buyerFinishTime) {
        this.buyerFinishTime = buyerFinishTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(Byte deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<OrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemVo> items) {
        this.items = items;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Byte getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(Byte warnStatus) {
        this.warnStatus = warnStatus;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", orderNo=" + orderNo +
                ", buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", vipLevel=" + vipLevel +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderFinishTime=" + orderFinishTime +
                ", payStatus=" + payStatus +
                ", payAmount=" + payAmount +
                ", payTime=" + payTime +
                ", deliverStatus=" + deliverStatus +
                ", buyerFinishTime=" + buyerFinishTime +
                ", orderAmount=" + orderAmount +
                ", remark='" + remark + '\'' +
                ", createById=" + createById +
                ", createName='" + createName + '\'' +
                ", createDatetime=" + createDatetime +
                ", warnStatus=" + warnStatus +
                ", items=" + items +
                '}';
    }

    public OrderVo() {
    }

    public OrderVo(Long orderId, Long orderNo, Long buyerId, String buyerName, Byte vipLevel, String phone, String address, Byte orderStatus, Date orderFinishTime, Byte payStatus, BigDecimal payAmount, Date payTime, Byte deliverStatus, Date buyerFinishTime, BigDecimal orderAmount, String remark, Long createById, String createName, Date createDatetime, Byte warnStatus, List<OrderItemVo> items) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.vipLevel = vipLevel;
        this.phone = phone;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderFinishTime = orderFinishTime;
        this.payStatus = payStatus;
        this.payAmount = payAmount;
        this.payTime = payTime;
        this.deliverStatus = deliverStatus;
        this.buyerFinishTime = buyerFinishTime;
        this.orderAmount = orderAmount;
        this.remark = remark;
        this.createById = createById;
        this.createName = createName;
        this.createDatetime = createDatetime;
        this.warnStatus = warnStatus;
        this.items = items;
    }
}
