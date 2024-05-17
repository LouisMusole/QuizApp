package com.telema.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.telema.quizapp.data.DataSource
import com.telema.quizapp.model.Questionnaire
import com.telema.quizapp.ui.ListQuestionnaireScreen
import com.telema.quizapp.ui.QuestionnaireScreen
import com.telema.quizapp.ui.theme.QuizAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizAppTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(title = { 
                            Text(text = "Quiz App")
                        })
                    }
                ) { it ->
                    val viewModel = viewModel<MainViewModel>{
                        MainViewModel(DataSource())
                    }
                    Box(modifier = Modifier.padding(it)){
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = ListScreen){
                            composable<ListScreen>(){
                                ListQuestionnaireScreen(
                                    listQuestionnaire = viewModel.questionnaires,
                                    onSelectQuestionnaire = {questionnaire->
                                        viewModel.setActiveQuestionnair(questionnaire)
                                        navController.navigate(QuestionnaireScreen(
                                            id = questionnaire.id,
                                            name = questionnaire.name,
                                            description = questionnaire.description,
                                            displayType = questionnaire.displayType
                                        ))
                                    },
                                    isLoaded = viewModel.isLoaded
                                )
                            }

                            composable<QuestionnaireScreen> {

                                val args = it.toRoute<QuestionnaireScreen>()

                                QuestionnaireScreen(
                                    listQuestions = viewModel.questions,
                                    displayType = args.displayType
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ListScreen

@Serializable
data class QuestionnaireScreen(
    val id :  String,
    val name : String,
    val description : String,
    val displayType : String
)
