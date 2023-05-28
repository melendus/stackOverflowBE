package com.utcn.demo.dtos;

import com.utcn.demo.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDto implements Comparable<QuestionDto> {

    private Long id;
    private String title;
    private String description;
    private Set<Tag> tags;

    private UserDto creator;

    private LocalDateTime createdAt;

    @Override
    public int compareTo(QuestionDto questionDto) {
        LocalDateTime questionToCompare = questionDto.getCreatedAt();

        if (this.createdAt.isEqual(questionToCompare))  {
            return 0;
        } else if (this.createdAt.isBefore(questionToCompare)) {
            return 1;
        } else {
            return -1;
        }
    }
}
