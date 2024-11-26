//package com.minijean.healthmer.util;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class VerificationToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String token;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private LocalDateTime expiryDate;
//
//    // getters and setters
//}
