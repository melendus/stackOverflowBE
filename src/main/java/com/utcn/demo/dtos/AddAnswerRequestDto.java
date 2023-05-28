package com.utcn.demo.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AddAnswerRequestDto {
    private Long questionId;
    private Long userId;
    private String description;
    private String picture;
}
