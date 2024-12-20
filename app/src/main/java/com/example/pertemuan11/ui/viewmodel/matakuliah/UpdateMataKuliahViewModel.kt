package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.repository.RepositoryMk

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
) : ViewModel() {

    var updateUIState by mutableStateOf(MatakuliahUIState())
        private set
}
fun MataKuliah.toUiStateMatakuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMatakuliahUiEvent(),
)