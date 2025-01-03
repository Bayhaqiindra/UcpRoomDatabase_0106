package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.repository.RepositoryMk
import kotlinx.coroutines.launch

class MataKuliahViewModel(private val repositoryMk: RepositoryMk): ViewModel() {
    var uiState by mutableStateOf(MatakuliahUIState())

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    fun validateField(): Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.matakuliahEvent
        if(validateField()){
            viewModelScope.launch {
                try{
                    repositoryMk.insertMk(currentEvent.toMatakuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }else{
            uiState=uiState.copy(
                snackBarMessage = "Input tidak valid periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState=uiState.copy(snackBarMessage = null)
    }
}

data class MatakuliahUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid:FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null,
){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenPengampu == null
    }
}

fun MatakuliahEvent.toMatakuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)

data class MatakuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)