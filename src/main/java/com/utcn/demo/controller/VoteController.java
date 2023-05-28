package com.utcn.demo.controller;

import com.utcn.demo.dtos.VoteDto;
import com.utcn.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    VoteService voteService;

    @GetMapping("/getVote")
    @ResponseBody
    public VoteDto getVote(@RequestParam Long userId, @RequestParam Long questionId, @RequestParam Long answerId) {
        return voteService.getVote(userId, questionId, answerId);
    }
}
