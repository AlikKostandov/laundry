package com.students.laundry.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sessionId;

    @Column(name = " session_start_time")
    @DateTimeFormat(pattern = "HH:MM")
    private Date sessionStartTime;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "floor")
    private int floor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_pass_number", nullable = true)
    private User user;

}
