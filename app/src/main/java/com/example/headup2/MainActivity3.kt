package com.example.headup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var delBtn: Button
    lateinit var updBtn: Button
    lateinit var backBtn: Button
    lateinit var etname: EditText
    lateinit var ettaboo13: EditText
    lateinit var ettaboo23: EditText
    lateinit var ettaboo33: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        delBtn=findViewById(R.id.btnDel)
        updBtn=findViewById(R.id.btnUpd)
        backBtn=findViewById(R.id.buttonback)
        etname=findViewById(R.id.etname3)
        ettaboo13=findViewById(R.id.ettaboo13)
        ettaboo23=findViewById(R.id.ettaboo23)
        ettaboo33=findViewById(R.id.ettaboo33)
        val name3=intent.getStringExtra("name")
        val id= intent.getStringExtra("id").toString().toInt()
        val t1=intent.getStringExtra("t1")
        val t2=intent.getStringExtra("t2")
        val t3=intent.getStringExtra("t3")
        etname.setText(name3)
        ettaboo13.setText(t1)
        ettaboo23.setText(t2)
        ettaboo33.setText(t3)

        btnUpd.setOnClickListener {
            val cel=CelData.Datum(etname.text.toString(),
                ettaboo13.text.toString(),
                ettaboo23.text.toString(),
                ettaboo33.text.toString())
            updateCelebrity(id,cel)
        }

        btnDel.setOnClickListener {
            deleteCelebrity(id)
        }

        backBtn.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun deleteCelebrity(id: Int) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.delCelebrityDetails(id)?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext,"Celebrity Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun updateCelebrity(id: Int, cel: CelData.Datum) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.upDateCelebrityDetails(id,cel)?.enqueue(object : Callback<CelData.Datum> {
                override fun onResponse(call: Call<CelData.Datum>, response: Response<CelData.Datum>) {
                    Toast.makeText(applicationContext,"Celebrity Update", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CelData.Datum>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}