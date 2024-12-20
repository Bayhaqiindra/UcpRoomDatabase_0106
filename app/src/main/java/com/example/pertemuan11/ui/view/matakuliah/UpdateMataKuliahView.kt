package com.example.pertemuan11.ui.view.matakuliah

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pertemuan11.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.pertemuan11.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.example.pertemuan11.ui.viewmodel.matakuliah.PenyediaMkViewModel
import com.example.pertemuan11.ui.viewmodel.matakuliah.UpdateMataKuliahViewModel

@Composable
fun UpdateMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMataKuliahViewModel = viewModel(factory = PenyediaMkViewModel.Factory),
    viewModelDsn: HomeDosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory),
){

}