package com.example.pertemuan11.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan11.R
import com.example.pertemuan11.data.entity.Dosen
import com.example.pertemuan11.navigation.AlamatNavigasi
import com.example.pertemuan11.ui.customwidget.TopAppBar
import com.example.pertemuan11.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.pertemuan11.ui.viewmodel.dosen.HomeUiState
import com.example.pertemuan11.ui.viewmodel.dosen.PenyediaDosenViewModel
import com.example.pertemuan11.ui.viewmodel.matakuliah.FormErrorState
import com.example.pertemuan11.ui.viewmodel.matakuliah.MataKuliahViewModel
import com.example.pertemuan11.ui.viewmodel.matakuliah.MatakuliahEvent
import com.example.pertemuan11.ui.viewmodel.matakuliah.MatakuliahUIState
import com.example.pertemuan11.ui.viewmodel.matakuliah.PenyediaMkViewModel
import com.example.pertemuan11.ui.widget.DynamicSelectedTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiMataKuliahInsert : AlamatNavigasi {
    override val route = "matakuliah_insert"
}

@Composable
fun InsertMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(factory = PenyediaMkViewModel.Factory),
    viewModelDsn: HomeDosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory),
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dsnList by viewModelDsn.homeUiState.collectAsState()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier= Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .padding(16.dp)
            .padding(top = 18.dp),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Tambah MataKuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyMataKuliah(
                uiState = uiState,
                listDosen = dsnList,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateField()) {
                            viewModel.saveData()
                            delay(500)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun InsertBodyMataKuliah(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    onClick: () -> Unit,
    uiState: MatakuliahUIState,
    listDosen: HomeUiState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                ),
            listDsn = listDosen.listDsn
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
            )
        ){
            Text("Simpan", color = Color.Black)
        }
    }
}

@Composable
fun FormMataKuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    listDsn: List<Dosen>
) {
    val semester = listOf("Ganjil", "Genap")
    val jenis = listOf("Programming", "Data", "Network","UI/UX")
    val namaDosenList = listDsn.map { it.nama }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("Kode", color = Color.White) },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan Kode", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Nama", color = Color.White) },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        Text(text = "Semester", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            semester.forEach { semesterOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = matakuliahEvent.semester == semesterOption,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(semester = semesterOption))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = semesterOption, color = Color.White)
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.sks,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sks = it))
            },
            label = { Text("SKS", color = Color.White) },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan SKS", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )

        Text(text = "Jenis", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[0],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[0]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[0], color = Color.White)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[1],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[1]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[1], color = Color.White)
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[2],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[2]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[2], color = Color.White)
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = matakuliahEvent.jenis == jenis[3],
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jenis[3]))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = jenis[3], color = Color.White)
                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Dosen Pengampu", color = Color.White)
        DynamicSelectedTextField(
            selectedValue = matakuliahEvent.dosenPengampu,
            options = namaDosenList,
            label = "Pilih Dosen Pengampu",
            onValueChangedEvent = {
                onValueChange(matakuliahEvent.copy(dosenPengampu = it))
            },
        )
        Text(
            text = errorState.dosenPengampu ?: "",
            color = Color.Red
        )
    }
}
