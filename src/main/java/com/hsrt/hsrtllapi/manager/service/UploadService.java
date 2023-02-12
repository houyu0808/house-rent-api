package com.hsrt.hsrtllapi.manager.service;

import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UploadService {
    ResponseMessage uploadFile(String fileType,MultipartFile file);

    ResponseMessage deleteFile(String fileUrl);
}
