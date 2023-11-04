package com.lop.devtools.monstera

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.encoder.EncoderBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun getMonsteraLogger(name: String): Logger = LoggerFactory.getLogger(name)

class MonsteraEncoder : EncoderBase<ILoggingEvent>() {
    private val patternLayout = PatternLayout()
    private val placeHolder = "monsteraLogLevelPlaceholder"

    init {
        patternLayout.pattern =
            "%d{HH:mm:ss.SSS} $placeHolder %boldMagenta(%logger{36}): %msg%n"
        patternLayout.context = LoggerContext()
        patternLayout.start()
    }

    private val logo = """
#####################################################################
#      __  __                         _                             #
#     |  \/  |                       | |                            #
#     | \  / |   ___    _ __    ___  | |_    ___   _ __    __ _     #
#     | |\/| |  / _ \  | '_ \  / __| | __|  / _ \ | '__|  / _` |    #
#     | |  | | | (_) | | | | | \__ \ | |_  |  __/ | |    | (_| |    #
#     |_|  |_|  \___/  |_| |_| |___/  \__|  \___| |_|     \__,_|    #
#####################################################################""".trimIndent()

    override fun encode(event: ILoggingEvent): ByteArray {
        val logMessage = patternLayout.doLayout(event)
        return when (event.level) {

            Level.ERROR -> getFormatted("\u001B[31m\u001B[1m", "err", logMessage).toByteArray()
            Level.WARN -> getFormatted("\u001B[33m\u001B[1m", "warn", logMessage).toByteArray()
            Level.INFO -> getFormatted("\u001B[34m\u001B[1m", "info", logMessage).toByteArray()
            else -> getFormatted("\u001B[32m\u001B[1m", "fine", logMessage).toByteArray()
        }
    }

    private fun getFormatted(color: String, level: String, msg: String): String {
        val spaceOnErr = if(level.length == 3) " " else ""
        return msg.replace(placeHolder, "($color$level\u001B[0m)$spaceOnErr")
    }

    override fun headerBytes(): ByteArray {
        return "\u001B[36m${logo.replace("\n", "\n\u001B[36m")} \u001B[35mBy Lots of Pixels Studios\u001B[0m\n\n".toByteArray()
    }

    override fun footerBytes(): ByteArray {
        return "Monstera by Lots of Pixels Studios\n".toByteArray()
    }
}
