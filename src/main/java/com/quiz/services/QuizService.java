package com.quiz.services;

import java.util.List;

import com.quiz.entity.Quiz;



public interface QuizService {

	Quiz add(Quiz quiz);
	
	List<Quiz> getQuiz();
	
	Quiz get(Long id);
}
