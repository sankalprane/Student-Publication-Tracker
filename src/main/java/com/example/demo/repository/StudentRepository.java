package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Modifying is used because this is not a read only operation like Select.
    @Transactional
    @Modifying
    @Query("delete from Student s where s.email = :email")
    void deleteByEmail(@Param("email") String email);
}
