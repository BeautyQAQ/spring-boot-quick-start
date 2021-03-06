package com.quick.start.ddd.domain.repository;

import com.quick.start.ddd.domain.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, String> {
}
