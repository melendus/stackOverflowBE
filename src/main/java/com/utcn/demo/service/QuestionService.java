package com.utcn.demo.service;

import com.utcn.demo.entity.Question;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    public List<Question> retrieveQuestions() {
        return questionRepository.findAll();
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(questions::add);

        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByTitle(String title) {
        List<Question> questions = new ArrayList<>();

        questionRepository.findByTitleContaining(title).forEach(questions::add);

        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Question> getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow();

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    public ResponseEntity<Question> createQuestion(Long userId, Question questionRequest) {

        Question question = userRepository.findById(userId).map(user -> {
            questionRequest.setUser(user);
            return questionRepository.save(questionRequest);
        }).orElseThrow(null);

        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    public ResponseEntity<Question> updateQuestion( Question question) {
        Question _question = questionRepository.findById(question.getId())
                .orElseThrow();
        System.out.println(question.getId());
        _question.setTitle(question.getTitle());
        _question.setDescription(question.getDescription());

        return new ResponseEntity<>(questionRepository.save(_question), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteQuestionById(Long id) {
        questionRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteAllQuestions() {
        questionRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
