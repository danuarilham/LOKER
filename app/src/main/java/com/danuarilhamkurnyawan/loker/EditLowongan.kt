package com.danuarilhamkurnyawan.loker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditLowongan : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance("https://loker-7f8ef-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val reference = database.getReference("LowonganPekerjaan")

    private lateinit var etNamaPerusahaan: EditText
    private lateinit var etProfesi: EditText
    private lateinit var etGajiPerusahaan: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnSave: Button
    private var lowonganId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editlowongan)

        etNamaPerusahaan = findViewById(R.id.etnamaperusahaan)
        etProfesi = findViewById(R.id.etProfesi)
        etGajiPerusahaan = findViewById(R.id.editTextGaji)
        etDeskripsi = findViewById(R.id.editTextMessage)
        btnSave = findViewById(R.id.btpasang)

        // Retrieve data from the intent
        lowonganId = intent.getStringExtra("lowonganId")
        etNamaPerusahaan.setText(intent.getStringExtra("namaPerusahaan"))
        etProfesi.setText(intent.getStringExtra("profesi"))
        etGajiPerusahaan.setText(intent.getStringExtra("gajiPerusahaan"))
        etDeskripsi.setText(intent.getStringExtra("deskripsi"))

        btnSave.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {
        val updatedNamaPerusahaan = etNamaPerusahaan.text.toString()
        val updatedProfesi = etProfesi.text.toString()
        val updatedGajiPerusahaan = etGajiPerusahaan.text.toString()
        val updatedDeskripsi = etDeskripsi.text.toString()

        lowonganId?.let {
            val databaseRef = database.getReference("LowonganPekerjaan").child(it)
            val updatedData = mapOf(
                "idlowongan" to it,  // Ensure `idlowongan` is set correctly
                "namaPerusahaan" to updatedNamaPerusahaan,
                "profesi" to updatedProfesi,
                "gajiPerusahaan" to updatedGajiPerusahaan,
                "deskripsi" to updatedDeskripsi,
                "tanggalDibuat" to SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())  // Update with current date if needed
            )
            databaseRef.updateChildren(updatedData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after saving
                } else {
                    Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
