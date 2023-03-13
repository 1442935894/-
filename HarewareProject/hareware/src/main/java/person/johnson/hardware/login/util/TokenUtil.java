package person.johnson.hardware.login.util;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/13
 */
@Component
@Slf4j
public class TokenUtil {
    private Long userId;
    @Resource
    private HttpServletRequest request;

    public Long getUserIdByToken(){
        String token = request.getHeader("token");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.valueOf(claims.getSubject());
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("信息异常，请尝试重新登录");
        }
    }
}
