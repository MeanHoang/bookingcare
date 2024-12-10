package com.example.bookingcare.service.user;

import java.sql.Date;
import java.util.List;

import com.example.bookingcare.model.ScheduleInfo;

public interface ScheduleInfoService {

	List<ScheduleInfo> getScheduleInfoByDoctorAndDay(int doctorId, Date day);

}
