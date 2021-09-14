package com.ecomindo.onboarding.testinghat.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public void upload(MultipartFile file) throws Exception;
    public String getFile(String filename) throws Exception;
    public void download(String filename, String output) throws Exception;
}
