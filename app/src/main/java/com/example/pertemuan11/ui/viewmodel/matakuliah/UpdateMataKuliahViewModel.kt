package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.navigation.DestinasiMataKuliahUpdate
import com.example.pertemuan11.repository.RepositoryMk
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
) : ViewModel() {

    var updateUIState by mutableStateOf(MatakuliahUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiMataKuliahUpdate.KODE])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMk.getMk(_kode)
                .filterNotNull()
                .first()
                .toUiStateMatakuliah()
        }
    }

    fun updateState (matakuliahEvent: MatakuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields() : Boolean {
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semeseter tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen tidak boleh kosong",
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMk.updateMk(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Berhasil Diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.
                    snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data Gagal Diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun MataKuliah.toUiStateMatakuliah () : MatakuliahUIState = MatakuliahUIState(
    matakuliahEvent = this.toDetailMatakuliahUiEvent(),
)