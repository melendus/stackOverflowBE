package com.utcn.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDto implements Comparable<AnswerDto>{
    private Long id;
    private String title;
    private String description;
    private UserDto creator;

    private Long voteCount;

    @Override
    public int compareTo(AnswerDto answer) {
        Long answerVoteCount = this.getVoteCount();
        Long comparedAnswerVoteCount = answer.getVoteCount();

        if (answerVoteCount.equals(comparedAnswerVoteCount)) {
            return 0;
        } else if (answerVoteCount < comparedAnswerVoteCount) {
            return 1;
        } else {
            return -1;
        }
    }
}
