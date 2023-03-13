package person.johnson.hardware.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.SysSupplier;

import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/2
 */
@JsonIgnoreProperties(value = "handler")
public class ProductVo {
    //商品的基本属性
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean ifDelete;
    private Long createById;
    private Date createDatetime;
    private Long updateById;
    private Date updateDatetime;
    //商品分类属性
    List<SysCategory> categories;
    //商品供应商属性
    List<SysSupplier> suppliers;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public List<SysCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SysCategory> categories) {
        this.categories = categories;
    }

    public List<SysSupplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SysSupplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Boolean getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(Boolean ifDelete) {
        this.ifDelete = ifDelete;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ifDelete=" + ifDelete +
                ", createById=" + createById +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateDatetime=" + updateDatetime +
                ", categories=" + categories +
                ", suppliers=" + suppliers +
                '}';
    }
}
