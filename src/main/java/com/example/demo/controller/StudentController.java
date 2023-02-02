package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.PublicationRepository;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// CrossOrigin annotation allows application from the given url to call these apis
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PublicationRepository publicationRepository;

    /*
        The ResponseEntity class provides a convenient way to represent and
        return HTTP responses in the Spring framework
    */

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students); // This returns 200 status with the body
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            /*
                If the student is found, the Optional contains the Student object, and student.get()
                returns the Student object. If the student is not found, the Optional is empty
                and calling student.get() would result in a NoSuchElementException. To handle the case
                where the student is not found, the code uses the if (student.isPresent())
             */
            return ResponseEntity.ok(student.get());
        } else {
            /*
                ResponseEntity.notFound() returns an object of ResponseEntityBuilder with 404 Not Found,
                so we need call build() on it to get Response entity object.
                This response is sent when the server cannot find the requested resource.
            */
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentRepository.save(student);
        return ResponseEntity.ok(createdStudent);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
//                                                  @RequestBody Student studentDetails) {
//        Student student = studentRepository.findById(studentId).get();
//        student.setEmail(studentDetails.getEmail());
//        student.setFirstName(studentDetails.getFirstName());
//        student.setLastName(studentDetails.getLastName());
//        final Student updatedStudent = studentRepository.save(student);
//        return ResponseEntity.ok(updatedStudent);
//    }

    /*
    For this to work when mapping is present between publication and student. We have to use
    cascade = CascadeType.ALL, orphanRemoval = true in Student Entity.
     */
    @DeleteMapping("/delete-using-id/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {
        // deleteById is implemented by the JPA repository
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-using-email/{email}")
    public ResponseEntity<Void> deleteStudentByEmail(@PathVariable("email") String email) {
        // deleteByEmail is implemented in the Student Repo
        studentRepository.deleteByEmail(email);
        /*
            ResponseEntity.noContent() returns an object of ResponseEntityBuilder with
            204 No Content, so we need call build() on it to get Response entity object.
            HTTP 204 response is typically used in response to a DELETE request, to indicate
            that the resource has been successfully deleted
         */
        return ResponseEntity.noContent().build();
    }


    /*
        This is an alternate method to delete all the Student records if cascading is
        not used. We have to delete all the publications first and then the student.
        The method deleteAllByStudentId(id) should also be declared in the PublicationRepository.

        @DeleteMapping("delete-using-id/{id}")
            public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
                try {
                    publicationRepository.deleteAllByStudentId(id);
                    studentRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                } catch (Exception e) {
                    return ResponseEntity.notFound().build();
                }
            }
     */


}
