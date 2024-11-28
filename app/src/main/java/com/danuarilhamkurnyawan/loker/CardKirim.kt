package com.danuarilhamkurnyawan.loker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class CardKirim : AppCompatActivity() {

    // Declare UI components
    private lateinit var perusahaanText: TextView
    private lateinit var profesiText: TextView
    private lateinit var gajiText: TextView
    private lateinit var deskripsiText: TextView
    private lateinit var tanggalText: TextView
    private lateinit var btnKirim: Button
    private lateinit var cardKirim: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_kirim) // Make sure this is your XML layout file

        // Initialize UI components by finding them by ID
        perusahaanText = findViewById(R.id.perusahaan)
        profesiText = findViewById(R.id.tvprofesi)
        gajiText = findViewById(R.id.tvgaji)
        deskripsiText = findViewById(R.id.tvdeskripsi)
        tanggalText = findViewById(R.id.tvtanggal)
        btnKirim = findViewById(R.id.btnkirim)
        cardKirim = findViewById(R.id.cardkirim)

        // Set text values (example)
        perusahaanText.text = "Nama Perusahaan"
        profesiText.text = "Software Engineer"
        gajiText.text = "Rp 10.000.000"
        deskripsiText.text = "Deskripsi pekerjaan"
        tanggalText.text = "2024-11-18"

        // Handle the button click event
        btnKirim.setOnClickListener {
            // This will switch to the LowonganPekerjaan activity
            val intent = Intent(this@CardKirim, LowonganPekerjaan::class.java)
            startActivity(intent)
        }
    }
}
