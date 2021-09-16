package com.ecomindo.onboarding.testinghat.controller;

import com.ecomindo.onboarding.testinghat.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testinghat.redis.model.HatsRedisModel;
import com.ecomindo.onboarding.testinghat.redis.services.HatsRedisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    HatsRedisService hatsRedisService;


    @RequestMapping(value = "/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getHatById(@PathVariable String id) {
		try {
			ResultMsgDTO msg = new ResultMsgDTO();
            HatsRedisModel res = hatsRedisService.getHat(id);
            if(res == null){
                msg.setMessage("Data not found in redis");
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
            
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/add", method=RequestMethod.POST)
	public ResponseEntity<?> addHat(@RequestBody HatsRedisModel body) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            if(body.getId()==null||body.getId().isEmpty()){
                res.setMessage("Id cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(body.getProductCode()==null||body.getProductCode().isEmpty()){
                res.setMessage("Product code cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(body.getProductName()==null||body.getProductName().isEmpty()){
                res.setMessage("Product name cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			hatsRedisService.addHatToRedis(body);
            res.setMessage("Successfuly adding new hat");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/update", method=RequestMethod.POST)
	public ResponseEntity<?> updateHat(@RequestBody HatsRedisModel body) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            HatsRedisModel existing = hatsRedisService.getHat(body.getId());
            if(existing == null){
                res.setMessage("Data not found in redis");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(body.getId()==null||body.getId().isEmpty()){
                res.setMessage("Id cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(body.getProductCode()==null||body.getProductCode().isEmpty()){
                res.setMessage("Product code cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

            if(body.getProductName()==null||body.getProductName().isEmpty()){
                res.setMessage("Product name cannot be empty");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			hatsRedisService.updateHatInRedis(body);
            res.setMessage("Successfuly updating hat with id "+body.getId());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> deleteHat(@PathVariable String id) {
		try {
            ResultMsgDTO res = new ResultMsgDTO();

            HatsRedisModel existing = hatsRedisService.getHat(id);
            if(existing == null){
                res.setMessage("Hat with id "+id+" is not exists");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }

			hatsRedisService.deleteHatInRedis(id);
            res.setMessage("Successfuly deleting hat with id "+id);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
