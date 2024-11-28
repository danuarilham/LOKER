package com.danuarilhamkurnyawan.loker

import android.os.Bundle
import android.service.autofill.UserData
import android.view.Display.Mode
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class LamarPekerjaan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lamar_pekerjaan)

        // Inisialisasi view
        val etNamaLengkap = findViewById<EditText>(R.id.etNamaLengkap)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val etUmur = findViewById<EditText>(R.id.etUmur)
        val etTelepon = findViewById<EditText>(R.id.etTelepon)
        val etAlamat = findViewById<EditText>(R.id.etAlamat)
        val etDeskripsi = findViewById<EditText>(R.id.etDeskripsi)
        val etportofolio = findViewById<EditText>(R.id.etportofolio)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        // Firebase Realtime Database instance
        val database =
            FirebaseDatabase.getInstance("https://loker-7f8ef-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val userRef = database.getReference("Lamaran")

        // Set OnClickListener untuk tombol submit
        btnSubmit.setOnClickListener {
            val namaLengkap = etNamaLengkap.text.toString()
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val jenisKelamin = findViewById<RadioButton>(selectedGenderId)?.text.toString()
            val umur = etUmur.text.toString().toIntOrNull()
            val telepon = etTelepon.text.toString()
            val alamat = etAlamat.text.toString()
            val deskripsi = etDeskripsi.text.toString()
            val portofolio = etportofolio.text.toString()

            if (namaLengkap.isEmpty() || jenisKelamin.isEmpty() || umur == null || telepon.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(this, "Harap isi semua kolom dengan benar", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Buat objek UserData
            val modeldata = ModelData(namaLengkap, jenisKelamin, umur, telepon, alamat, deskripsi, portofolio)

            // Simpan ke Realtime Database
            val newUserRef = userRef.push() // generate unique ID
            newUserRef.setValue(modeldata)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish() // Tutup form
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show()
                }

        }
    }
}