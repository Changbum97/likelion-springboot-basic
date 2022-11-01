package com.likelion.springbootbasic.hospitalExercise.domain.dao;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
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

    @Test
    @DisplayName("CSV 첫 줄을 DB에 삽입, getCount, deleteAll, findById Test")
    void test() throws IOException {
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine("./hospital_data.csv");
        Hospital hospital1 = hospitalList.get(0);
        hospitalDao.add(hospital1);

        assertEquals(1, hospitalDao.getCount());

        Hospital findHospital = hospitalDao.findById(1);
        assertEquals("효치과의원", findHospital.getHospitalName());
        assertEquals("의원", findHospital.getOpenServiceName());
        assertEquals( LocalDateTime.of(1999, 6, 12, 0, 0, 0),
                findHospital.getLicenseDate());
        assertEquals(52.29f, findHospital.getTotalAreaSize());

        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
    }

}