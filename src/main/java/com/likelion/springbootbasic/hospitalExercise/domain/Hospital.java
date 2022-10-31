package com.likelion.springbootbasic.hospitalExercise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Hospital {
    private int id;     // 번호(PK)
    private String openServiceName;         // 개방 서비스명
    private int openLocalGovernmentCode;    // 개방 자치 단체 코드
    private String managementNumber;        // 관리 번호
    private LocalDateTime licenseDate;      // 인/허가 일자
    private int businessStatus;             // 영업 상태 구분 => 1:영업, 2:휴업, 3:폐업, 4:취소/말소
    private int businessStatusCode;         // 상세 영업 상태 코드 => 13:영업중, 2:휴업, 3:폐업
    private String phone;                   // 소재지 전화 번호
    private String fullAddress;             // 소재지 전체 주소
    private String roadNameAddress;         // 도로명 전체 주소
    private String hospitalName;            // 사업장 이름
    private String businessTypeName;        // 업태 구분명
    private int healthcareProviderCount;    // 의료인
    private int patientRoomCount;           // 입원실 수
    private int totalNumberOfBeds;          // 병상 수
    private float totalAreaSize;            // 총 면적







}
