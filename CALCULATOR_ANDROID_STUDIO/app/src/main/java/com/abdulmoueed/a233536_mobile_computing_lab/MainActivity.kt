package com.abdulmoueed.a233536_mobile_computing_lab


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var canAddOperator = false
    private var canAddDecimal = true

    private lateinit var working: TextView
    private lateinit var results: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing TextViews
        working = findViewById(R.id.working)
        results = findViewById(R.id.results)
    }

    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDecimal) {
                    working.append(view.text)
                    canAddDecimal = false
                }
            } else {
                working.append(view.text)
            }
            canAddOperator = true
        }
    }

    fun operationAction(view: View) {
        if (view is Button && canAddOperator) {
            val op = when (view.text) {
                "×" -> "*" // Replace '×' with '*'
                "−" -> "-" // Replace '−' with '-'
                else -> view.text
            }
            working.append(op)
            canAddOperator = false
            canAddDecimal = true
        }
    }

    fun allClearAction(view: View) {
        working.text = ""
        results.text = ""
        canAddDecimal = true
    }

    fun backSpaceAction(view: View) {
        val length = working.text.length
        if (length > 0) {
            working.text = working.text.subSequence(0, length - 1)
        }
    }

    fun equalsAction(view: View) {
        results.text = calculate()
    }

    private fun calculate(): String {
        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+') result += nextDigit
                if (operator == '-') result -= nextDigit
            }
        }
        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('*') || list.contains('/')) {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float

                when (operator) {
                    '*' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        if (nextDigit == 0f) return mutableListOf("Error: Division by zero")
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }
            if (i > restartIndex) newList.add(passedList[i])
        }
        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""

        for (character in working.text) {
            if (character.isDigit() || character == '.') {
                currentDigit += character
            } else {
                if (currentDigit.isNotEmpty()) {
                    list.add(currentDigit.toFloat())
                    currentDigit = ""
                }
                list.add(character)
            }
        }

        if (currentDigit.isNotEmpty()) list.add(currentDigit.toFloat())
        return list
    }
}
