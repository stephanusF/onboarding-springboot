package com.ecomindo.onboarding.testinghat.controller;

import java.util.List;

import com.ecomindo.onboarding.testinghat.dto.HatDTO;
import com.ecomindo.onboarding.testinghat.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testinghat.model.HatsModel;
import com.ecomindo.onboarding.testinghat.services.HatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hat")
public class HatController {

    @Autowired
    HatsService hatsService;
	
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public ResponseEntity<?> test() {
		try {
			ResultMsgDTO res = new ResultMsgDTO("OK");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/getAll", method=RequestMethod.GET)
	public ResponseEntity<?> getAllHats() {
		try {
			List<HatsModel> res = hatsService.getAllHats();

			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getHatById(@PathVariable int id) {
		try {
			HatsModel res = hatsService.getHatById(id);
            
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/get", method=RequestMethod.GET)
	public ResponseEntity<?> getHatsBySearchwords(@RequestParam String searchWords) {
		try {
			List<HatsModel> res = hatsService.getHatsBySearchWords(searchWords);
            
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/add", method=RequestMethod.POST)
	public ResponseEntity<?> addHat(@RequestBody HatDTO dto) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            if(dto.getProductCode()==null||dto.getProductCode().isEmpty()){
                res.setMessage("Product code cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            boolean isExist = hatsService.isProductCodeExist(dto.getProductCode());
            if(isExist == true){
                res.setMessage("Product code "+dto.getProductCode()+" is already exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(dto.getProductName()==null||dto.getProductName().isEmpty()){
                res.setMessage("Product name cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			hatsService.addHat(dto);
            res.setMessage("Successfuly adding new hat");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/update/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> updateHat(@PathVariable int id, @RequestBody HatDTO dto) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            HatsModel hat = hatsService.getHatById(id);
            if(hat == null){
                res.setMessage("Hat with id "+id+" is not exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(dto.getProductCode()==null||dto.getProductCode().isEmpty()){
                res.setMessage("Product code cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            boolean isExist = hatsService.isProductCodeExist(dto.getProductCode());
            if(isExist == true){
                res.setMessage("Product code "+dto.getProductCode()+" is already exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(dto.getProductName()==null||dto.getProductName().isEmpty()){
                res.setMessage("Product name cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			//hatsService.updateHat(id, dto);
            hatsService.updateHat2(hat, dto);
            res.setMessage("Successfuly updating hat with id "+id);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> deleteHat(@PathVariable int id) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            HatsModel hat = hatsService.getHatById(id);
            if(hat == null){
                res.setMessage("Hat with id "+id+" is not exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			hatsService.deleteHat(id);
            res.setMessage("Successfuly deleting hat with id "+id);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
