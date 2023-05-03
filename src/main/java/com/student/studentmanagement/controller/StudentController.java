package com.student.studentmanagement.controller;


import com.student.studentmanagement.entity.Student;
import com.student.studentmanagement.imp.StudentServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/student") //this is the base url
public class StudentController {
    @Autowired
    private StudentServicesImp studentServicesImp;

    @GetMapping("/listStudents")
    public String listStudents(Model model) {
        /*Model is an interface provided by Spring MVC
        that is used to pass data between a controller and a view.
        The Model object is used to store data that will be displayed by the view.*/
        model.addAttribute("students", studentServicesImp.getAllStudents());
        return "listStudents";
    }
    @GetMapping("/formAddStudent")
    public String formAddStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudent( @ModelAttribute("student") Student student) {
        studentServicesImp.saveStudent(student);
        return "redirect:/student/listStudents";
    }

    @GetMapping("/deleteStudent/{id}")
    public String editStudent(@PathVariable("id") long id){
        studentServicesImp.deleteStudentById(id);
        return "redirect:/student/listStudents";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") long id, Model model, Student student) {
//        model.addAttribute("student", student);
        model.addAttribute("student", studentServicesImp.getStudentById(id));
        return "editStudent";
    }

    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student, Model model) {
        Student studentExits = studentServicesImp.getStudentById(id);
        studentExits.setId(student.getId());
        studentExits.setFirstName(student.getFirstName());
        studentExits.setLastName(student.getLastName());
        studentExits.setEmail(student.getEmail());

        studentServicesImp.updateStudent(studentExits);
        return "redirect:/student/listStudents";
    }

}
