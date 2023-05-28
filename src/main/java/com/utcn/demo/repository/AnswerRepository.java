package com.utcn.demo.repository;

import com.utcn.demo.entity.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Transactional
    void deleteByQuestionId(Long id);

    List<Answer> findByTitleContaining(String title);
    List<Answer> findByQuestionId(Long id);
}
