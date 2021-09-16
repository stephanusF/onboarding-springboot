package com.ecomindo.onboarding.testinghat.redis.dao;

import com.ecomindo.onboarding.testinghat.redis.model.HatsRedisModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatsRedisDao extends CrudRepository<HatsRedisModel, String> {
    void deleteById(String id);
}