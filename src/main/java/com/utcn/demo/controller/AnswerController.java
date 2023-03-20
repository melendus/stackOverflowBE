package com.utcn.demo.controller;

import com.utcn.demo.entity.Answer;
import com.utcn.demo.service.AnswerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {


    @Autowired
    AnswerService answerService;

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<Answer>> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping("/getByTitle/{title}")
    @ResponseBody
    public ResponseEntity<List<Answer>> getAnswerByTitle(@PathVariable String title) {
        return answerService.getAnswersByTitle(title);
    }

    @PostMapping("/addAnswer/{userId}/question/{questionId}")
    @ResponseBody
    public ResponseEntity<Answer> createAnswer(@PathVariable(value = "userId") Long userId,@PathVariable(value = "questionId") Long questionId, @RequestBody Answer answer) {
        return answerService.createAnswer(userId,questionId ,answer);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) {
        return answerService.updateAnswer( answer);
    }

    @DeleteMapping("deleteById/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<HttpStatus> deleteAnswerById(@PathVariable Long id) {
        return answerService.deleteAnswerById(id);
    }

}
