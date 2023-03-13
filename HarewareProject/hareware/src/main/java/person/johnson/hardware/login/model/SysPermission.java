package person.johnson.hardware.login.model;

import java.io.Serializable;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */

public class SysPermission implements Serializable {
    private Long id;
    private String permission;

    public SysPermission() {
    }

    public SysPermission(Long id, String permission) {
        this.id = id;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
