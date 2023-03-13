package person.johnson.hardware.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import person.johnson.hardware.common.result.RestResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * packege: IntelliJ IDEA
 * author: Johnson
 * createTime：2022/10/7
 */
@RestController
@CrossOrigin
@RequestMapping("/picture")
public class PictureController {
    //设置固定日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH：mm：ss");
    private Logger log = LoggerFactory.getLogger("PictureController");

    // 文件上传（用户头像）
    @PostMapping("/user/upload")
    public RestResult fileUploads(@RequestParam("file") MultipartFile file) throws IOException {
        //用户头像
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\data\\user\\";
        if(file.isEmpty()){
            return RestResult.failure("文件为空！");
        }
        String format = sdf.format(new Date());
        String fileName = file.getOriginalFilename();
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            return RestResult.success( "/static/data/user/" + newFileName);
        } catch (IOException e) {
            log.error(e.toString());
            return RestResult.failure(e.getMessage());
        }
        // 待完成 —— 文件类型校验工作
//        return RestResult.failure("上传错误");
    }

    // 文件上传（产品图片）
    @PostMapping("/product/upload")
    public RestResult fileUploadsProduct(@RequestParam("file") MultipartFile file) throws IOException {
        //产品图
        String filePath =System.getProperty("user.dir") + "\\src\\main\\resources\\static\\data\\product\\";
        if(file.isEmpty()){
            return RestResult.failure("文件为空！");
        }
        String format = sdf.format(new Date());
        String fileName = file.getOriginalFilename();
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            return RestResult.success("/static/data/product/" + newFileName);
        } catch (IOException e) {
            log.error(e.toString());
            return RestResult.failure(e.getMessage());
        }
        // 待完成 —— 文件类型校验工作
//        return RestResult.failure("上传错误");
    }
} 
