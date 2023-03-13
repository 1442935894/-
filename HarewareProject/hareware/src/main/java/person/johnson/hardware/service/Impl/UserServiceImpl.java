package person.johnson.hardware.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import person.johnson.hardware.dao.SysRoleUserMapper;
import person.johnson.hardware.dao.SysUserMapper;
import person.johnson.hardware.model.SysRoleUser;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private SysUserMapper userMapper;
    @Autowired(required = false)
    private SysRoleUserMapper roleUserMapper;
    List<UserVo> userVos=new ArrayList<UserVo>();

    @Override
    public List<UserVo> getAll() {
        userVos=userMapper.getAll();
        return userVos;
    }

    @Override
    public UserVo getOne(Long id) {
        return userMapper.getOneByKey(id);
    }

    @Override
    public int insert(UserVo userVo) {
        SysUser user = new SysUser();
        SysRoleUser roleUser = new SysRoleUser();
        //密码加密（md5）
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password  = encoder.encode(userVo.getPassword());
        userVo.setPassword(password);
        BeanUtils.copyProperties(userVo,user);
        Date date = new Date(System.currentTimeMillis());
        int a,b=0;
        user.setIfDelete(false);
        user.setStatus(false);
        a = userMapper.insert(user);
        roleUser.setRoleId(userVo.getRoleId());
        roleUser.setUserId(user.getId());
        roleUser.setCreateById(userVo.getCreateById());
        roleUser.setCreateDatetime(date);
        b = roleUserMapper.insert(roleUser);
        if(a==b && a!=0){
            return a;
        }else{
            return 0;
        }
    }

    @Override
    public int updateUserMsg(Long id,UserVo userVo) throws Exception {
        if(id!=userVo.getId() || StringUtils.isEmpty(userVo.getId())||StringUtils.isEmpty(userVo.getPassword())){
            throw new Exception("参数错误");
        }
        if(!StringUtils.isEmpty(userVo.getPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(userVo.getPassword());
            userVo.setPassword(password);
        }
        SysUser user = new SysUser();
        user.setId(id);
        BeanUtils.copyProperties(userVo,user);
        int sum=0;
        sum = userMapper.updateSomeByKey(user);
        SysRoleUser roleUser = roleUserMapper.getOne(user.getId());
        //判断角色是否更改
        if(roleUser.getRoleId()!=userVo.getRoleId()){
            roleUser.setRoleId(userVo.getRoleId());
        }
        roleUser.setUpdateById(userVo.getUpdateById());
        roleUser.setUpdateDatetime(userVo.getUpdateDatetime());
        roleUserMapper.updateRoleUser(roleUser);
        return sum;
    }

    @Override
    public int resetPwd(Long id,UserVo userVo) throws Exception {
        if(id!=userVo.getId() || StringUtils.isEmpty(userVo.getId())){
            throw new Exception("参数错误");
        }
        SysUser user= new SysUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password  = encoder.encode("123");
        //重置密码，需要加密
        userVo.setPassword(password);
        user.setId(id);
        BeanUtils.copyProperties(userVo,user);
        return userMapper.resetPwd(user);
    }

    @Override
    public int frozenUser(List<Object> ids, UserVo userVo) {
        SysUser user= new SysUser();
        BeanUtils.copyProperties(userVo,user);
        int sum = 0;
        for(Object id:ids){
            user.setId((Long) id);
            sum = sum + userMapper.frozenUser(user);
        }
        return sum;
    }

    @Override
    public int deleteUser(List<Object> ids) {
        int sum = 0;
        for(Object id : ids){
            SysUser user = new SysUser();
            user.setId((Long) id);
            user.setIfDelete(true);
            sum = sum + userMapper.updateSomeByKey(user);
        }
        return sum;
    }

    @Override
    public List<UserVo> getBodyByUsername(String username) {
        return userMapper.getBodyByUsername(username);
    }

    @Override
    public int updateLastLoginTime(Long id) {
        return userMapper.updateLastLogintime(id,new Date());
    }

    @Override
    public List<SysUser> getSales() {
        return userMapper.selectSales();
    }
}
