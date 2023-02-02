package com.example.demo.controller;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.model.Publication;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/publication")
public class PublicationController {

    Logger logger = LoggerFactory.getLogger(PublicationController.class);

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    StudentRepository studentRepository;

//    @PostMapping("/create")
//    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
//        Publication createdPublication = publicationRepository.save(publication);
//        return ResponseEntity.ok(createdPublication);
//    }

    @GetMapping
    public ResponseEntity<List<PublicationDTO>> findAllPublicationsWithStudentDetails() {
        List<Publication> publications = publicationRepository.findAll();
        /*
            By default, Java does not have map method on the List like Javascript. So we have to
            convert it into a stream using stream() method and then apply map. This gives back a
            stream as a result. THen use collect(Collectors.toList()) to convert it into a List.
         */
        List<PublicationDTO> publicationDTOs = publications.stream().map(publication -> {
            Student student = publication.getStudent();
            return new PublicationDTO(publication.getTitle(), publication.getYear(),
                    student.getFirstName(), student.getLastName(), student.getEmail());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(publicationDTOs);
    }

    @PostMapping("create/{studentId}")
    public ResponseEntity<Publication> createPublication(@PathVariable Integer studentId, @RequestBody Publication publication) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            publication.setStudent(student.get());
            Publication createdPublication = publicationRepository.save(publication);
            return ResponseEntity.ok(createdPublication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("list-of-publications")
    public ResponseEntity<List<Publication>> getListOfPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return ResponseEntity.ok(publications);
    }

}
