package com.mocktpo.web.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadVo {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
