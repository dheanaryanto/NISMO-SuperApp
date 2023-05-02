package edu.uksw.fti.pam.pam_activityintent.models

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _firstName: MutableStateFlow<String?> = MutableStateFlow(null)
    val firstName: StateFlow<String?> = _firstName

    // Fungsi untuk mengambil nama depan user dari Firestore berdasarkan email
    fun fetchFirstNameByEmail(email: String) {
        db.collection("users")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                if (result.size() > 0) {
                    // Ambil nama depan dari dokumen yang sesuai
                    val user = result.documents[0].toObject(Users::class.java)
                    _firstName.value = user?.fnama
                } else {
                    _firstName.value = null
                }
            }
            .addOnFailureListener { exception ->
                // Handle error jika query gagal
            }
    }
}