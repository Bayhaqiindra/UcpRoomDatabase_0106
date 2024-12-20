package com.example.pertemuan11.ui.viewmodel.matakuliah

import com.example.pertemuan11.data.entity.MataKuliah

data class HomeMatakuliahUiState (
    val listMtk: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)