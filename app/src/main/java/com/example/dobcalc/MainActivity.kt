package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
   private var displayDate:TextView? = null
    private var tvAgeInMinutes:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayDate = findViewById(R.id.tv_date)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        val btnDatePicker:AppCompatButton = findViewById(R.id.btn_date)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                displayDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }
                }

            },
            year,
            month,
            day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}