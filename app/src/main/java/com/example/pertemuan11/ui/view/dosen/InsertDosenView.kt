package com.example.pertemuan11.ui.view.dosen

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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan11.R
import com.example.pertemuan11.navigation.AlamatNavigasi
import com.example.pertemuan11.ui.customwidget.TopAppBar
import com.example.pertemuan11.ui.viewmodel.dosen.DosenEvent
import com.example.pertemuan11.ui.viewmodel.dosen.DosenUIState
import com.example.pertemuan11.ui.viewmodel.dosen.DosenViewModel
import com.example.pertemuan11.ui.viewmodel.dosen.FormErrorState
import com.example.pertemuan11.ui.viewmodel.dosen.PenyediaDosenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiDosenInsert : AlamatNavigasi {
    override val route = "insert_dsn"
}

@Composable
fun InsertDosenView(
    onBack:()->Unit,
    onNavigate:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember{ SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen",
                modifier = modifier
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyDosen(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyDosen(
    modifier: Modifier = Modifier,
    onValueChange:(DosenEvent)->Unit,
    onClick:() -> Unit,
    uiState: DosenUIState
){
    Column (
        modifier= modifier
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // Tombol berwarna putih
                contentColor = Color.Black // Teks pada tombol berwarna hitam
            )
        ) {
            Text("Simpan", color = Color.Black)
        }
    }
}

@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange:(DosenEvent)->Unit={},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki-Laki", "Perempuan")

    Column(
        modifier = modifier
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama", color = Color.White) },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
            shape = RoundedCornerShape(16.dp)
            )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN", color = Color.White) },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan NIDN", color = Color.White) },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.nidn ?: "",
            color = Color.Red
        )

        Text(text = "Jenis Kelamin", color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth()

        ) {
            jenisKelamin.forEach { jk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = dosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jenisKelamin = jk))
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White, // Radio button putih ketika dipilih
                            unselectedColor = Color.White // Radio button putih ketika tidak dipilih
                        )
                    )
                    Text(
                        text = jk,
                        color = Color.White
                    )
                }
            }
        }
    }
}