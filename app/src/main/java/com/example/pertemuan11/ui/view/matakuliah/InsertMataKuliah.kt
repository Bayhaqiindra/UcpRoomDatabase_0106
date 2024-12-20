package com.example.pertemuan11.ui.view.matakuliah

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pertemuan11.data.entity.Dosen
import com.example.pertemuan11.ui.viewmodel.matakuliah.FormErrorState
import com.example.pertemuan11.ui.viewmodel.matakuliah.MatakuliahEvent

@Composable
fun FormMataKuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    listDsn: List<Dosen>
) {

}