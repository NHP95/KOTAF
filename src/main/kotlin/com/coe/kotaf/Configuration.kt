package com.coe.kotaf

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.coe.kotaf.utils.getResourcesPath

val configuration: JsonObject =
    Parser.default().parse(getResourcesPath("configuration.json")) as JsonObject

val ELEMENT_TIMEOUT = configuration.long("elementTimeout") ?: 10
