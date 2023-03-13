package person.johnson.hardware.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import person.johnson.hardware.login.model.LoginUser;
import person.johnson.hardware.login.service.LoginService;
import person.johnson.hardware.login.util.JwtUtil;
import person.johnson.hardware.login.util.RedisCache;
import person.johnson.hardware.login.util.ResponseResult;
import person.johnson.hardware.model.SysUser;
import person.johnson.hardware.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser user) {

        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if(Objects.isNull(authenticate)){ //说明输入错误
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal(); // 这个其实就是UserDetails 也就是LoginUser
        String userId = loginUser.getUser().getId().toString();
        Long uid = loginUser.getUser().getId();


        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:"+userId,loginUser);//将用户信息直接存入redis

        userServiceImpl.updateLastLoginTime(uid);

        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
//        map.put("userId",user.getId().toString());
//        map.put("username",user.getUsername());
        return new ResponseResult(200,map);
    }

    @Override
    public ResponseResult logout() {
        //因为这个方法 是通过了jwt过滤器执行到这里的 所以SecurityContextHolder上下文是一样的
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //拿到用户id删除redis中的数据
        String userId  = loginUser.getUser().getId().toString();
        redisCache.deleteObject("login:"+userId);
        return new ResponseResult(200,"退出成功");
    }

}

