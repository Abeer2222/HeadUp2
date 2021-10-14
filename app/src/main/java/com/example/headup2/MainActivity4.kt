package com.example.headup2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity4 : AppCompatActivity() {
    var celedata: List<CelData.Datum>? = null
    var celebrities = ArrayList<ArrayList<String>>()
    var celebrity = ArrayList<String>()
    var timer: Long = 60000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getCelebrityDetails(onResult = {
                celedata = it
                val datumList = celedata
                for (d in datumList!!) {
                    celebrities += arrayListOf(
                        arrayListOf("${d.pk}",
                                   "${d.name}",
                                   "${d.taboo1}",
                                   "${d.taboo2}",
                                   "${d.taboo3}"))
                }
                celebrities.shuffle()
                CoroutineScope(Dispatchers.IO).launch {
                    val result = useradd()
                    withContext(Dispatchers.Main) {
                        tvname.text = result[1]
                        tvta1.text = result[2]
                        tvta2.text = result[3]
                        tvta3.text = result[4]
                        counttimer(timer)
                    }
                }
            })
        }

    }

    private fun counttimer(ctime: Long) {
        object : CountDownTimer(ctime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTime.text = "Time: ${millisUntilFinished / 1000}"
                timer = millisUntilFinished
            }

            override fun onFinish() {
                tvTime.text = "Time: --"
                timer = 60000
            }
        }.start()
    }

    private fun useradd(): ArrayList<String> {
        celebrity = celebrities[0]
        return celebrity
    }

    private fun getCelebrityDetails(onResult: (List<CelData.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getCelebrityDetails()?.enqueue(object :
                Callback<List<CelData.Datum>> {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("time", timer)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timer = savedInstanceState.getLong("time", 0)
    }
}