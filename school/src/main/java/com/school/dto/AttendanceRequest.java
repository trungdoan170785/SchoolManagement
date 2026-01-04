package com.school.dto;

import com.school.entity.Attendance;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {

    private Long classId;

    private List<Item> records;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Item {

        private Long studentId;

        private Attendance.Status status;
    }
}
