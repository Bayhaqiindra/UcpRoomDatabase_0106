package com.example.pertemuan11.dependeciesinjection

import android.content.Context
import com.example.pertemuan11.data.database.KrsDatabase
import com.example.pertemuan11.repository.LocalRepositoryDosen
import com.example.pertemuan11.repository.LocalRepositoryMk
import com.example.pertemuan11.repository.RepositoryDosen
import com.example.pertemuan11.repository.RepositoryMk

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMk: RepositoryMk
}

class ContainerApp(private val context: Context) : InterfaceContainerApp
{
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(KrsDatabase.getDataBase(context).dosenDao())
    }
    override val repositoryMk: RepositoryMk by lazy {
        LocalRepositoryMk(KrsDatabase.getDataBase(context).matakuliahDao())
    }
}
