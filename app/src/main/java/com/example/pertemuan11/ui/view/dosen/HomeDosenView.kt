package com.example.pertemuan11.ui.view.dosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan11.R
import com.example.pertemuan11.data.entity.Dosen
import com.example.pertemuan11.ui.customwidget.TopAppBar
import com.example.pertemuan11.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.pertemuan11.ui.viewmodel.dosen.HomeUiState
import com.example.pertemuan11.ui.viewmodel.dosen.PenyediaDosenViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeDosenView(
    viewModel: HomeDosenViewModel = viewModel(factory = PenyediaDosenViewModel.Factory),
    onAddDosen: () -> Unit = {},
    onBack: () -> Unit,
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
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
        topBar = {
            TopAppBar(
                judul = "Daftar Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDosen,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        }
    ){ innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDosenView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
                println(it)
            },
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.primary
                    )
                )
                .padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDosenView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            Box(
                modifier = modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxSize(),

                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeUiState.listDsn.isEmpty() -> {
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(
                            id = R.color.primary
                        )
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListDosen(
                listDsn = homeUiState.listDsn,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListDosen(
    listDsn: List<Dosen>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.primary
                )
            )
    ) {
        items(
            items = listDsn,
            itemContent = { dsn ->
                CardDosen(
                    dsn = dsn,
                    onClick = { onClick(dsn.nidn) }
                )
            }
        )
    }
}

@Composable
fun CardDosen(
    dsn: Dosen,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors( // Warna interaktif kartu
                containerColor = colorResource(id = R.color.yellow), // Warna default
        contentColor = colorResource(id = R.color.black)
                )
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.nidn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dsn.jenisKelamin,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}