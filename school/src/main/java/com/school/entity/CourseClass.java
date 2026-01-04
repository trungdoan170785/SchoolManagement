package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "classes")
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classCode;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy="courseClass")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy="courseClass")
    private List<Attendance> attendanceList;
}
