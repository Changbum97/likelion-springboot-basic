package com.likelion.springbootbasic.hospitalExercise.domain.dao;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalDaoTest {

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;


    @Test
    @DisplayName("CSV 첫 줄을 DB에 삽입하는 Test")
    void addTest() throws IOException {
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine("./hospital_data.csv");
        Hospital hospital1 = hospitalList.get(0);
        System.out.println(hospital1.getHospitalName());

        hospitalDao.add(hospital1);
    }
}