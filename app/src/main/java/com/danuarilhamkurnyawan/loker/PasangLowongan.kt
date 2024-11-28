package com.danuarilhamkurnyawan.loker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class PasangLowongan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance("https://loker-7f8ef-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val reference = database.getReference("LowonganPekerjaan")

    lateinit var etNamaPerusahaan: EditText
    private lateinit var etProfesi: EditText
    private lateinit var etGajiPerusahaan: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnKirimLowongan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasang_lowongan)

        // Inisialisasi View
        etNamaPerusahaan = findViewById(R.id.etnamaperusahaan)
        etProfesi = findViewById(R.id.etProfesi)
        etGajiPerusahaan = findViewById(R.id.editTextGaji)
        etDeskripsi = findViewById(R.id.editTextMessage)
        btnKirimLowongan = findViewById(R.id.btpasang)

        // Aksi Tombol Kirim Lowongan
        btnKirimLowongan.setOnClickListener {
            kirimLowongan()
        }
    }

    private fun kirimLowongan() {
        // Ambil input dari pengguna
        val namaPerusahaan = etNamaPerusahaan.text.toString().trim()
        val profesi = etProfesi.text.toString().trim()
        val gajiPerusahaan = etGajiPerusahaan.text.toString().trim()
        val deskripsi = etDeskripsi.text.toString().trim()

        // Validasi input
        if (namaPerusahaan.isEmpty() || profesi.isEmpty() || gajiPerusahaan.isEmpty() || deskripsi.isEmpty()) {
            Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            return
        }


        // Buat ID unik untuk lowongan baru
        val lowonganId = reference.push().key ?: return
        val idLowongan = lowonganId.toString()

        // Buat objek data lowongan
        val lowongan = ModelLowongan(idLowongan, namaPerusahaan, profesi, gajiPerusahaan, deskripsi)

        // Simpan data ke Firebase
        reference.child(lowonganId).setValue(lowongan).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Lowongan berhasil dikirim", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman baru setelah data disimpan
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Gagal mengirim lowongan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}