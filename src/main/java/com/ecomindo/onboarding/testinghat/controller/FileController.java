package com.ecomindo.onboarding.testinghat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomindo.onboarding.testinghat.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testinghat.services.FileService;
import com.ecomindo.onboarding.testinghat.services.HatsService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;

    @Autowired
	HatsService hatsService;

//	@ApiParam(allowMultiple=true) 
	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> upload(@RequestPart(value = "file") MultipartFile file) {
		ResultMsgDTO res = new ResultMsgDTO();
		try {
			fileService.upload(file);

            res.setMessage("Successfuly uploading file");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(path = "/readFile", method = RequestMethod.GET)
	public ResponseEntity<?> readFile(@RequestParam String fileName) {
        ResultMsgDTO res = new ResultMsgDTO();
		try {
			String fileRes = fileService.getFileContent(fileName);
            hatsService.addHatFromFileContent(fileRes);

            res.setMessage("Successfuly adding new hat");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
