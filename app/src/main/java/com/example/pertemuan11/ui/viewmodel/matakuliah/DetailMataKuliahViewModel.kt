package com.example.pertemuan11.ui.viewmodel.matakuliah

import com.example.pertemuan11.data.entity.MataKuliah

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