package com.ecomindo.onboarding.testinghat.dao;

import java.util.List;

import com.ecomindo.onboarding.testinghat.model.HatsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HatsDao extends JpaRepository<HatsModel, Long> {

	HatsModel findById(int id);

    List<HatsModel> findByProductCode(String productCode);

    void deleteById(int id);

    @Query(value="SELECT h FROM HatsModel h "
    + "WHERE LOWER(h.productCode) LIKE LOWER(:searchWords) "
    + "OR LOWER(h.productName) LIKE LOWER(:searchWords) "
    )
	List<HatsModel> findBySearchWords(String searchWords);

    @Modifying
    @Query(value="UPDATE HatsModel h "
    + "SET h.productCode = :productCode, h.productName = :productName "
    + "WHERE h.id = :id "
    )
	void updateHat(int id, String productCode, String productName);

}
