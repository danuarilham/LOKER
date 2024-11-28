package com.danuarilhamkurnyawan.loker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class AdapterKirim(private var lowonganList: List<ModelLowongan>,
                      private val database: FirebaseDatabase
) : RecyclerView.Adapter<AdapterKirim.LowonganViewHolder>() {

    // ViewHolder untuk menghubungkan data dengan tampilan item
    inner class LowonganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNamaPerusahaan: TextView = itemView.findViewById(R.id.perusahaan)
        private val tvProfesi: TextView = itemView.findViewById(R.id.tvprofesi)
        private val tvGajiPerusahaan: TextView = itemView.findViewById(R.id.tvgaji)
        private val tvDeskripsi: TextView = itemView.findViewById(R.id.tvdeskripsi)
        private val tvTanggal: TextView = itemView.findViewById(R.id.tvtanggal)
        val sendButton: Button = itemView.findViewById(R.id.btnkirim)

        // Fungsi untuk mengisi data ke dalam item
        fun bind(lowongan: ModelLowongan) {
            tvNamaPerusahaan.text = lowongan.namaPerusahaan
            tvProfesi.text = lowongan.profesi
            tvGajiPerusahaan.text = "Rp."+lowongan.gajiPerusahaan
            tvDeskripsi.text = lowongan.deskripsi
            tvTanggal.text = "Dibuat Pada: "+lowongan.tanggalDibuat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LowonganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_kirim, parent, false)
        return LowonganViewHolder(view)
    }

    override fun getItemCount(): Int = lowonganList.size

    override fun onBindViewHolder(holder: LowonganViewHolder, position: Int) {
        val lowongan = lowonganList[position]
        holder.bind(lowongan)
        holder.sendButton.setOnClickListener {
            // Menambahkan logika untuk kirim lowongan (bisa menggunakan Intent atau API)
            val context = holder.itemView.context
            val intent = Intent(context, LamarPekerjaan::class.java)
            intent.putExtra("idlowongan", lowongan.idlowongan)
            context.startActivity(intent)
        }
    }


    // Fungsi untuk memperbarui data di adapter
    fun setData(newLowonganList: List<ModelLowongan>) {
        lowonganList = newLowonganList
        notifyDataSetChanged()
    }

}
