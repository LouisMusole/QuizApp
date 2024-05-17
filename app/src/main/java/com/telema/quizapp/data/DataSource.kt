package com.telema.quizapp.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.telema.quizapp.model.Question
import com.telema.quizapp.model.Questionnaire
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class DataSource {
    private val db = Firebase.firestore

    fun getQuestionnaires() = db.collection("Questionnaire")
        .snapshotFlow()
    fun getQuestionnaireQuestions(id: String) = db.collection("Questionnaire")
        .document(id)
        .collection("Questions")
        .snapshotFlow()




}

fun CollectionReference.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
    val listenerRegistration = addSnapshotListener { value, error ->
        if (error != null) {
            close(error)
            return@addSnapshotListener
        }
        if (value != null) {
            trySend(value)
        }
    }
    awaitClose { listenerRegistration.remove() }
}