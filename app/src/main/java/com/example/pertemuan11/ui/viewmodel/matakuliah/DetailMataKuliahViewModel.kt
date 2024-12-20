package com.example.pertemuan11.ui.viewmodel.matakuliah

import com.example.pertemuan11.data.entity.MataKuliah

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