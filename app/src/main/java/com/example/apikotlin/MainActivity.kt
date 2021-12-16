 package com.example.apikotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.apikotlin.httpQueries.LoginRequest
import com.example.apikotlin.httpQueries.LoginResponse
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity : AppCompatActivity() {

     private lateinit var sessionManager:SessionManager
     private lateinit var apiClient:ApiClient

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email:TextInputLayout  = findViewById(R.id.email)
        val password: TextInputLayout = findViewById(R.id.password)

        val signIn: Button = findViewById(R.id.signIn)
        val signUp: Button = findViewById(R.id.signUp)


         apiClient = ApiClient()
         sessionManager = SessionManager(this)

        signUp.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }

        signIn.setOnClickListener {
            if(email.editText?.text.toString()!="" || password.editText?.text.toString()!=""){
                println(email.editText?.text.toString() + " "  + password.editText?.text.toString())
                apiClient.getApiService().auth(LoginRequest(email = email.editText?.text.toString(), password = password.editText?.text.toString()))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                        ) {
                           val loginResponse = response.body()
                            println(loginResponse?.token.toString() + " " + response.code() + " " + loginResponse)
                            if (response.code() == 200) {
                                if (loginResponse != null) {
                                    sessionManager.saveAuthToken(loginResponse.token)
                                    Toast.makeText(applicationContext,"ok", Toast.LENGTH_SHORT).show()
                                }

                            } else {
                                Toast.makeText(applicationContext,"Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            print(t.printStackTrace())
                        }

                    })
            }else{
                Toast.makeText(this,"Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }

    }

 }