package com.example.pertemuan11.ui.viewmodel.matakuliah

import com.example.pertemuan11.data.entity.MataKuliah

fun MataKuliah.toUiStateMatakuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMatakuliahUiEvent(),
)