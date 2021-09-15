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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

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


			List<String> fileRes = fileService.getFileContent(fileName);
            hatsService.addHatFromFileContent(fileRes);

            res.setMessage("Successfuly adding new hat");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/readFile2", method = RequestMethod.GET)
	public ResponseEntity<?> readFile2(@RequestParam String fileName) {
        ResultMsgDTO res = new ResultMsgDTO();
		try {

			List<String> fileRes = fileService.getFileContent(fileName);

			Future<Void> first = hatsService.addHatFromFileContent2(new ArrayList<>(fileRes.subList(0, (fileRes.size()) / 2)));
            Future<Void> second = hatsService.addHatFromFileContent2(new ArrayList<>(fileRes.subList((fileRes.size()) / 2, fileRes.size())));
            
            while (!(first.isDone() && second.isDone())) {
                if(!first.isDone()){
                    System.out.println("Add Hats From File Job first batch is running...");
                }

                if(!second.isDone()){
                    System.out.println("Add Hats From File Job second batch is running...");
                }
			    
			    Thread.sleep(500);
			}

			
            // hatsService.addHatFromFileContent(fileRes);

            res.setMessage("Successfuly adding new hat");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

}
