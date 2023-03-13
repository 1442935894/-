package person.johnson.hardware.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.johnson.hardware.login.service.LoginService;
import person.johnson.hardware.login.util.ResponseResult;
import person.johnson.hardware.model.SysUser;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser user){
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
