package com.example.pertemuan11.ui.viewmodel.dosen

import com.example.pertemuan11.data.entity.Dosen

data class HomeUiState(
    val listDsn: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)