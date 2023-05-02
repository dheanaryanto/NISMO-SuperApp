package edu.uksw.fti.pam.pam_activityintent.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PartViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    private val partCollection = firestore.collection("parts")

    private val _partList = MutableLiveData<List<Part>>()
    val partList: LiveData<List<Part>>
        get() = _partList

    init {
        loadData()
    }

    private fun loadData() {
        partCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.w("PartViewModel", "Listener failed", error)
                return@addSnapshotListener
            }

            val list = mutableListOf<Part>()
            snapshot?.documents?.forEach { doc ->
                val part = doc.toObject(Part::class.java)
                if (part != null) {
                    list.add(part)
                }
            }
            _partList.value = list
        }
    }
}