package edu.uksw.fti.pam.pam_activityintent.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ApparelViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    private val apparelCollection = firestore.collection("apparels")

    private val _apparelList = MutableLiveData<List<Apparel>>()
    val apparelList: LiveData<List<Apparel>>
        get() = _apparelList

    init {
        loadData()
    }

    private fun loadData() {
        apparelCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.w("ApparelViewModel", "Listener failed", error)
                return@addSnapshotListener
            }

            val list = mutableListOf<Apparel>()
            snapshot?.documents?.forEach { doc ->
                val apparel = doc.toObject(Apparel::class.java)
                if (apparel != null) {
                    list.add(apparel)
                }
            }
            _apparelList.value = list
        }
    }
}