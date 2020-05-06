package com.example.biodata

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*


class EditActivity : AppCompatActivity() {

    private var namaInput : String? = ""
    private var emailInput : String? = ""
    private var telpInput : String? = ""
    private var alamatInput : String? = ""
    private var umurInput : String? = ""
    private var genderInput : String? = ""

    var nama : String = ""
    var email : String = ""
    var telp : String = ""
    var umur : String = ""
    var alamat : String = ""
    var gender : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intentData = intent.extras
        namaInput = intentData?.getString("nama")
        emailInput = intentData?.getString("email")
        telpInput = intentData?.getString("telp")
        genderInput = intentData?.getString("gender")
        umurInput = intentData?.getString("umur")
        alamatInput = intentData?.getString("alamat")

        var array = resources.getStringArray(R.array.jk)
        var i = 0
        var selection = 0

        for (item in array) {
            if (item.equals(genderInput)) {
                selection = i
            }
            i++
        }

        et_nama2.setText(namaInput)
        spinnerGender2.setSelection(selection)
        et_umur2.setText(umurInput)
        et_email2.setText(emailInput)
        et_telp2.setText(telpInput)
        et_alamat2.setText(alamatInput)

        bt_simpan2.setOnClickListener { saveData() }
    }

    override fun onBackPressed() {
        retakeData()
        if (!nama.equals(namaInput) || !email.equals(emailInput) || !umur.equals(umurInput) || !gender.equals(genderInput) || !telp.equals(telpInput) || !alamat.equals(alamatInput)) {
            AlertDialog.Builder(this)
                .setMessage("Data yang anda inputkan tidak akan disimpan, apakah anda yakin?")
                .setCancelable(false)
                .setPositiveButton(
                    "Ya"
                ) { dialog, id -> this.finish() }
                .setNegativeButton("Tidak", null)
                .show()
        }
        else {
            finish()
        }
    }

    private fun saveData(){
        retakeData()
        when{
            nama.isEmpty() -> et_nama2.error = "Nama tidak boleh kosong"
            gender.equals("Pilih Jenis Kelamin", ignoreCase = true) ->
                tampilToast("Jenis Kelamin harus dipilih")
            umur.isEmpty() -> et_umur2.error = "Umur tidak boleh kosong"
            email.isEmpty() -> et_email2.error = "Email tidak boleh kosong"
            telp.isEmpty() -> et_telp2.error = "Telp tidak boleh kosong"
            alamat.isEmpty() -> et_alamat2.error = "Alamat tidak boleh kosong"

            else -> {
                val result = Intent()
                val bundle = Bundle()
                bundle.putString("nama", nama)
                bundle.putString("gender", gender)
                bundle.putString("email", email)
                bundle.putString("umur", umur)
                bundle.putString("telp", telp)
                bundle.putString("alamat", alamat)
                result.putExtras(bundle)

                setResult(Activity.RESULT_OK, result)
            }
        }
        finish()
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.jk, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter
    }

    private fun retakeData() {
        nama = et_nama2.text.toString()
        email = et_email2.text.toString()
        telp = et_telp2.text.toString()
        umur = et_umur2.text.toString()
        alamat = et_alamat2.text.toString()
        gender = spinnerGender2.selectedItem.toString()
    }
}
