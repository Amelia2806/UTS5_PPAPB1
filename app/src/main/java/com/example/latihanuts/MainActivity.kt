package com.example.LatihanUTS

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.LatihanUTS.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // Get Array
            val monthList = resources.getStringArray(R.array.month)

            // Dummy kehadiran
            val kehadiranList = arrayOf("Hadir Tepat Waktu", "Terlambat", "Izin")

            // jam dan tanggal
            val _tempCalendar: Calendar = Calendar.getInstance()
            val selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"

            // Spinner
            val adapterKehadiran = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                kehadiranList
            )
            adapterKehadiran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kehadiranSpinner.adapter = adapterKehadiran

            // Handle Spinner selection
            kehadiranSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedKehadiran = kehadiranList[position]
                    Toast.makeText(this@MainActivity, "Kehadiran dipilih: $selectedKehadiran", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            // Set onClick listener untuk submit button
            val submitButton: Button = findViewById(R.id.submit_button)
            submitButton.setOnClickListener {
                // Collect data
                val selectedKehadiran = kehadiranSpinner.selectedItem.toString()
                val keterangan = keteranganEdittext.text.toString()
                val selectedDate = "${datepicker.dayOfMonth} ${monthList[datepicker.month]} ${datepicker.year}"
                val selectedTime = "${timePicker.hour}:${timePicker.minute}"

                // Show collected data
                Toast.makeText(this@MainActivity, "Presensi berhasil\n$selectedDate\nJam: $selectedTime\nKehadiran: $selectedKehadiran\nKeterangan: $keterangan", Toast.LENGTH_LONG).show()
            }
        }
    }
}
