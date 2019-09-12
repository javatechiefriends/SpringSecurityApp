package com.app.service;

import java.util.List;

import com.app.model.Student;

public interface IStudentService {

	public Student createStudent(Student student);
	public Student getStudent(Long stdid);
	public List<Student> getAllStudents();
	public boolean deleteStudent(Student std);

}
