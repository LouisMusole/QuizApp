package com.telema.quizapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telema.quizapp.data.DataSource
import com.telema.quizapp.model.Question
import com.telema.quizapp.model.Questionnaire
import kotlinx.coroutines.launch

class MainViewModel(private val dataSource: DataSource) : ViewModel() {

    var questionnaires by mutableStateOf<List<Questionnaire>>(emptyList())
    var activeQuestionnaire by mutableStateOf<Questionnaire?>(null)
    var questions by mutableStateOf<List<Question>>(emptyList())
    var isLoaded by mutableStateOf(false)

    init {
        viewModelScope.launch {
            observeQuestionnaires()
            observeQuestion()
        }
    }

    fun setActiveQuestionnair(questionnaire: Questionnaire) {
        activeQuestionnaire = questionnaire
        viewModelScope.launch {
            observeQuestion()
        }
    }


    private suspend fun observeQuestionnaires(){
        isLoaded = true
        dataSource.getQuestionnaires().collect{
            questionnaires = it.toObjects(Questionnaire::class.java)
        }
        isLoaded = true
    }

    private suspend fun observeQuestion(){
        isLoaded = true
        dataSource.getQuestionnaireQuestions(activeQuestionnaire!!.id).collect{
            questions = it.toObjects(Question::class.java)
        }
        isLoaded = true
    }
}

