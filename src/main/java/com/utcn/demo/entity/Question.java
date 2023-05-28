package com.utcn.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    //aici trb sa vad daca merge asa
    //@Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "question_title")
    private String title;

    @Column(name = "question_description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "question-tags,",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new HashSet<>();

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Lob
    @Column(name = "picture", length = Integer.MAX_VALUE, columnDefinition = "LONGTEXT")
    private String picture;
    @OneToMany(mappedBy = "question",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Vote> votes;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question() {
    }

    public Question(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }

    public void removeTab(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId)
                .findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getQuestions().remove(this);
        }
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getQuestions().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(tag1 -> tag1.getId() == tagId)
                .findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getQuestions().remove(this);
        }
    }
}
