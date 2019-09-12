package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@PostMapping("/save")
	public  ResponseEntity<Student> ceateStudent(@RequestBody Student student) {
		
		Student std=studentService.createStudent(student);
		if(std !=null) {
			return new ResponseEntity<Student>(std,HttpStatus.OK);
		}else {
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		List<Student> stdLst=studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(stdLst, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Student> updateStudentInfo(@RequestBody Student student){
		
		Student std=studentService.createStudent(student);
		
		return new ResponseEntity<Student>(std,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{stdid}")
	public String deleteStudent(@PathVariable Long stdid) {
		
		Student std=studentService.getStudent(stdid);
		
		if(std !=null){
			if(studentService.deleteStudent(std)) {
				return "student info deleted succesfully...";
			}else {
				return "student info cannot delete, if try to delete provide valid info";
			}
		}else {
			return "student info cannot delete, if try to delete provide valid info";
		}
	}
}
