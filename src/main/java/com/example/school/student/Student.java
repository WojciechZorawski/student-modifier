package com.example.school.student;

import com.example.school.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "student")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {

  private String name;
  private String lastName;
  private String email;
  private LocalDate dateOfBirth;
  private int age;
  @Enumerated(EnumType.STRING)
  private Sex sex;

  public StudentResponseDTO toResponseDto() {
    Sex setSex = name.endsWith("a") ? Sex.F : Sex.M;
    return StudentResponseDTO.builder()
                             .name(name)
                             .lastName(lastName)
                             .email(email)
                             .dateOfBirth(dateOfBirth)
                             .age(age)
                             .sex(setSex)
                             .build();
  }
}
