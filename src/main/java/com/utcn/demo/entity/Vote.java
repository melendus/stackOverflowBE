package com.utcn.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "answer_id")
    private Answer answer;


    @Column(name="type")
    private String type;
    @Column(name = "value")
    private int value;

    public Vote() {

    }

    public Vote(Long id, User user, Question question, Answer answer) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) && Objects.equals(user, vote.user) && Objects.equals(question,
                vote.question) && Objects.equals(answer, vote.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, question, answer);
    }
}
