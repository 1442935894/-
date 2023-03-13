package person.johnson.hardware.login.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import person.johnson.hardware.dao.SysRoleUserMapper;
import person.johnson.hardware.dao.SysUserMapper;
import person.johnson.hardware.login.mapper.LoginUserMapper;
import person.johnson.hardware.login.mapper.SysPermissionMapper;
import person.johnson.hardware.login.model.LoginUser;
import person.johnson.hardware.login.model.SysPermission;
import person.johnson.hardware.model.SysRoleUser;
import person.johnson.hardware.model.SysUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysPermissionMapper permissionMapper;
    @Autowired
    private SysRoleUserMapper roleUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("请输入用户名");
        }
        SysUser user = userMapper.selectByUsername(username);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        SysRoleUser roleUser = roleUserMapper.getOne(user.getId());
        List<SysPermission> permissions = permissionMapper.selectByRoleId(roleUser.getRoleId());
        //传递用户所具有的权限
        List<String> list = new ArrayList<>();
        for(SysPermission permission:permissions){
            list.add(permission.getPermission());
        }

        return new LoginUser(user,list);
    }
}
