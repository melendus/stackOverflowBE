package com.utcn.demo.service;

import com.utcn.demo.entity.Answer;
import com.utcn.demo.entity.Question;
import com.utcn.demo.repository.AnswerRepository;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Answer> retrieveAnswers() {
        return answerRepository.findAll();
    }

    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        answerRepository.findAll().forEach(answers::add);

        if (answers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    public ResponseEntity<List<Answer>> getAnswersByTitle(String title) {
        List<Answer> answers = new ArrayList<>();

        answerRepository.findByTitleContaining(title).forEach(answers::add);

        if (answers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    public ResponseEntity<Answer> getAnswerById(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow();

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    public ResponseEntity<Answer> createAnswer(Long userId,Long questionId, Answer answerRequest) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(null);

        Answer answer = userRepository.findById(userId).map(user -> {
            answerRequest.setUser(user);
            answerRequest.setQuestion(question);
            return answerRepository.save(answerRequest);
        }).orElseThrow(null);

        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    public ResponseEntity<Answer> updateAnswer( Answer answer) {
        Answer _answer = answerRepository.findById(answer.getAnswerId())
                .orElseThrow();
        _answer.setTitle(answer.getTitle());
        _answer.setDescription(answer.getDescription());

        return new ResponseEntity<>(answerRepository.save(_answer), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAnswerById(Long id) {
        answerRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteAllAnswers() {
        answerRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
