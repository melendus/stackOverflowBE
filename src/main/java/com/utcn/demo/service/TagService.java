package com.utcn.demo.service;

import com.utcn.demo.entity.Question;
import com.utcn.demo.entity.Tag;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<>();

        tagRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    //if no such question, return null
    public ResponseEntity<List<Tag>> getAllTagsByQuestionId(Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            return null;
        }

        List<Tag> tags = tagRepository.findTagsByQuestionsId(questionId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    public ResponseEntity<Tag> getTagsById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElse(null);

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getAllQUestionsByTagId(Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            return null;
        }

        List<Question> questions = questionRepository.findQuestionByTagsId(tagId);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Tag> addTag(Long questionId, Tag tagRequest) {
        Tag tag = questionRepository.findById(questionId).map(question -> {
            Long tagId = tagRequest.getId();

            //tag exists
            if (tagId != null) {
                Tag _tag = tagRepository.findById(tagId)
                        .orElse(null);
                question.addTag(_tag);
                questionRepository.save(question);
                return _tag;
            }

            //add and create new tag
            question.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).orElse(null);

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    public ResponseEntity<Tag> updateTag(Tag tagRequest) {
        Tag tag = tagRepository.findById(tagRequest.getId())
                .orElseThrow(null);

        tag.setName(tagRequest.getName());

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteTagFromQuestion( Long questionId,  Long tagId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(null);

        question.removeTag(tagId);
        questionRepository.save(question);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteTag(Long id) {
        tagRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
