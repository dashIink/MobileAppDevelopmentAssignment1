package com.example.assignment1mobile


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double.parseDouble


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get each view that we will be needing as a value to reference later
        val mP = findViewById(R.id.morgagePrincipal) as EditText
        val interestRate = findViewById(R.id.interestRate) as EditText
        val paymentPeriod = findViewById(R.id.paymentPeriod) as EditText
        val error = findViewById(R.id.errorMessage) as TextView

        // get reference to button
        val getMonthly = findViewById(R.id.getMonthly) as Button
        // set on-click listener for the calculation button
        getMonthly.setOnClickListener {
            // Display an invalid input message if the user has entered the values incorrectly
            try {
                //convert each entered text value to a string and then to a double. Given the way kotlin works
                //you cannot directly convert to double, so this is a simple work around
                val principal = parseDouble(mP.text.toString())
                var interest = parseDouble(interestRate.text.toString())
                //we ask for the interest as a percent, and so must convert is to a decimal before calculation
                interest *= 0.01
                val period = parseDouble(paymentPeriod.text.toString())

                //intent will take us to the next activity, bundle is used to store our variables to be accessed within that activity
                val i = Intent(this, CalculationResult::class.java)
                val bundle = Bundle()

                //Using Key value pairs, store the variables
                bundle.putDouble("principal", principal)
                bundle.putDouble("interest", interest)
                bundle.putDouble("period", period)
                i.putExtras(bundle)
                //enter the next activity
                startActivity(i)
            }
            catch (e: Exception){
                error.text = "Invalid input"
            }
        }


    }
}