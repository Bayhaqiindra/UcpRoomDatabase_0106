package com.example.pertemuan11.ui.view.dosen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan11.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.pertemuan11.ui.viewmodel.dosen.PenyediaDosenViewModel

@Composable
fun HomeDosenView(
    viewModel: HomeDosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory),
    onAddDosen: () -> Unit = {},
    onBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){

}