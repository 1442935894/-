package person.johnson.hardware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/7
 */
@Configuration
public class PictureConfig implements WebMvcConfigurer {
    //得到classpath路径，resource目录下的所有路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";
        registry
                .addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
                .addResourceLocations("file:"+path);
    }
}
