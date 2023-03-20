package com.project.Backendapi.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_KEYWORD")
@Entity
public class UserKeywordEntity extends BaseTimeEntity {
    @Id
    private String keyword;
    @Column(nullable = false)
    private Integer cnt;
}
