package person.johnson.hardware.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import person.johnson.hardware.dao.SysUserMapper;
import person.johnson.hardware.login.mapper.LoginUserMapper;
import person.johnson.hardware.model.SysUser;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('system:admin')")
public class HelloController {

    @Autowired(required = false)
    private SysUserMapper userMapper;

    @GetMapping("/hello")
    public List hello(){
        List<SysUser> list = userMapper.selectAll();
        return list;
    }
}
