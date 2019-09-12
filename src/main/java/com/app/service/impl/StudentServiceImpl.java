package com.app.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.app.model.Student;
import com.app.repository.StudentRepository;
import com.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student createStudent(Student student) {
		
		return studentRepository.save(student);
	}
	
	@Override
	public Student getStudent(Long stdid) {
		
		return studentRepository.getOne(stdid);
	}
	
	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public boolean deleteStudent(Student student) {
		
		studentRepository.delete(student);
		Student std=studentRepository.getOne(student.getStdid());
		if(std!=null) {
			return true;
		}else {
			return false;	
		}
	}

}
