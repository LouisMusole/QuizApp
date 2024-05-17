package com.telema.quizapp.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionMultipleSelection(
    selectedOptions: SnapshotStateList<String>,
    options: List<String>,
){
    options.forEach {option ->
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (selectedOptions.contains(option)) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface,
                contentColor = if (selectedOptions.contains(option)) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedOptions.contains(option),
                    onCheckedChange = { if (selectedOptions.contains(option)) selectedOptions.remove(option) else selectedOptions.add(option) },
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = option, modifier = Modifier.padding(16.dp))
            }
        }
    }
}