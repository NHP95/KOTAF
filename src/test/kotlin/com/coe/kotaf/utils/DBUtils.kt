package com.coe.kotaf.utils

import com.beust.klaxon.Klaxon
import com.coe.kotaf.configuration
import com.coe.kotaf.database.DataSource
import com.coe.kotaf.database.SQLConnection

const val dbName = "patest2"
val database by lazy {
    val dataSource = Klaxon().parse<DataSource>(configuration.obj("paDataSource")?.toJsonString()!!)
        ?: DataSource()
    SQLConnection(dataSource)
}
