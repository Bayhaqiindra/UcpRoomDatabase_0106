package com.example.pertemuan11.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pertemuan11.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(
        dosen: Dosen
    )
    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllDosen () : Flow<List<Dosen>>
}