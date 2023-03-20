package com.utcn.demo.repository;

import com.utcn.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long deleteQuestionById(Long questionId);

    Optional<Question> findQuestionById(Long qustionId);

    List<Question> findQuestionByTagsId(Long tagId);

    List<Question> findByTitleContaining(String title);
}
