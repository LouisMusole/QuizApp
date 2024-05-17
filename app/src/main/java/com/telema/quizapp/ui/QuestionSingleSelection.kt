package com.telema.quizapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionSingleSelection(
    optionSelected : MutableState<String>,
    options: List<String>,
){
    options.forEach { sr ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (optionSelected.value==sr) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface,
                contentColor = if (optionSelected.value==sr) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                RadioButton(selected = optionSelected.value==sr, onClick = { optionSelected.value=sr })
                Text(text = sr, modifier = Modifier.padding(16.dp))
            }
        }
    }
}