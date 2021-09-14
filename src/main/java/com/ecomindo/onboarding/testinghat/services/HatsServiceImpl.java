package com.ecomindo.onboarding.testinghat.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

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
    public List<HatsModel> getAllHats() {
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
        //List<HatsModel> res = hatsDao.findBySearchWords("%"+searchWords+"%");

        List<HatsModel> res = hatsDao.findAll().stream()
            .filter(x -> x.getProductCode().toLowerCase().contains(searchWords.toLowerCase()) || x.getProductName().toLowerCase().contains(searchWords.toLowerCase())).collect(toList());

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

    @Override
    public void updateHat2(HatsModel model, HatDTO dto) {
        model.setProductCode(dto.getProductCode());
        model.setProductName(dto.getProductName());

        hatsDao.save(model);
    }

    @Override
    public void addHatFromFileContent(String content) {
        String denominator = ",";
        List<String> lines = Arrays.asList(content.split("\n"));
        for(String items : lines){
            String productCode = items.split(denominator)[0];
            String productName = items.split(denominator)[1];
            hatsDao.save(new HatsModel(productCode,productName));
        }
    }   
}
