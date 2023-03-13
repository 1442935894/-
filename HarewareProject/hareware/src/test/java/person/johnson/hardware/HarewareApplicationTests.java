package person.johnson.hardware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import person.johnson.hardware.model.SysUser;

import java.util.List;

@SpringBootTest
class HarewareApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encoder = passwordEncoder.encode("123");
        System.out.println(encoder);//$2a$10$ICuGU2taAXDa4qYUMefgpOjc54R69Ww1j/bW0BWFJWqZgz0PF8qVe
    }

    @Test
    public void test2(){
    }
}
