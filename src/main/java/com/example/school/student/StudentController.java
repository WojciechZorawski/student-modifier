package com.example.school.student;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/{id}")
  public StudentResponseDTO getStudentById(@PathVariable Long id) {
    StudentResponseDTO student = studentService.getStudentById(id);
    return student;
  }

  @GetMapping("/list")
  public List<StudentResponseDTO> getAllStudents() {
    List<StudentResponseDTO> students = studentService.getAllStudents();
    return students;
  }

  @PostMapping
  public StudentResponseDTO createStudent(@RequestBody final StudentRequestDTO newStudent) {
    StudentResponseDTO createdStudent = studentService.createStudent(newStudent);
    return createdStudent;
  }

  @PutMapping("/{id}")
  public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
    StudentResponseDTO updatedStudent = studentService.updateStudent(id, name, email);
    return updatedStudent;
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
  }
}