package com.utcn.demo.service;

import com.utcn.demo.dtos.QuestionDto;
import com.utcn.demo.dtos.UserDto;
import com.utcn.demo.entity.Question;
import com.utcn.demo.entity.User;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.UserRepository;
import com.utcn.demo.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

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

    public List<QuestionDto> getAllQuestionsInOrder() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> questionDtos = new ArrayList<>();

        for (Question question : questions) {
            Long voteCount = voteRepository.getVotesValueQuestion(question.getId());

            if (voteCount == null) {
                voteCount = (long) 0;
            }
            User user = question.getUser();
            UserDto userDto = new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getScore(), user.getRole(), user.isBanned());


            QuestionDto questionDto = new QuestionDto(question.getId(), question.getTitle(), question.getDescription(), question.getTags(), userDto, question.getCreatedAt());
            questionDtos.add(questionDto);
        }
        Collections.sort(questionDtos);

        return questionDtos;
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

    public QuestionDto saveQuestion(Long userId, String title, String description, String picture) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user;

        if (userOptional.isEmpty()) {
            return null;
        }

        user = userOptional.get();

        Question questionToSave = new Question();
        questionToSave.setUser(user);
        questionToSave.setTitle(title);
        questionToSave.setDescription(description);
        questionToSave.setPicture(picture);
        questionToSave.setTags(new HashSet<>());

        Question savedQuestion = questionRepository.save(questionToSave);
        UserDto userDTO = new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getScore(), user.getRole(), user.isBanned());
        return new QuestionDto(savedQuestion.getId(), savedQuestion.getTitle(), savedQuestion.getDescription(), savedQuestion.getTags(), userDTO, savedQuestion.getCreatedAt());

    }

    public ResponseEntity<Question> updateQuestion( Question question) {
        Question _question = questionRepository.findById(question.getId())
                .orElseThrow();
        System.out.println(question.getId());
        _question.setTitle(question.getTitle());
        _question.setDescription(question.getDescription());
        _question.setPicture(question.getPicture());
        _question.setTags(question.getTags());

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
