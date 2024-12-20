package com.example.pertemuan11.repository

import com.example.pertemuan11.data.dao.DosenDao
import com.example.pertemuan11.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen(
    private val dosenDao: DosenDao
) : RepositoryDosen {
    override suspend fun insertDsn(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDsn(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }
}