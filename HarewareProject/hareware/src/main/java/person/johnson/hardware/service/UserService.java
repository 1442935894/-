package person.johnson.hardware.service;

import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.model.vo.UserVo;

import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
public interface UserService {
    //展示所有用户信息（角色）
    List<UserVo> getAll();
    //获取单个用户信息
    UserVo getOne(Long id);
    //添加系统用户
    int insert(UserVo userVo);
    //更新用户信息
    int updateUserMsg(Long id,UserVo userVo) throws Exception;
    //重置用户密码
    int resetPwd(Long id,UserVo userVo) throws Exception;
    //冻结/启用用户（删除用户）
    int frozenUser(List<Object> ids, UserVo userVo);
    //删除用户
    int deleteUser(List<Object> ids);
    //模糊查询
    List<UserVo> getBodyByUsername(String username);
    //更新最后登录时间
    int updateLastLoginTime(Long id);
    //获取所有销售员信息
    List<SysUser> getSales();
}
