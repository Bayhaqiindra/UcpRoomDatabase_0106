package com.example.pertemuan11.repository

import com.example.pertemuan11.data.dao.MataKuliahDao
import com.example.pertemuan11.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMk(
    private val mataKuliahDao: MataKuliahDao
) : RepositoryMk {

    override suspend fun insertMk(mataKuliah: MataKuliah) {
        mataKuliahDao.insertMataKuliah(mataKuliah)
    }

    override fun getAllMk(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMataKuliah()
    }

    override fun getMk(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getMataKuliah(kode)
    }

    override suspend fun updateMk(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMataKuliah(mataKuliah)
    }

    override suspend fun deleteMk(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMataKuliah(mataKuliah)
    }
}