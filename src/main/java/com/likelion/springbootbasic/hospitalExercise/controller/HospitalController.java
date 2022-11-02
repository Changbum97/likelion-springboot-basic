package com.likelion.springbootbasic.hospitalExercise.controller;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.domain.dao.HospitalDao;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        try {
            Hospital hospital = hospitalDao.findById(id);

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(hospital);

        } catch (EmptyResultDataAccessException e) {
            // 106271번 id 없음 => 404 에러 (Not Found)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(id + "번 아이디를 가진 병원이 없습니다.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
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
