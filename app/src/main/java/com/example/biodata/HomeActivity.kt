package com.example.biodata

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var nama : String? = ""
    var gender : String? = ""
    var email : String? = ""
    var umur : String? = ""
    var telp : String? = ""
    var alamat : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ambilData()

        bt_info.setOnClickListener{ goToAbout() }
        bt_edit.setOnClickListener { goToEdit() }
        bt_dial.setOnClickListener { dialPhone(tv_phone.text.toString()) }
    }

    private fun ambilData(){
        val bundle = intent.extras
        nama = bundle?.getString("nama")
        gender = bundle?.getString("gender")
        email = bundle?.getString("email")
        umur = bundle?.getString("umur")
        telp = bundle?.getString("telp")
        alamat = bundle?.getString("alamat")
        tv_nama.text = nama
        tv_jk.text = gender
        tv_umur.text = umur
        tv_email.text = email
        tv_phone.text = telp
        tv_alamat.text = alamat
    }

    private fun goToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun dialPhone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    companion object {
        val REQUEST_CODE = 100
    }

    private fun goToEdit() {
        val intent = Intent(this, EditActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", nama)
        bundle.putString("gender", gender)
        bundle.putString("email", email)
        bundle.putString("umur", umur)
        bundle.putString("telp", telp)
        bundle.putString("alamat", alamat)
        intent.putExtras(bundle)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.extras

                nama = result?.getString("nama")
                gender = result?.getString("gender")
                email = result?.getString("email")
                umur = result?.getString("umur")
                telp = result?.getString("telp")
                alamat = result?.getString("alamat")

                tv_nama.text = nama
                tv_jk.text = gender
                tv_umur.text = umur
                tv_email.text = email
                tv_phone.text = telp
                tv_alamat.text = alamat
            }else{
                Toast.makeText(this, "Edit batal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
