package com.ecomindo.onboarding.testinghat.redis.services;

import com.ecomindo.onboarding.testinghat.redis.model.HatsRedisModel;

public interface HatsRedisService {
    void addHatToRedis(HatsRedisModel hat);
    HatsRedisModel getHat(String id);
    void updateHatInRedis(HatsRedisModel hat);
    void deleteHatInRedis(String id);
}
