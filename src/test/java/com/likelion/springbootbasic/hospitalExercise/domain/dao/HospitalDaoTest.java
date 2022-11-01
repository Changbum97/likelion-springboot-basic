package com.likelion.springbootbasic.hospitalExercise.domain.dao;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.parser.HospitalParser;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalDaoTest {

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @AfterEach
    void afterTest() {
        hospitalDao.deleteAll();
    }

    @Test
    @DisplayName("add, getCount, deleteAll, findById Test")
    void functionTest() {
        String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\"";
        HospitalParser hp = new HospitalParser();

        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);

        // add, getCount Test
        assertEquals(1, hospitalDao.getCount());

        // findById Test
        Hospital findHospital = hospitalDao.findById(1);
        assertEquals("효치과의원", findHospital.getHospitalName());
        assertEquals("의원", findHospital.getOpenServiceName());
        assertEquals( LocalDateTime.of(1999, 6, 12, 0, 0, 0),
                findHospital.getLicenseDate());
        assertEquals(52.29f, findHospital.getTotalAreaSize());

        // deleteAll Test
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
    }

    @Test
    @DisplayName("모든 데이터가 DB에 잘 삽입되는지 Test")
    public void dbTest() throws IOException {
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine("./hospital_data.csv");
        for(Hospital hospital : hospitalList) {
            hospitalDao.add(hospital);
        }

        assertEquals(hospitalList.size(), hospitalDao.getCount());

        System.out.println("삽입 개수 : " + hospitalDao.getCount());
    }
}