package com.ll

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

object TestRunner {
    private val originalIn: InputStream = System.`in` // 키보드. in만 ``을 쓰는 이유는 코틀린에서 in이 특별한 키워드이기 때문.
    private val originalOut: PrintStream = System.out // 모니터

    fun run(input: String): String {
        // 표준 입력 리다이렉팅
        // 키보드 입력 => 문자열 입력
        System.setIn(
            ByteArrayInputStream(
                ("${input.trimIndent()}\n종료")
                    .toByteArray()
            )
        )

        // 표준 출력 리다이렉팅
        // 콘솔 출력 => 문자열 출력
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)

        System.setOut(printStream)

        App().run() // 앱실행

        // 표준 출력 결과를 문자열로 변환
        val result = outputStream
            .toString()
            .trim()
            .replace(Regex("\\r\\n"), "\n") // 개행문자 차이 표준화(윈도우와/맥의 차이)

        // 다시 표준 입력으로 복구
        System.setIn(originalIn)
        // 다시 표준 출력으로 복구
        System.setOut(originalOut)

        return result
    }

}