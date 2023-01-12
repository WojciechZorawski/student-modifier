package com.example.school.student;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

  private String name;
  private String lastName;
  @Email
  private String email;
  @Past
  private LocalDate dateOfBirth;
  @Min(18)
  @Max(40)
  private int age;

  public Student toEntity() {
    Sex setSex = name.endsWith("a") ? Sex.F : Sex.M;
    return Student.builder()
                  .name(name)
                  .lastName(lastName)
                  .email(email)
                  .dateOfBirth(dateOfBirth)
                  .age(age)
                  .sex(setSex)
                  .build();
  }
}

