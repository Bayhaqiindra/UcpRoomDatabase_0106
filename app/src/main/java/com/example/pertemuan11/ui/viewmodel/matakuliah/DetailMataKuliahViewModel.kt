package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.repository.RepositoryMk

class DetailMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
) : ViewModel() {

}

data class DetailMatakuliahUiState(
    val detailMatakuliahUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailMatakuliahUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailMatakuliahUiEvent != MatakuliahEvent()
}

fun MataKuliah.toDetailMatakuliahUiEvent(): MatakuliahEvent {
    return MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu
    )
}