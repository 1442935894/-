package person.johnson.hardware.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class SysCategory implements Serializable {
    private Long id;

    private String categoryName;

    private Long createById;

    private Date createDatetime;

    private Long updateById;

    private Date updateDatetime;

    private static final long serialVersionUID = 1L;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "SysCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createById=" + createById +
                ", createDatetime=" + createDatetime +
                ", updateById=" + updateById +
                ", updateDatetime=" + updateDatetime +
                '}';
    }
}