package com.students.laundry.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sessionId;

    @Column(name = " session_start_time")
    private String sessionStartTime;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "floor")
    private int floor;

//    @ManyToOne
//    @JoinColumn(name = "user_pass_number")
//    private User user;
}
