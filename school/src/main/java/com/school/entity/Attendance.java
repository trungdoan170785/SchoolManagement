package com.school.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private CourseClass courseClass;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    public enum Status { PRESENT, ABSENT, LATE, EXCUSED }
}
