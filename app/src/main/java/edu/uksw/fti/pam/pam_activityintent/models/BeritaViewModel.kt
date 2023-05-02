package edu.uksw.fti.pam.pam_activityintent.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.pam_activityintent.repositories.JSONPlaceholderTypicodeRepository
import kotlinx.coroutines.launch

class BeritaViewModel :  ViewModel() {
    private var _beritaList = mutableStateListOf<Berita>()

    var errorMessage: String by mutableStateOf("")
    val beritaList: List<Berita>
        get() = _beritaList

    fun getBeritaList() {
        viewModelScope.launch {
            val apiClient = JSONPlaceholderTypicodeRepository.getClient()
            try {
                _beritaList.clear()
                _beritaList.addAll(apiClient.getBerita())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}