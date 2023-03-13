package person.johnson.hardware.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import person.johnson.hardware.model.SysCategory;
import person.johnson.hardware.model.SysProduct;

import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/3
 */
@JsonIgnoreProperties(value = "handler")
public class CategoryVo {
    //商品分类基本属性
    private Long id;
    private String categoryName;
    private Long createById;
    private Date createDatetime;
    private Long updateById;
    private Date updateDatetime;
    //多对多映射
    private List<SysProduct> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public List<SysProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SysProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createById=" + createById +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateDatetime=" + updateDatetime +
                ", products=" + products +
                '}';
    }
}
