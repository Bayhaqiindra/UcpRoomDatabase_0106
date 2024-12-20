package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.data.entity.MataKuliah
import com.example.pertemuan11.navigation.DestinasiMataKuliahDetail
import com.example.pertemuan11.repository.RepositoryMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,
) : ViewModel() {

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiMataKuliahDetail.KODE])

    val detailMatakuliahUiState: StateFlow<DetailMatakuliahUiState> = repositoryMk.getMk(_kode)
        .filterNotNull()
        .map {
            DetailMatakuliahUiState(
                detailMatakuliahUiEvent = it.toDetailMatakuliahUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMatakuliahUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailMatakuliahUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailMatakuliahUiState(
                isLoading = true,
            ),
        )

    fun deleteMatakuliah() {
        detailMatakuliahUiState.value.detailMatakuliahUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repositoryMk.deleteMk(it)
            }
        }
    }
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