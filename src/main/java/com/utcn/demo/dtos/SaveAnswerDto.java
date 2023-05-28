package com.utcn.demo.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaveAnswerDto {
    private Long userId;
    private String title;
    private String description;
    private String picture;
}
