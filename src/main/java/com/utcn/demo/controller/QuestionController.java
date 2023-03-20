package com.utcn.demo.controller;

import com.utcn.demo.entity.Question;
import com.utcn.demo.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/getByTitle/{title}")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuestionByTitle(@PathVariable String title) {
        return questionService.getQuestionsByTitle(title);
    }

    @PostMapping("/addQuestion/{userId}")
    @ResponseBody
    public ResponseEntity<Question> createQuestion(@PathVariable(value = "userId") Long userId, @RequestBody Question question) {
        return questionService.createQuestion(userId,question);
    }

    @PutMapping("/updateQuestion")
    @ResponseBody
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion( question);
    }

    @DeleteMapping("deleteById/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable Long id) {
        return questionService.deleteQuestionById(id);
    }
}