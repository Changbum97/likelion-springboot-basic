package com.likelion.springbootbasic.hospitalExercise.controller;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import com.likelion.springbootbasic.hospitalExercise.domain.dao.HospitalDao;
import com.likelion.springbootbasic.hospitalExercise.domain.dto.HospitalDto;
import com.likelion.springbootbasic.hospitalExercise.parser.ReadLineContext;
import com.likelion.springbootbasic.hospitalExercise.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    public String convertBusinessStatusCode(int code) {
        //13:영업중, 2:휴업, 3:폐업
        if(code == 13) {
            return "영업중";
        } else if(code == 2) {
            return "휴업";
        } else if(code == 3) {
            return "폐업";
        } else {
            return "기타";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        try {
            Hospital hospital = hospitalDao.findById(id);

            HospitalDto hospitalDto = new HospitalDto(hospital.getHospitalName(),
                    hospital.getFullAddress(), hospital.getRoadNameAddress(),
                    hospital.getHealthcareProviderCount(), hospital.getTotalNumberOfBeds(),
                    hospital.getTotalAreaSize(), convertBusinessStatusCode(hospital.getBusinessStatusCode()));

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(hospitalDto);

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
            int cnt = hospitalService.insertLargeVolumeHospitalData("./hospital_data.csv");

            return cnt + "개 병원 데이터 삽입 성공";

        } catch (Exception e) {
            e.printStackTrace();
            return "전체 삽입 실패";
        }
    }

    // Hospital 1개만 따로 입력받아 추가
    @PostMapping("/")
    public String insert(@RequestBody Hospital hospital) {
        try {
            // id는 사용자가 모르기 때문에 직접 생성해서 넣어줌
            int id = hospitalDao.getMaxId() + 1;
            hospital.setId(id);
            hospitalDao.add(hospital);

            return id + "번 Hospital 추가 완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "Hospital 추가 실패";
        }
    }
    // { "businessStatus": 1,, "businessStatusCode": 13, "businessTypeName": "치과의원", "fullAddress": "주소1", "healthcareProviderCount": 10, "hospitalName": "치과1", "managementNumber": "mm1", "openLocalGovernmentCode": 3620000, "openServiceName": "의원", "patientRoomCount": 1, "phone": "010-1111-1111", "roadNameAddress": "도로명주소1", "totalAreaSize": 10.1, "totalNumberOfBeds": 5 }
    // { "businessStatus": 1,, "businessStatusCode": 13, "businessTypeName": "치과의원", "fullAddress": "주소2", "healthcareProviderCount": 7, "hospitalName": "치과2", "managementNumber": "mm2", "openLocalGovernmentCode": 3620000, "openServiceName": "의원", "patientRoomCount": 2, "phone": "010-2222-2222", "roadNameAddress": "도로명주소2", "totalAreaSize": 74.0, "totalNumberOfBeds": 1 }
    // { "businessStatus": 1,, "businessStatusCode": 13, "businessTypeName": "치과의원", "fullAddress": "주소2", "healthcareProviderCount": 3, "hospitalName": "치과3", "managementNumber": "mm3", "openLocalGovernmentCode": 3620000, "openServiceName": "의원", "patientRoomCount": 0, "phone": "010-3333-3333", "roadNameAddress": "도로명주소3", "totalAreaSize": 8.8, "totalNumberOfBeds": 0 }

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
