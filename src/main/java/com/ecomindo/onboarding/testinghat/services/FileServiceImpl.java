package com.ecomindo.onboarding.testinghat.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Base64;

import com.ecomindo.onboarding.testinghat.config.Config;
import com.ecomindo.onboarding.testinghat.utils.SftpUtil;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	Config config;

	@Override
	public void upload(MultipartFile file) throws Exception {
		try {
			SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

			String filename = file.getOriginalFilename();
			String fileType = file.getContentType();
			byte[] fileContent = file.getBytes();

			String sftpPath = config.getSftpFolder().concat("/").concat(filename);
			sftp.sftpPutFromStream(file.getInputStream(), sftpPath);

		} catch (Exception e) {
			throw e;
		}
	}

    @Override
    public String getFile(String filename) throws Exception {
        SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

        String sftpPath = config.getSftpFolder().concat("/").concat(filename);
        String base64 = sftp.sftpGetFile(sftpPath);
        // byte[] bytes = IOUtils.toByteArray(getFile);
        // String base64 = Base64.getEncoder().encodeToString(bytes);

        return base64;
    }

    @Override
    public void download(String filename, String output) throws Exception {
        
        SftpUtil sftp = new SftpUtil(config.getSftpHost(), config.getSftpPort(), config.getSftpUsername(),
					config.getSftpPassword(), config.getSftpFolder());

        String sftpPath = config.getSftpFolder().concat("/").concat(filename);
        sftp.sftpGetToFile(sftpPath, output); 

        // TODO Auto-generated method stub
        
    }
}
