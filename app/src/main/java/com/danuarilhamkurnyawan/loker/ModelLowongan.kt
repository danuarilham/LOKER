package com.danuarilhamkurnyawan.loker

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ModelLowongan (
    var idlowongan: String?="",
    var namaPerusahaan: String?=null,
    var profesi: String?=null,
    var gajiPerusahaan: String?=null,
    var deskripsi: String?=null,
    var tanggalDibuat: String? = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)
