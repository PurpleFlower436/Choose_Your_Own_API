package com.example.choose_your_own_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    var Rickandmorty_pic_url = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Rickandmorty_pic_url = getRick_and_morty_Pic()
        Log.d("Rickandmorty_pic_url", "rick and morty pic url is set")
        val button: Button = findViewById<Button>(R.id.get_random_rick_and_morty_pic)
        val imageView = findViewById<ImageView>(R.id.rick_and_morty_pic)

        getNextImage(button, imageView)


    }



    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getRick_and_morty_Pic()

            Glide.with(this)
                .load(Rickandmorty_pic_url)
                .fitCenter()
                .into(imageView)
        }
    }


    private fun getRick_and_morty_Pic() {
        var randomInt = Random.nextInt(827)
        val client = AsyncHttpClient()
        val characterJson = "https://rickandmortyapi.com/api/character/" + randomInt
        client[characterJson, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Rick and morty", "response successful$json")

                Rickandmorty_pic_url = json.jsonObject.getString("image")

            }







            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Oh no! No rick and morty picture", errorResponse)
            }
        }]

    }
}