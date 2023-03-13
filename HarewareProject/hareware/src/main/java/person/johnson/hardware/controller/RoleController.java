package person.johnson.hardware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.dao.SysRoleMapper;
import person.johnson.hardware.service.RoleService;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/7
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleServiceImpl;
    @RequestMapping("/list")
    public RestResult roleList(){
        return RestResult.success(roleServiceImpl.getAll());
    }
}
