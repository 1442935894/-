package person.johnson.hardware.model.vo;

import person.johnson.hardware.model.SysProduct;
import person.johnson.hardware.model.SysSupplierProduct;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/3
 */
public class SupplierVo {
    //供应商基本属性
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Long createById;
    private Date createDatetime;
    private Long updateById;
    private Date updateDatetime;
    private Long spId;              //中间表序列
    private BigDecimal price;       //售价
    private BigDecimal costPrice;   //成本
    private Long number;            //库存
//    private SysSupplierProduct sysSupplierProduct;
    //多个供应商对多个产品
//    private List<SysProduct> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    @Override
    public String toString() {
        return "SupplierVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createById=" + createById +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateDatetime=" + updateDatetime +
                ", spId=" + spId +
                ", price=" + price +
                ", costPrice=" + costPrice +
                ", number=" + number +
                '}';
    }

    //    public List<SysProduct> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<SysProduct> products) {
//        this.products = products;
//    }
//
//    public SysSupplierProduct getSysSupplierProduct() {
//        return sysSupplierProduct;
//    }
//
//    public void setSysSupplierProduct(SysSupplierProduct sysSupplierProduct) {
//        this.sysSupplierProduct = sysSupplierProduct;
//    }
//
//    @Override
//    public String toString() {
//        return "SupplierVo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                ", createById=" + createById +
//                ", createDatetime=" + createDatetime +
//                ", updateById=" + updateById +
//                ", updateDatetime=" + updateDatetime +
//                ", sysSupplierProduct=" + sysSupplierProduct +
//                ", products=" + products +
//                '}';
//    }

}
