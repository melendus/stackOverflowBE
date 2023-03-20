package com.utcn.demo.controller;

import com.utcn.demo.entity.Question;
import com.utcn.demo.entity.Tag;
import com.utcn.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<Tag>> getAllTags() {
        return tagService.getAllTags();
    }


    @GetMapping("getTagsByQuestionId/{questionId}")
    @ResponseBody
    public ResponseEntity<List<Tag>> getTagsByQuestionId(@PathVariable(value = "questionId") Long questionId) {
        return tagService.getAllTagsByQuestionId(questionId);
    }

    @GetMapping("getQuestionByTagId/{tagId}")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestionByTagId(@PathVariable(value = "tagId") Long tagId) {
        return tagService.getAllQUestionsByTagId(tagId);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return tagService.getTagsById(id);
    }

    @PostMapping("/addTag/{questionId}")
    @ResponseBody
    public ResponseEntity<Tag> addTag(@PathVariable(value = "questionId") Long questionId, @RequestBody Tag tagRequest) {
        return tagService.addTag(questionId, tagRequest);
    }

    @PutMapping("/updateTag")
    @ResponseBody
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }

    @DeleteMapping("/deleteTag/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable(value = "id") Long id) {
        return tagService.deleteTag(id);
    }

    @DeleteMapping("/deleteTag/{tagId}/question/{questionId}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteTagFromQuestion(@PathVariable(value = "questionId") Long questionId, @PathVariable(value = "tagId") Long tagId) {
        return tagService.deleteTagFromQuestion(questionId, tagId);
    }

}
