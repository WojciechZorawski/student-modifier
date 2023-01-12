package com.example.school.student;

import static com.example.school.student.StudentFactoryFaker.getSex;
import static com.example.school.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.school.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.school.student.StudentFactoryFaker.getValidStudentResponseDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentMapperTest {

  @Test
  void shouldMapRequestDtoToEntity() {
    StudentRequestDTO studentRequestDto = getValidStudentRequestDto();
    Student studentEntity = studentRequestDto.toEntity();
    assertEquals(studentRequestDto.getName(), studentEntity.getName());
    assertEquals(studentRequestDto.getLastName(), studentEntity.getLastName());
    assertEquals(studentRequestDto.getEmail(), studentEntity.getEmail());
    assertEquals(studentRequestDto.getDateOfBirth(), studentEntity.getDateOfBirth());
    assertEquals(studentRequestDto.getAge(), studentEntity.getAge());
  }

  @Test
  void shouldMapEntityToResponseDto() {
    Student studentEntity = getValidStudentEntity();
    StudentResponseDTO studentResponseDto = studentEntity.toResponseDto();
    assertEquals(studentEntity.getName(), studentResponseDto.getName());
    assertEquals(studentEntity.getLastName(), studentResponseDto.getLastName());
    assertEquals(studentEntity.getEmail(), studentResponseDto.getEmail());
    assertEquals(studentEntity.getDateOfBirth(), studentResponseDto.getDateOfBirth());
    assertEquals(studentEntity.getAge(), studentResponseDto.getAge());
    assertEquals(getSex(studentEntity.getName()), studentResponseDto.getSex());
  }

  @Test
  void shouldMapResponseDtoToRequestDto() {
    StudentResponseDTO studentResponseDto = getValidStudentResponseDto();
    StudentRequestDTO studentRequestDto = studentResponseDto.toRequestDto();
    assertEquals(studentResponseDto.getName(), studentRequestDto.getName());
    assertEquals(studentResponseDto.getLastName(), studentRequestDto.getLastName());
    assertEquals(studentResponseDto.getEmail(), studentRequestDto.getEmail());
    assertEquals(studentResponseDto.getDateOfBirth(), studentRequestDto.getDateOfBirth());
    assertEquals(studentResponseDto.getAge(), studentRequestDto.getAge());
    assertEquals(getSex(studentResponseDto.getName()), getSex(studentRequestDto.getName()));
  }
}