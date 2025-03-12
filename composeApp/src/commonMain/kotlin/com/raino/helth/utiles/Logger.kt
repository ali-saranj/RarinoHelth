package com.raino.helth.utiles

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object Logger {
    fun init() {
        Napier.base(DebugAntilog())
    }
} 