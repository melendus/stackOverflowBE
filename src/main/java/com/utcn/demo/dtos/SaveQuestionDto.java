package com.utcn.demo.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaveQuestionDto {
    private Long userId;
    private String title;
    private String description;
    private String picture;

}
