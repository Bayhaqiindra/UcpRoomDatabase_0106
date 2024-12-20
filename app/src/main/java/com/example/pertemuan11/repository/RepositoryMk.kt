package com.example.pertemuan11.repository

import com.example.pertemuan11.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMk {
    suspend fun insertMk(mataKuliah: MataKuliah)

    fun getAllMk(): Flow<List<MataKuliah>>

    fun getMk(kode: String): Flow<MataKuliah>

    suspend fun updateMk(mataKuliah: MataKuliah)

    suspend fun deleteMk(mataKuliah: MataKuliah)
}