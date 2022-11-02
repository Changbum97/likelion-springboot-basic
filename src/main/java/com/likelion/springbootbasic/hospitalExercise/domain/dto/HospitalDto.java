package com.likelion.springbootbasic.hospitalExercise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalDto {

    private String hospitalName;            // 병원 이름
    private String fullAddress;             // 전체 주소
    private String roadNameAddress;         // 도로명 주소
    private int healthcareProviderCount;    // 의료인 수
    private int totalNumberOfBeds;          // 병상 수
    private float totalAreaSize;            // 총 면적
    private String businessStatusCode;      // 상세 영업 상태 코드 => 13:영업중, 2:휴업, 3:폐업 (int -> String)
}
