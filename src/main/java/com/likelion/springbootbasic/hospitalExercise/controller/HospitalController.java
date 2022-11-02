package com.likelion.springbootbasic.hospitalExercise.controller;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.domain.dao.HospitalDao;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @GetMapping({"", "/"})
    public String hello() {
        return "Hello";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id) {
        try {
            Hospital hospital = hospitalDao.findById(id);

            if(hospital == null) {
                return id + "번 아이디를 가진 병원이 없습니다";
            }
            return hospital.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "병원 조회 실패";
        }
    }

    @GetMapping("/count")
    public String getCount() {
        try {
            int count = hospitalDao.getCount();
            return "전체 데이터 개수 : " + count;
        } catch (Exception e) {
            e.printStackTrace();
            return "전체 개수 조회 실패";
        }
    }

    @PostMapping("/all")
    public String insertAll() {
        try {
            List<Hospital> hospitalList = hospitalReadLineContext.readByLine("./hospital_data.csv");
            for(Hospital hospital : hospitalList) {
                hospitalDao.add(hospital);
            }

            return hospitalList.size() + "개 병원 데이터 삽입 성공";

        } catch (Exception e) {
            e.printStackTrace();
            return "전체 삽입 실패";
        }
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        try {
            hospitalDao.deleteAll();
            return "전체 삭제 성공";

        } catch (Exception e) {
            e.printStackTrace();
            return "전체 삭제 실패";
        }
    }
}
