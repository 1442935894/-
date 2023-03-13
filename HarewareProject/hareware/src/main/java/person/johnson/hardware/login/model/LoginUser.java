package person.johnson.hardware.login.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import person.johnson.hardware.model.SysUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */
@Data
public class LoginUser implements UserDetails {

    private SysUser user;

    private List<String> permission;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities = new ArrayList<>();
        for(String perm:permission){
            authorities.add(new SimpleGrantedAuthority(perm));
        }
        return authorities;//该用户有哪些权限
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {//账号是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//是否被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//凭证是否过期
        return true;
    }

    @Override
    public boolean isEnabled() {//是否可用
        return this.user.getStatus();
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public LoginUser() {
    }

    public LoginUser(SysUser user,List<String> permission) {
        this.user = user;
        this.permission = permission;
    }
}
