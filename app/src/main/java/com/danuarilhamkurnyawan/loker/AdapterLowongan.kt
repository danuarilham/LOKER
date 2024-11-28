package com.danuarilhamkurnyawan.loker

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class AdapterLowongan(private val context: Context,
                      private var lowonganList: List<ModelLowongan>,
                      private val database: FirebaseDatabase
) : RecyclerView.Adapter<AdapterLowongan.LowonganViewHolder>() {

    // ViewHolder untuk menghubungkan data dengan tampilan item
    inner class LowonganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNamaPerusahaan: TextView = itemView.findViewById(R.id.perusahaan)
        private val tvProfesi: TextView = itemView.findViewById(R.id.tvprofesi)
        private val tvGajiPerusahaan: TextView = itemView.findViewById(R.id.tvgaji)
        private val tvDeskripsi: TextView = itemView.findViewById(R.id.tvdeskripsi)
        private val tvTanggal: TextView = itemView.findViewById(R.id.tvtanggal)
        val etedit: TextView = itemView.findViewById(R.id.etedit)
        val btnhapus: Button = itemView.findViewById(R.id.hapus)

        // Fungsi untuk mengisi data ke dalam item
        fun bind(lowongan: ModelLowongan) {
            tvNamaPerusahaan.text = lowongan.namaPerusahaan
            tvProfesi.text = lowongan.profesi
            tvGajiPerusahaan.text = lowongan.gajiPerusahaan
            tvDeskripsi.text = lowongan.deskripsi
            tvTanggal.text = "Dibuat Pada: "+lowongan.tanggalDibuat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LowonganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_lowongan, parent, false)
        return LowonganViewHolder(view)
    }

    override fun getItemCount(): Int = lowonganList.size

    override fun onBindViewHolder(holder: LowonganViewHolder, position: Int) {
        val lowongan = lowonganList[position]
        holder.bind(lowongan)

        holder.etedit.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditLowongan::class.java)

            // Pass data to the edit activity
            intent.putExtra("lowonganId", lowongan.idlowongan)
            intent.putExtra("namaPerusahaan", lowongan.namaPerusahaan)
            intent.putExtra("profesi", lowongan.profesi)
            intent.putExtra("gajiPerusahaan", lowongan.gajiPerusahaan)
            intent.putExtra("deskripsi", lowongan.deskripsi)
            intent.putExtra("tanggalDibuat", lowongan.tanggalDibuat)

            context.startActivity(intent)
        }
        holder.btnhapus.setOnClickListener {
            showDeleteAccountConfirmation(position, lowongan.idlowongan.toString())
        }
    }


    // Fungsi untuk memperbarui data di adapter
    fun setData(newLowonganList: List<ModelLowongan>) {
        lowonganList = newLowonganList
        notifyDataSetChanged()
    }

    private fun showDeleteAccountConfirmation(position: Int, lowonganId: String) {
        val dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Konfirmasi Hapus")
            .setMessage("Apakah anda yakin ingin menghapus lowongan ini secara permanen?")
            .setPositiveButton("Ya") { dialog, _ ->
                //delete function
                val lowonganRef = database.getReference("LowonganPekerjaan").child(lowonganId)
                lowonganRef.removeValue()

                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

        dialogBuilder.create().show()
    }

}
