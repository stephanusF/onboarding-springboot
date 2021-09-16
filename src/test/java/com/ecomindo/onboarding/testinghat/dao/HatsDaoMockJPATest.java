package com.ecomindo.onboarding.testinghat.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.PersistenceContext;

import com.ecomindo.onboarding.testinghat.model.HatsModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class HatsDaoMockJPATest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HatsDao hatsDao;

    // @BeforeAll
    @BeforeEach
    public void init() {
    	// given
    	HatsModel hat1 = new HatsModel("hat-00000x","Hat test X");
    	entityManager.persist(hat1);
    	
    	HatsModel hat2 = new HatsModel("hat-00000y","Hat test Y");
        entityManager.persist(hat2);
        
        entityManager.flush();
    }

    @Test
    public void test_findById() {        
        // when
        HatsModel found = hatsDao.findById(2);

        // then
        assertThat(found.getProductCode()).isEqualTo("hat-00000y");
        assertThat(found.getProductName()).isEqualTo("Hat test Y");
    }

    @Test
    public void test_findByProductCode() {        
        // when
        List<HatsModel> found = hatsDao.findByProductCode("hat-00000x");

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getProductName()).isEqualTo("Hat test X");
    }

    // @Test
    // public void test_deleteById() {        
    //     // when
    //     hatsDao.deleteById(1);
    //     List<HatsModel> found = hatsDao.findAll();

    //     // then
    //     assertThat(found.size()).isEqualTo(1);
    //     assertThat(found.get(0).getProductCode()).isEqualTo("hat-00000y");
    //     assertThat(found.get(0).getProductName()).isEqualTo("Hat test Y");
    // }

    @Test
    public void test_findBySearchWords() {        
        // when
        List<HatsModel> found = hatsDao.findBySearchWords("%00Y%");

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getProductCode()).isEqualTo("hat-00000y");
        assertThat(found.get(0).getProductName()).isEqualTo("Hat test Y");
    }

    // @Test
    // public void test_update() {        
    //     // when
    //     hatsDao.updateHat(1, "hat-00000z", "Hat test Z");
    //     hatsDao.flush();
    //     HatsModel found = hatsDao.findById(1);

    //     // then
    //     //assertThat(found.size()).isEqualTo(1);
    //     assertThat(found.getProductCode()).isEqualTo("hat-00000z");
    //     assertThat(found.getProductName()).isEqualTo("Hat test Z");
    // }
    
}
