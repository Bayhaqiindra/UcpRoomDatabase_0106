package com.example.pertemuan11.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mata_kuliah")
data class MataKuliah(
    @PrimaryKey
    val kode: String,
    val nama: String,
    val sks: String,
    val semester: String,
    val jenis: String,
    val dosenPengampu: String
)