package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author hewei
 * @date 2019/9/10 - 23:01
 */
@Service
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    public static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }
        //校验文件内容
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }
            //保存到服务器
            file.transferTo(new File("E:\\upload\\images\\" + originalFilename));

            //返回url，进行回显
            return "http://image.leyou.com/" + originalFilename;


            //保存到FastDFS服务器
//            String ext = StringUtils.substringAfterLast(originalFilename, ".");
//            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

//            System.out.println(storePath.getFullPath());
//            group1/M00/00/00/wKg4ZVsWl5eAdLNZAABAhya2V0c424.jpg   //这个就是文件的id
//            反正是静态文件，通过地址直接访问就好，不用下载了。


//            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：" + originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
