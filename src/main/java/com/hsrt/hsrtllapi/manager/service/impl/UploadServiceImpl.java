package com.hsrt.hsrtllapi.manager.service.impl;

import com.hsrt.hsrtllapi.manager.service.UploadService;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.UUID;


@Transactional
@Service
public class UploadServiceImpl implements UploadService {
    @Value("${oss.local.upload-file-path}")
    private String uploadFilePath;
    @Value("${path.uploadPath}")
    private String uploadPath;
    final String[] fileType = new String[]{"image","video","docx","excel"};

    @Override
    public ResponseMessage uploadFile(String fileName, MultipartFile file) {
        //便利检索当前文件类型是否在规定的数组中
        boolean flag = false;
        for(String type:fileType){
            if (type.equals(fileName)) {
                flag = true;
                break;
            }
        }
        //文件类型存在 文件存储操作
        if(flag){
            //获取文件原来的名字
            String name = file.getOriginalFilename();
            //使用UUID生成新的文件名字确保不会重复
            String newName = UUID.randomUUID().toString() + name.substring(name.lastIndexOf("."), name.length());
            //文件保存路径
            File dest = new File(uploadFilePath +  "/" + fileName + "/" + newName);
            try{
                //文件保存至静态地址
                file.transferTo(dest);
                //映射出可以直接访问的地址
                String fileUrl = uploadPath + "/" + fileName + "/" + newName;
                return ResponseMessage.ok(fileUrl);
            }catch (Exception e){
                System.out.println(e.toString());
                return ResponseMessage.error(400,"上传失败");
            }
        }else{
            return ResponseMessage.error(400,"请选择合适的文件类型");
        }
    }

    @Override
    public ResponseMessage deleteFile(String fileUrl) {
        //映射路径替换为静态资源路径
        String file = fileUrl.replace(uploadPath,uploadFilePath);
        File existFile = new File(file);
        //判断文件是否存在
        if(existFile.exists()){
            //删除文件
            System.out.println(existFile.delete());
            return ResponseMessage.ok("删除成功");
        }else{
            return ResponseMessage.error(400,"文件不存在，删除失败");
        }
    }

}
