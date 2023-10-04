package com.example.assignment1mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class CalculationResult  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculation_result)

        //Get the bundle we created earlier, and extract the values
        val bundle = intent.extras
        val principal = bundle?.getDouble("principal");
        val interest = bundle?.getDouble("interest");
        val period = bundle?.getDouble("period");

        // convert to a monthly basis
        val months = period?.times(12)
        val interestPerMonth = interest?.div(12)






        val goBack = findViewById(R.id.backButton) as Button
        val result = findViewById(R.id.resultsView) as TextView

        // set the result text to the result value using a function. Multiply and divide by 100.0 in order to round to the nearest
        // cent using roundToInt
        result.text = "$" + (calculateEMI(principal, interestPerMonth, months)*100.0).roundToInt()/100.0

        //go back the the previous screen
        goBack.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i);
        }



    }
    //perform the calculation. pow is used to the compute the power of a value.
    private fun calculateEMI(principal: Double?, interest: Double?, months: Double?): Double {
        return principal!! * ((interest!! * (1 + interest).pow(months!!)) / ((1 + interest).pow(
            months
        ) - 1))
    }
}