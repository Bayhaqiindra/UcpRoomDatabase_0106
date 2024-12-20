package com.example.pertemuan11.ui.viewmodel.dosen

import com.example.pertemuan11.data.entity.Dosen

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin,
)

data class DosenEvent(
    val nidn: String = "",
    val nama: String ="",
    val jenisKelamin: String ="",
)