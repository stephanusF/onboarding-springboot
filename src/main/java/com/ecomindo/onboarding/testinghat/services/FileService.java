package com.ecomindo.onboarding.testinghat.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileService {
	public void upload(MultipartFile file) throws Exception;
    public List<String> getFileContent(String filename) throws Exception;
    public void download(String filename, String output) throws Exception;
}
