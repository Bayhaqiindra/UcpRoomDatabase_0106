package com.example.pertemuan11.repository

import com.example.pertemuan11.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    suspend fun insertDsn(dosen: Dosen)
    fun getAllDsn(): Flow<List<Dosen>>
}