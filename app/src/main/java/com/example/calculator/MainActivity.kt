package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClear.setOnClickListener {
            input.text = ""
            res.text = ""
            output.text = ""
        }
        btn0.setOnClickListener {
            input.text = addToInputText("0")
            res.text = addToInputText("0")


        }
        btn1.setOnClickListener {
            input.text =addInputText("1")
            res.text = addToInputText("1")

        }
        btn2.setOnClickListener {
            input.text = addInputText("2")
            res.text = addToInputText("2")

        }
        btn3.setOnClickListener {
            input.text = addInputText("3")
            res.text = addToInputText("3")
        }
        btn4.setOnClickListener {
            input.text = addInputText("4")
            res.text = addToInputText("4")
        }
        btn5.setOnClickListener {
            input.text = addInputText("5")
            res.text = addToInputText("5")
        }
        btn6.setOnClickListener {
            input.text = addInputText("6")
            res.text = addToInputText("6")
        }
        btn7.setOnClickListener {
            input.text = addInputText("7")
            res.text = addToInputText("7")
        }
        btn8.setOnClickListener {
            input.text = addInputText("8")
            res.text = addToInputText("8")
        }
        btn9.setOnClickListener {
            input.text = addInputText("9")
            res.text = addToInputText("9")
        }
        btnDot.setOnClickListener {
            input.text = addToInputText(".")
            res.text = addToInputText(".")
        }
        btnDivide.setOnClickListener {
            input.text = addToInputText("÷")
            res.text = addToInputText("÷")
            input.text = null
        }
        btnMultiply.setOnClickListener {
            input.text = addToInputText("×")
            res.text = addToInputText("×")
            input.text = null
        }
        btnSubstract.setOnClickListener {
            input.text = addToInputText("-")
            res.text = addToInputText("-")
            input.text = null
        }
        btnAdd.setOnClickListener {
            input.text = addToInputText("+")
            res.text = addToInputText("+")
            input.text = null

        }

        btnEqual.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${res.text}$buttonValue"
    }
    private fun addInputText(buttonValue: String): String {
        return "${input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = res.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                output.text = "Помилка!"
                output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                output.text = DecimalFormat("0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            output.text = "Помилка!"
            output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        btnAct.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("result", output.text.toString())
            startActivity(intent)
        }


    }
}