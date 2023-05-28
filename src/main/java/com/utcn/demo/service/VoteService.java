package com.utcn.demo.service;

import com.utcn.demo.dtos.VoteDto;
import com.utcn.demo.entity.Vote;
import com.utcn.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public VoteDto getVote(Long userId, Long questionId, Long answerId) {
        Vote vote = voteRepository.findByUser_UserIdAndQuestion_IdAndAnswer_AnswerId(userId, questionId, answerId);

        if (vote == null)
            return null;
        if (vote.getType() == "question")
            return new VoteDto(vote.getUser().getUserId(), vote.getQuestion().getId(), vote.getValue());
        else
            return new VoteDto(vote.getUser().getUserId(), vote.getAnswer().getAnswerId(), vote.getValue());

    }
}
