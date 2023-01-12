package com.example.school.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.school.student.Student;
import com.example.school.student.StudentResponseDTO;

public class EntityToDtoComparatorHelper {

  public static void compareStudentEntityToStudentResponseDto(Student student, StudentResponseDTO studentResponseDto) {
    assertEquals(student.getName(), studentResponseDto.getName());
    assertEquals(student.getLastName(), studentResponseDto.getLastName());
    assertEquals(student.getEmail(), studentResponseDto.getEmail());
    assertEquals(student.getDateOfBirth(), studentResponseDto.getDateOfBirth());
    assertEquals(student.getAge(), studentResponseDto.getAge());
  }
}
