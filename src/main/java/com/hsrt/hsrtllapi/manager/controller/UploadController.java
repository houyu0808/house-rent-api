package com.hsrt.hsrtllapi.manager.controller;

import com.hsrt.hsrtllapi.manager.service.UploadService;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileUpload")
@Api(tags = "文件上传和删除接口")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/uploadFile")
    public ResponseMessage uploadFile(@RequestParam String fileName,@RequestBody MultipartFile file){
        return uploadService.uploadFile(fileName,file);
    }

    @ApiOperation(value = "文件删除")
    @PostMapping("/deleteFile")
    public ResponseMessage deleteFile(@RequestParam String fileUrl){
        return uploadService.deleteFile(fileUrl);
    }

}
