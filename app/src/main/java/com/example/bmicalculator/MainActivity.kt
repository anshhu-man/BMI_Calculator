package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcBtn = findViewById<Button>(R.id.btnCalculate)
        val result = findViewById<CardView>(R.id.cvResult)

        calcBtn.setOnClickListener {
            result.visibility = View.VISIBLE
            if(validate(weightText.text.toString(),heightText.text.toString())){
                val weight = weightText.text.toString().toFloat();
                val height = heightText.text.toString().toFloat()/100.0
                val bmi = weight/(height*height)
                val bmi2Digits = String.format("%.2f",bmi).toFloat()
                displayResult(bmi2Digits)
            }

        }
    }
    private fun validate(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrBlank() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrBlank() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "normal range is 18.5-24.9"

        var resultText = ""
        var color = 0

        when{
            bmi<18.5 ->{
                resultText = "Under Weight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9 ->{
                resultText = "Normal"
                color = R.color.normal
            }
            bmi in 25.0..29.9 ->{
                resultText = "Over Weight"
                color = R.color.over_weight
            }
            bmi>29.9 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.text = resultText
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
    }
}