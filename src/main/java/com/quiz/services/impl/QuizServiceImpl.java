package com.quiz.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private QuestionClient questionClient;

	@Override
	public Quiz add(Quiz quiz) {
		
		return quizRepo.save(quiz);
	}

	@Override
	public List<Quiz> getQuiz() {
		List<Quiz> quizzes= quizRepo.findAll();
		
		List<Quiz> newQuizList=quizzes.stream().map(quiz -> {
			quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList());
		
		return newQuizList;
	}

	@Override
	public Quiz get(Long id) {
		Quiz quiz=quizRepo.findById(id).orElseThrow(()-> new RuntimeException("Quiz not found"));
		quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		return quiz;
	}

}
