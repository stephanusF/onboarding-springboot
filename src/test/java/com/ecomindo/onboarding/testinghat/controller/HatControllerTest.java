package com.ecomindo.onboarding.testinghat.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import com.ecomindo.onboarding.testinghat.dto.HatDTO;
import com.ecomindo.onboarding.testinghat.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testinghat.model.HatsModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @DataJpaTest
public class HatControllerTest {
    
    @LocalServerPort
    private int port;

    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init() {
    	// given
        url = String.format("http://localhost:%d/hat/add", port);
        HatDTO request = new HatDTO();
        request.setProductCode("hat-00000x");
        request.setProductName("Hat test X");

    	this.restTemplate.postForEntity(url, request, ResultMsgDTO.class);
    }


    @Test
    public void test() {
    	url = String.format("http://localhost:%d/hat/test", port);

    	ResponseEntity<ResultMsgDTO> res = this.restTemplate.getForEntity(url, ResultMsgDTO.class);
        ResultMsgDTO body = res.getBody(); 
    	assertThat(res.getStatusCode().equals(HttpStatus.OK));
        assertThat(body.getMessage().equals("OK"));
    }

    @Test
    public void test_getAll() {
    	url = String.format("http://localhost:%d/hat/getAll", port);

    	ResponseEntity<?> res = this.restTemplate.getForEntity(url, List.class);
        List<HatsModel> body = (List<HatsModel>) res.getBody();
    	assertThat(res.getStatusCode().equals(HttpStatus.OK));
        assertThat(body.size()).isEqualTo(1);
        // assertThat(body.get(0).getProductCode().equals("hat-00000x"));

    }

    @Test
    public void test_add() {
    	url = String.format("http://localhost:%d/hat/add", port);
        HatDTO request = new HatDTO();
        request.setProductCode("hat-00000y");
        request.setProductName("Hat test Y");

    	ResponseEntity<ResultMsgDTO> res = this.restTemplate.postForEntity(url, request, ResultMsgDTO.class);
        ResultMsgDTO body = res.getBody(); 
    	assertThat(res.getStatusCode().equals(HttpStatus.OK));
        assertThat(body.getMessage().equals("Successfuly adding new hat"));
    }

    @Test
    public void test_update() {
    	url = String.format("http://localhost:%d/hat/update/1", port);
        HatDTO request = new HatDTO();
        request.setProductCode("hat-00000y");
        request.setProductName("Hat test Y");

    	ResponseEntity<ResultMsgDTO> res = this.restTemplate.postForEntity(url, request, ResultMsgDTO.class);
        ResultMsgDTO body = res.getBody(); 
    	assertThat(res.getStatusCode().equals(HttpStatus.OK));
        assertThat(body.getMessage().equals("Successfuly updating hat with id 1"));

    }
}
