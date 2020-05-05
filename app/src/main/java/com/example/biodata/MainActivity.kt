package com.example.biodata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataSpinnerGender()

        val bt_simpan = findViewById<AppCompatButton>(R.id.bt_simpan)

        bt_simpan.setOnClickListener { validasiInput() }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun validasiInput(){
        namaInput = et_nama.text.toString()
        emailInput = et_email.text.toString()
        telpInput = et_telp.text.toString()
        alamatInput = et_alamat.text.toString()
        genderInput = spinnerGender.selectedItem.toString()
        when{
            namaInput.isEmpty() -> et_nama.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true) ->
                tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> et_email.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> et_telp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> et_alamat.error = "Alamat tidak boleh kosong"
            else -> {
                goToHome()
            }
        }
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

}