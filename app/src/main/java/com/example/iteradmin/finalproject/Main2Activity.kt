package com.example.iteradmin.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class Main2Activity : AppCompatActivity() {
    private val user="123abcd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val e=findViewById<EditText>(R.id.city)
        val b=findViewById<Button>(R.id.wheather)
        val t=findViewById<TextView>(R.id.report)
        b.setOnClickListener{
            val str:String=e.text.toString()
            val url="http://api.openweathermap.org/data/2.5/weather?q=${str}&appid=b6907d289e10d714a6e88b30761fae22"
            val queue=Volley.newRequestQueue(this)

            val jsonObjectRequest:JsonObjectRequest=
                    JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
                        response->
                        val coord:JSONObject=response.getJSONObject("coord")
                        val weather:JSONArray=response.getJSONArray("weather")
                        val name:String=response.getString("code")

                        val report:String="Lattitude :"+coord.getString("lat")+
                                "Longitude :"+coord.getString("lon")+
                                ("\n description :"+weather.getJSONObject(0).
                                        getString("description")+"\n name :"+name)
                        t.text=report
                    },
                            Response.ErrorListener {

                            })
            queue.add(jsonObjectRequest)


        }

    }



}
