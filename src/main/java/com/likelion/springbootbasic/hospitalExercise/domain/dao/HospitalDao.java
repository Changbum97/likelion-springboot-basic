package com.likelion.springbootbasic.hospitalExercise.domain.dao;

import com.likelion.springbootbasic.hospitalExercise.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Hospital hospital) {
        String sql = "INSERT INTO nation_wide_hospitals(id, open_service_name, open_local_government_code," +
                "management_number, license_date, business_status, business_status_code, phone," +
                "full_address, road_name_address, hospital_name, business_type_name," +
                "healthcare_provider_count, patient_room_count, total_number_of_beds, total_area_size)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";  // 16개

        jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize());
    }

    public int getCount() {
        String sql = "SELECT COUNT(*) FROM nation_wide_hospitals;";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void deleteAll() {
        String sql = "DELETE FROM nation_wide_hospitals";
        this.jdbcTemplate.update(sql);
    }

    /*RowMapper<Hospital> rowMapper = new RowMapper<Hospital>() {
        @Override
        public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }
    };*/

    // RowMapper을 lambda로 생성
    RowMapper<Hospital> rowMapper = (rs, rowNum) -> {
        Hospital hospital = new Hospital();
        hospital.setId(rs.getInt("id"));
        hospital.setHospitalName(rs.getString("hospital_name"));
        hospital.setOpenServiceName(rs.getString("open_service_name"));
        hospital.setLicenseDate(rs.getTimestamp("license_date").toLocalDateTime());
        return hospital;
    };
    public Hospital findById(int id) {
        String sql = "SELECT * FROM nation_wide_hospitals WHERE id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}