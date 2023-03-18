package com.project.Backendapi.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Table(name = "USER_KEYWORD")
@Data
@Entity
public class UserKeyword {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long seq;
    @Id
    private String keyword;
    @Column(nullable = false)
    private Integer cnt;
    @Column(nullable = false)
    private LocalDateTime fstRegDtmd;
    @Column(nullable = false)
    private LocalDateTime lastChgDtmd;
}
