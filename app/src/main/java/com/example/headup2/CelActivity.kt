package com.example.headup2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CelActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var etCelename: EditText
    private lateinit var rvMain: RecyclerView

    var celList: List<CelData.Datum>? = null
    var arrayListResponse = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cel)
        btnAdd = findViewById(R.id.addBtn)
        btnSub = findViewById(R.id.btnSubmit)
        etCelename = findViewById(R.id.etCelename)
        rvMain = findViewById(R.id.rvMain)

        getCelebrityDetails(onResult = {
            celList = it
            val datumList = celList
            for (datum in datumList!!) {
                arrayListResponse += arrayListOf(arrayListOf("${datum.pk}",
                    "${datum.name}",
                    "${datum.taboo1}",
                    "${datum.taboo2}",
                    "${datum.taboo3}"))
            }
            rvMain.adapter = RVAdapter(arrayListResponse)
            rvMain.layoutManager = LinearLayoutManager(this)
        })

        btnAdd.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        btnSub.setOnClickListener {
            val celname = etCelename.text.toString()
            checkCelebrity(celname)
        }
    }

    private fun checkCelebrity(celname: String) {
        for (c in arrayListResponse.indices) {
            if (celname == arrayListResponse[c][1]) {
                val id = arrayListResponse[c][0]
                val celebrityn = arrayListResponse[c][1]
                val t1 = arrayListResponse[c][2]
                val t2 = arrayListResponse[c][3]
                val t3 = arrayListResponse[c][4]
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("name", celebrityn)
                intent.putExtra("t1", t1)
                intent.putExtra("t2", t2)
                intent.putExtra("t3", t3)
                intent.putExtra("id", id)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"$celname not found", Toast.LENGTH_LONG).show()
                Log.d("Main","$celname Not Found")

            }
        }
    }

    private fun getCelebrityDetails(onResult: (List<CelData.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getCelebrityDetails()?.enqueue(object : Callback<List<CelData.Datum>> {
                override fun onResponse(
                    call: Call<List<CelData.Datum>>,
                    response: Response<List<CelData.Datum>>,
                ) {
                    onResult(response.body())
                }

                override fun onFailure(call: Call<List<CelData.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }

}