package com.danuarilhamkurnyawan.loker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Fungsi untuk pindah ke halaman Lowongan Pekerjaan
    fun pindah1(view: View) {
        // Navigasi ke Activity Lowongan Pekerjaan
        val intent = Intent(this, LowonganPekerjaan::class.java)
        startActivity(intent)
    }

    // Fungsi untuk pindah ke halaman Pasang Lowongan
    fun pindah2(view: View) {
        // Navigasi ke Activity Pasang Lowongan
        val intent = Intent(this, PasangLowongan::class.java)
        startActivity(intent)
    }

    // Fungsi untuk pindah ke halaman Tips Karier
    fun pindah3(view: View) {
        // Navigasi ke Activity Tips Karier
        val intent = Intent(this, tipskarir::class.java)
        startActivity(intent)
    }

    fun pindah4(view: View) {
        // Navigasi ke Activity Data Lowongan
        val intent = Intent(this, DataLowongan::class.java)
        startActivity(intent)

    }
}

