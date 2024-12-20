package com.example.pertemuan11.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pertemuan11.KrsApp

object PenyediaMkViewModel {

    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                KrsApp().containerApp.repositoryMk
            )
        }
        initializer {
            HomeMataKuliahViewModel(
                KrsApp().containerApp.repositoryMk,
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMk,
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMk,
            )
        }
    }
}

fun CreationExtras.KrsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
