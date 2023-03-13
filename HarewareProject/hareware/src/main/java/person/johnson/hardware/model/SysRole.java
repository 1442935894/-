package person.johnson.hardware.model;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    private Long id;

    private String roleName;

    private String description;

    private Long createById;

    private Long updateById;

    private Date createDatetime;

    private Date updateDatetime;

    private Long rolePower;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById ;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById ;
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

    public Long getRolePower() {
        return rolePower;
    }

    public void setRolePower(Long rolePower) {
        this.rolePower = rolePower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", description=").append(description);
        sb.append(", createById=").append(createById);
        sb.append(", updateById=").append(updateById);
        sb.append(", createDatetime=").append(createDatetime);
        sb.append(", updateDatetime=").append(updateDatetime);
        sb.append(", rolePower=").append(rolePower);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}