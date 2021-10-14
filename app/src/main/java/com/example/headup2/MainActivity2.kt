package com.example.headup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var btnAdd: Button
    lateinit var btnBack: Button
    lateinit var etname: EditText
    lateinit var ettaboo1: EditText
    lateinit var ettaboo2: EditText
    lateinit var ettaboo3: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnAdd = findViewById(R.id.btnadd)
        btnBack = findViewById(R.id.btnBack)
        etname = findViewById(R.id.etname)
        ettaboo1 = findViewById(R.id.ettab1)
        ettaboo2 = findViewById(R.id.ettab2)
        ettaboo3 = findViewById(R.id.ettab3)

        btnAdd.setOnClickListener {
            var cel = CelData.Datum(etname.text.toString(),
                ettaboo1.text.toString(),
                ettaboo2.text.toString(),
                ettaboo3.text.toString())
            addCelebrityDetails(cel, onResult = {
                etname.setText("")
                ettaboo1.setText("")
                ettaboo2.setText("")
                ettaboo3.setText("")
            })
        }

        btnBack.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addCelebrityDetails(cel: CelData.Datum, onResult: () -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addCelebrityDetails(cel)?.enqueue(object
                : Callback<CelData.Datum?> {
                override fun onResponse(
                    call
                    : Call<CelData.Datum?>,
                    response: Response<CelData.Datum?>,
                ) {
                    Toast.makeText(applicationContext, "celebrity added",
                        Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CelData.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Something went wrong",
                        Toast.LENGTH_LONG)
                        .show()
                }

            })
        }
    }
}