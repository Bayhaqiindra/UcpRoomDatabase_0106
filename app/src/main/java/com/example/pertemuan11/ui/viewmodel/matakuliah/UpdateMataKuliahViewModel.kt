package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.repository.RepositoryMk

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
) : ViewModel() {

}
fun MataKuliah.toUiStateMatakuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMatakuliahUiEvent(),
)