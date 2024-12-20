package com.example.pertemuan11.dependeciesinjection

import com.example.pertemuan11.repository.RepositoryDosen
import com.example.pertemuan11.repository.RepositoryMk

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMk: RepositoryMk
}
