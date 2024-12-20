package com.example.pertemuan11.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pertemuan11.data.dao.DosenDao
import com.example.pertemuan11.data.dao.MataKuliahDao
import com.example.pertemuan11.data.entity.Dosen
import com.example.pertemuan11.data.entity.MataKuliah

@Database(entities = [MataKuliah::class, Dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {

    abstract fun dosenDao(): DosenDao
    abstract fun matakuliahDao(): MataKuliahDao
}
