package com.ecomindo.onboarding.testinghat.services;

import java.util.ArrayList;
import java.util.List;

import com.ecomindo.onboarding.testinghat.dao.HatsDao;
import com.ecomindo.onboarding.testinghat.dto.HatDTO;
import com.ecomindo.onboarding.testinghat.model.HatsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HatsServiceImpl implements HatsService {

    @Autowired
	HatsDao hatsDao;

    @Override
    public List<HatsModel> getAllBooks() {
        List<HatsModel> res = new ArrayList<>();
        hatsDao.findAll().forEach(res::add);
        
        return res;
    }

    @Override
    public HatsModel getHatById(int id) {
        HatsModel res = hatsDao.findById(id);

        return res;
    }

    @Override
    public List<HatsModel> getHatsBySearchWords(String searchWords) {
        List<HatsModel> res = hatsDao.findBySearchWords("%"+searchWords+"%");

        return res;
    }

    @Override
    public void addHat(HatDTO dto) {
        hatsDao.save(new HatsModel(dto.getProductCode(), dto.getProductName()));
        
    }

    @Override
    public boolean isProductCodeExist(String productCode) {
        List<HatsModel> list = hatsDao.findByProductCode(productCode);
        if(list.size()>0){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void updateHat(int id, HatDTO dto) {
        hatsDao.updateHat(id, dto.getProductCode(), dto.getProductName());
        
    }

    @Transactional
    @Override
    public void deleteHat(int id) {
        hatsDao.deleteById(id);
        
    }

    
    
}
