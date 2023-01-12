package com.example.school.student;

import static com.example.school.student.EntityToDtoComparatorHelper.compareStudentEntityToStudentResponseDto;
import static com.example.school.student.StudentFactoryFaker.getSex;
import static com.example.school.student.StudentFactoryFaker.getValidStudentEntity;
import static com.example.school.student.StudentFactoryFaker.getValidStudentRequestDto;
import static com.example.school.student.StudentFactoryFaker.toEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @InjectMocks
  private StudentService studentService;
  @Mock
  private StudentRepository studentRepository;

  private static Stream<Arguments> provideNameAndLastNameReturnsFullName() {
    Student student = getValidStudentEntity();
    String name = student.getName();
    String lastName = student.getLastName();
    String expectedResult1 = " " + lastName;
    String expectedResult2 = name + " ";
    String expectedResult3 = name + " " + lastName;
    return Stream.of(
        Arguments.of(null, null, " "),
        Arguments.of(null, lastName, expectedResult1),
        Arguments.of(name, null, expectedResult2),
        Arguments.of(name, lastName, expectedResult3)
    );
  }

  @Test
  void getStudentByIdReturnsProperStudent() {
    studentService = new StudentService(studentRepository);
    Student student = getValidStudentEntity();
    Long id = student.getId();

    when(studentRepository.findById(id)).thenReturn(Optional.of(student));

    StudentResponseDTO studentResponseDto = studentService.getStudentById(id);
    compareStudentEntityToStudentResponseDto(student, studentResponseDto);
  }

  @Test
  void getAllStudentsReturnsProperStudentList() {
    List<Student> entityList = List.of(getValidStudentEntity());

    when(studentRepository.findAll()).thenReturn(entityList);

    List<StudentResponseDTO> dtoList = studentService.getAllStudents();
    compareStudentEntityToStudentResponseDto(entityList.get(0), dtoList.get(0));
  }

  @Test
  void createStudentReturnsProperStudent() {
    StudentRequestDTO requestDto = getValidStudentRequestDto();

    when(studentRepository.save(any(Student.class))).thenReturn(toEntity(requestDto));

    StudentResponseDTO createdStudent = studentService.createStudent(requestDto);
    compareStudentEntityToStudentResponseDto(toEntity(requestDto), createdStudent);
  }

  @Test
  void createStudentEntityReturnsProperStudent() {
    Student studentEntity = getValidStudentEntity();

    when(studentRepository.save(any(Student.class))).thenReturn(studentEntity);

    Student createdStudent = studentService.createStudentEntity(studentEntity);
    assertEquals(studentEntity.getName(), createdStudent.getName());
    assertEquals(studentEntity.getLastName(), createdStudent.getLastName());
    assertEquals(studentEntity.getEmail(), createdStudent.getEmail());
    assertEquals(studentEntity.getDateOfBirth(), createdStudent.getDateOfBirth());
    assertEquals(studentEntity.getAge(), createdStudent.getAge());
    assertEquals(getSex(studentEntity.getName()), createdStudent.getSex());
  }

  @Test
  void updateStudentReturnsProperStudent() {
    Student entity = getValidStudentEntity();
    Long id = entity.getId();
    String name = "Anastasia";
    String email = "anastasia.paczkowska@hotmail.pl";

    when(studentRepository.findById(id)).thenReturn(Optional.of(entity));
    when(studentRepository.save(any(Student.class))).thenReturn(entity);

    StudentResponseDTO updatedStudent = studentService.updateStudent(id, name, email);
    assertEquals(name, updatedStudent.getName());
    assertEquals(entity.getLastName(), updatedStudent.getLastName());
    assertEquals(email, updatedStudent.getEmail());
    assertEquals(entity.getDateOfBirth(), updatedStudent.getDateOfBirth());
    assertEquals(entity.getAge(), updatedStudent.getAge());
    assertEquals(getSex(name), updatedStudent.getSex());
  }

  @Test
  void deleteStudent() {
    Student studentEntity = getValidStudentEntity();
    Long id = studentEntity.getId();
    studentService.deleteStudent(id);
    verify(studentRepository, times(1)).deleteById(id);
  }

  @Test
  void findStudentListByIdListReturnsProperList() {
    List<Student> listOfStudents = List.of(getValidStudentEntity());
    List<Long> ids = List.of(listOfStudents.get(0).getId());

    when(studentRepository.findAllById(ids)).thenReturn(listOfStudents);

    List<Student> takenStudents = studentService.findStudentListByIdList(ids);
    assertEquals(listOfStudents.get(0).getName(), takenStudents.get(0).getName());
    assertEquals(listOfStudents.get(0).getLastName(), takenStudents.get(0).getLastName());
    assertEquals(listOfStudents.get(0).getEmail(), takenStudents.get(0).getEmail());
    assertEquals(listOfStudents.get(0).getDateOfBirth(), takenStudents.get(0).getDateOfBirth());
    assertEquals(listOfStudents.get(0).getAge(), takenStudents.get(0).getAge());
    assertEquals(listOfStudents.get(0).getSex(), takenStudents.get(0).getSex());
  }

  @ParameterizedTest
  @MethodSource("provideNameAndLastNameReturnsFullName")
  void createFullNameReturnsProperString(String name, String lastName, String expected) {
    assertEquals(expected, studentService.createFullName(name, lastName));
  }
}
