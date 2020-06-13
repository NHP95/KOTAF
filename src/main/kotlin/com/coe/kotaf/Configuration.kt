package com.coe.kotaf

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.coe.kotaf.enum.getResourcesPath

val configurationFilePath: String = System.getProperty("config", "configuration.json")
val configuration: JsonObject =
    Parser.default().parse(getResourcesPath(configurationFilePath)) as JsonObject

val ELEMENT_TIMEOUT = configuration.long("elementTimeout") ?: 20
