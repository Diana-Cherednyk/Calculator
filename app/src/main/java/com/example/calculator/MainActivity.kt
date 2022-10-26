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
            inputNumber.text = null
           output.text = ""
       }
        btnDivide.setOnClickListener {
            input.text = addToInputText(inputNumber.text.toString())
            input.text = addToInputText("÷")
            inputNumber.text = null
        }
        btnMultiply.setOnClickListener {
            input.text = addToInputText(inputNumber.text.toString())
            input.text = addToInputText("×")
            inputNumber.text= null
        }
        btnSubstract.setOnClickListener {
            input.text = addToInputText(inputNumber.text.toString())
            input.text = addToInputText("-")
            inputNumber.text= null
        }
        btnAdd.setOnClickListener {
        input.text = addToInputText(inputNumber.text.toString())
            input.text = addToInputText("+")
            inputNumber.text = null

        }

        btnEqual.setOnClickListener {
            input.text = addToInputText(inputNumber.text.toString())
            inputNumber.text = null
            showResult()
        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
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