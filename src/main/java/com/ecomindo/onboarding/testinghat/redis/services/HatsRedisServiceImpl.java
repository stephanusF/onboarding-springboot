package com.ecomindo.onboarding.testinghat.redis.services;

import java.util.Optional;

import com.ecomindo.onboarding.testinghat.redis.dao.HatsRedisDao;
import com.ecomindo.onboarding.testinghat.redis.model.HatsRedisModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HatsRedisServiceImpl implements HatsRedisService{

    @Autowired
    HatsRedisDao hatsDao;

    @Override
    public void addHatToRedis(HatsRedisModel hat) {
        hatsDao.save(hat);
    }

    @Override
    public HatsRedisModel getHat(String id) {
        Optional<HatsRedisModel> res = hatsDao.findById(id);
        if(!res.isPresent()){
            return null;
        }
        return res.get();
    }

    @Override
    public void updateHatInRedis(HatsRedisModel hat) {
        hatsDao.save(hat);
        
    }

    @Override
    public void deleteHatInRedis(String id) {
        hatsDao.deleteById(id);
        
    }
    
}
