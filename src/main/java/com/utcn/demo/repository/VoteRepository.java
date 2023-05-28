package com.utcn.demo.repository;

import com.utcn.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByUser_UserIdAndQuestion_IdAndAnswer_AnswerId(Long userId, Long id, Long answerId);
    @Query("select sum(a.value) from Vote a where a.question.id = ?1")
    Long getVotesValueQuestion(Long questionId);

    @Query("select sum(a.value) from Vote a where a.answer.id = ?1")
    Long getVotesValueAnswer(Long answerId);
}
