package com.ll

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

object TestRunner {
    private val originalIn: InputStream = System.`in` // 키보드. in만 ``을 쓰는 이유는 코틀린에서 in이 특별한 키워드이기 때문.
    private val originalOut: PrintStream = System.out // 모니터

    fun run(input: String): String {
        val formattedInput = input
            .trimIndent()
            .plus("\n종료")

        return ByteArrayOutputStream().use { outputStream ->
            PrintStream(outputStream).use { printStream ->
                try {
                    System.setIn(
                        ByteArrayInputStream(
                            formattedInput.toByteArray()
                        )
                    )

                    System.setOut(printStream)

                    App().run()
                } finally {
                    System.setIn(originalIn)
                    System.setOut(originalOut)
                }
            }

            outputStream
                .toString()
                .trim()
                .replace("\r\n", "\n")
        }
    }
}