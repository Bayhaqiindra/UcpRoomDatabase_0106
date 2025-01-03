package com.example.pertemuan11.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pertemuan11.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Insert
    suspend fun insertMataKuliah(
        mataKuliah: MataKuliah
    )

    @Query("SELECT * FROM mata_kuliah ORDER BY nama ASC")
    fun getAllMataKuliah () : Flow<List<MataKuliah>>

    @Query("SELECT * FROM mata_kuliah WHERE kode = :kode")
    fun getMataKuliah (kode: String) : Flow<MataKuliah>

    @Delete
    suspend fun deleteMataKuliah (mataKuliah: MataKuliah)

    @Update
    suspend fun updateMataKuliah (mataKuliah: MataKuliah)

}