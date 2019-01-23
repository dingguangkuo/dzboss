package com.smartdz.dzboss.controller;

import com.smartdz.dzboss.domain.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
    public BaseResponse uploadFile(@RequestParam String fileName,
                                   @RequestParam(value = "file", required = false) MultipartFile file,
                                   HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("resources/upload");
        File filePath = new File(path);
        System.out.println("文件保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            boolean mkdir = filePath.mkdir();
            System.out.println("新建上传文件目录：" + mkdir);
        }
        // 获取原始文件名称
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);
        // 获取文件类型，以最后一个`.`作为标识
        String type = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".") + 1) : "";
        System.out.println("文件类型：" + type);
        // 设置文件新名字
        String fileName2 = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称：" + fileName2);
        // 在指定路径创建一个文件
        File targetFile = new File(path, fileName2);
        // 将文件保存到服务器指定位置
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }
        return new BaseResponse();
    }
}
