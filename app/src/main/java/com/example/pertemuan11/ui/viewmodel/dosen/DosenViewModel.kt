package com.example.pertemuan11.ui.viewmodel.dosen

import com.example.pertemuan11.data.entity.Dosen

data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid:FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val nidn:String? =null,
    val nama:String?= null,
    val jeniskelamin:String?=null
){
    fun isValid():Boolean{
        return nidn == null && nama == null &&jeniskelamin== null
    }
}

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