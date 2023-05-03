package com.student.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository => unnecessary
public interface StudentRepository extends
        JpaRepository <com.student.studentmanagement.entity.Student, Long> {
}
