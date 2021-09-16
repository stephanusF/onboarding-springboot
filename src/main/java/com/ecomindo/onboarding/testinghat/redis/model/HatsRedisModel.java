package com.ecomindo.onboarding.testinghat.redis.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "Hats", timeToLive = 3000)
public class HatsRedisModel implements Serializable {
	private String id;
	private String productCode;
	private String productName;

    public HatsRedisModel(){
        
    }

    public HatsRedisModel(String id, String productCode, String productName){
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

	
	
}
