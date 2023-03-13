package person.johnson.hardware.login.service;

import person.johnson.hardware.login.util.ResponseResult;
import person.johnson.hardware.model.SysUser;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */
public interface LoginService {
    ResponseResult login(SysUser user);
    ResponseResult logout();
}
