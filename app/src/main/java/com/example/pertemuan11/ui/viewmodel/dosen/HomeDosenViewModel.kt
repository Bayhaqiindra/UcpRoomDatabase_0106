package com.example.pertemuan11.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import com.example.pertemuan11.data.entity.Dosen
import com.example.pertemuan11.repository.RepositoryDosen

class HomeDosenViewModel (
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

}

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)