package person.johnson.hardware.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import person.johnson.hardware.common.result.RestResult;
import person.johnson.hardware.login.util.TokenUtil;
import person.johnson.hardware.model.vo.CustomerVo;
import person.johnson.hardware.model.vo.UserVo;
import person.johnson.hardware.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/9/25
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired(required = false)
    private UserService userServiceImpl;
    @Resource
    private TokenUtil tokenUtil;
    Long userId = null;
    List<UserVo> userVos=new ArrayList<UserVo>();

    //查询当前用户的用户信息
    @GetMapping("/person")
    public RestResult getPerson(){
        userId = tokenUtil.getUserIdByToken();
        UserVo userVo = userServiceImpl.getOne(userId);
        return RestResult.success(userVo);
    }
    //查询系统用户信息
    @GetMapping("/list")
    public RestResult getAll(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        userVos = userServiceImpl.getAll();
        PageInfo<UserVo> pageInfo = new PageInfo<>(userVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }
    //查询单个系统用户信息
    @GetMapping("/{id}")
    public RestResult getOne(@PathVariable("id") Long id){
        return RestResult.success(userServiceImpl.getOne(id));
    }

    //模糊用户信息
    @GetMapping("/list/{username}")
    public RestResult getBodyByUsername(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,
                                     @PathVariable("username") String username){
        PageHelper.startPage(pageNum,pageSize);
        userVos = userServiceImpl.getBodyByUsername(username);
        PageInfo<UserVo> pageInfo = new PageInfo<>(userVos);
        RestResult result = RestResult.success(pageInfo.getList());
        result.setPage((int) pageInfo.getTotal(),pageInfo);
        return result;
    }

    //添加系统用户
    @PostMapping("")
    public RestResult insert(@RequestBody UserVo userVo){
        userId = tokenUtil.getUserIdByToken();
        userVo.setCreateById(userId);
        userVo.setCreateDatetime(new Date());
        return RestResult.success(userServiceImpl.insert(userVo));
    }

    //更新系统用户信息
    @PutMapping("/{id}")
    public RestResult updateUser(@PathVariable("id")Long id,@RequestBody UserVo userVo) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            userVo.setUpdateById(userId);
            userVo.setUpdateDatetime(new Date());
            return RestResult.success(userServiceImpl.updateUserMsg(id,userVo));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //重置（更新）用户密码
    @PutMapping("/reset/{id}")
    public RestResult resetPwd(@PathVariable("id") Long id,@RequestBody UserVo userVo) throws Exception {
        try {
            userId = tokenUtil.getUserIdByToken();
            userVo.setUpdateById(userId);
            userVo.setUpdateDatetime(new Date());
            return RestResult.success(userServiceImpl.resetPwd(id,userVo));
        } catch (Exception e) {
            return RestResult.failure(e.getMessage());
        }
    }

    //(冻结/启用)用户
    @PutMapping("/frozen/{ids}")
    public RestResult frozenUser(@PathVariable("ids") Long[] ids,@RequestBody UserVo userVo){
        userId = tokenUtil.getUserIdByToken();
        userVo.setUpdateById(userId);
        userVo.setUpdateDatetime(new Date());
        return RestResult.success(userServiceImpl.frozenUser(Arrays.asList(ids),userVo));
    }

    //删除用户
    @DeleteMapping("/{ids}")
    public RestResult deleteUser(@PathVariable("ids") Long[] ids){
        return RestResult.success(userServiceImpl.deleteUser(Arrays.asList(ids)));
    }

}
