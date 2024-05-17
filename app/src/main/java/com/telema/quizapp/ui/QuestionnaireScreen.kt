package com.telema.quizapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.telema.quizapp.model.Question
import kotlinx.coroutines.launch

@Composable
fun QuestionnaireScreen(
    displayType : String,
    listQuestions : List<Question>
){
    Box(modifier = Modifier.fillMaxSize()){
        val pagerState = rememberPagerState(pageCount = {
            listQuestions.size
        })

        val coroutineScope = rememberCoroutineScope()

        when (displayType) {
            "Pager" -> {
                // Pager content
                HorizontalPager(state = pagerState) { page ->
                    // Our page content
                    Box(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize())
                    {
                        Column {

                            QuestionTitled(
                                titled = listQuestions[page].titled ,
                                format = listQuestions[page].format)

                            Spacer(modifier = Modifier.height(12.dp))

                            val selectedOption = rememberSaveable { mutableStateOf("") }
                            val selectedOptions = remember { mutableStateListOf("") }

                            when (listQuestions[page].format) {
                                "Single Selection" -> {
                                    QuestionSingleSelection(
                                        optionSelected = selectedOption,
                                        options = listQuestions[page].suggestedResponses
                                    )
                                }
                                "Multiple Selection" -> {
                                    QuestionMultipleSelection(
                                        selectedOptions = selectedOptions,
                                        options = listQuestions[page].suggestedResponses
                                    )
                                }
                            }
                        }

                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)) {
                            Button(
                                onClick = { coroutineScope.launch {
                                    pagerState.animateScrollToPage(page-1)
                                }
                                },
                                enabled = page > 0
                            ) {
                                Text(text = "Précédent")
                            }
                            Button(onClick = { coroutineScope.launch {
                                pagerState.animateScrollToPage(page+1)
                            }
                            },
                                enabled = page<listQuestions.size-1
                            ) {
                                Text(text = "Suivant")
                            }
                        }
                    }
                }
            }
            "List" -> {
                // List content
                Column {
                    LazyColumn {
                        items(listQuestions){question->
                            Box(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize())
                            {
                                Column {

                                    val responseChecked = rememberSaveable {
                                        mutableStateOf("")
                                    }
                                    val checkedResponses = remember { mutableStateListOf("") }

                                    QuestionTitled(
                                        titled = question.titled,
                                        format = question.format
                                    )

                                    when (question.format) {
                                        "Single Selection" -> {
                                            QuestionSingleSelection(
                                                optionSelected = responseChecked,
                                                options = question.suggestedResponses
                                            )
                                        }
                                        "Multiple Selection" -> {
                                            QuestionMultipleSelection(
                                                selectedOptions = checkedResponses,
                                                options = question.suggestedResponses
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionTitled(titled : String, format : String){
    Column {
        Text(
            text = "format: $format",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Question : $titled",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}