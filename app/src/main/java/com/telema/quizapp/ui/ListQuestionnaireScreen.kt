package com.telema.quizapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.telema.quizapp.model.Questionnaire


@Composable
fun ListQuestionnaireScreen(
    listQuestionnaire: List<Questionnaire>,
    onSelectQuestionnaire : (Questionnaire)->Unit,
    isLoaded : Boolean
){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            if (isLoaded){
                LazyColumn {
                    items(listQuestionnaire){
                        Card(modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable {
                                onSelectQuestionnaire(it)
                            }) {
                            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.weight(3f)) {
                                    Text(text = it.name, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)
                                    Text(text = it.description, overflow = TextOverflow.Ellipsis)
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }else{
                CircularProgressIndicator()
            }

        }
}