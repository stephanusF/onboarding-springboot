package com.ecomindo.onboarding.testinghat.services;

import java.util.List;

import com.ecomindo.onboarding.testinghat.dto.HatDTO;
import com.ecomindo.onboarding.testinghat.model.HatsModel;

public interface HatsService {
    List<HatsModel> getAllHats();
    HatsModel getHatById(int id);
    List<HatsModel> getHatsBySearchWords(String searchWords);
    void addHat(HatDTO dto);
    boolean isProductCodeExist(String productCode);
    void updateHat(int id, HatDTO dto);
    void updateHat2(HatsModel model, HatDTO dto);
    void deleteHat(int id);
}
