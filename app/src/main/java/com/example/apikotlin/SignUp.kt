package com.example.apikotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.apikotlin.httpQueries.SignUpRequest
import com.example.apikotlin.httpQueries.SignUpResponse
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {

    private lateinit var apiClient:ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val firstName: TextInputLayout = findViewById(R.id.name)
        val lastName: TextInputLayout = findViewById(R.id.lastName)
        val email: TextInputLayout = findViewById(R.id.createEmail)
        val pwd: TextInputLayout = findViewById(R.id.createPassword)
        val repeatPwd: TextInputLayout = findViewById(R.id.repeatPassword)

        apiClient = ApiClient()

        val signUp: Button = findViewById(R.id.newSignUp)
        val existProfile: Button = findViewById(R.id.haveProfile)

        existProfile.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        signUp.setOnClickListener {
            if(firstName.editText?.text.toString() == "" ||
                lastName.editText?.text.toString()  == "" ||
                email.editText?.text.toString() == "" ||
                pwd.editText?.text.toString() == "" ||
                repeatPwd.editText?.text.toString() == "" ){
                Toast.makeText(this,"Заполните поля", Toast.LENGTH_SHORT).show()
            }else{
                if(pwd.editText?.text.toString() == repeatPwd.editText?.text.toString()){

                apiClient.getApiService().signUp(SignUpRequest(email =  email.editText?.text.toString(),
                                                                password = repeatPwd.editText?.text.toString(),
                                                                firstName = firstName.editText?.text.toString(),
                                                                lastName = lastName.editText?.text.toString()))
                    .enqueue(object : Callback<Void>{
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if(response.code() == 201){
                                Toast.makeText(applicationContext, "Пользователь зарегистирован", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                            }

                        }
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            print(t.printStackTrace())
                        }
                    })
                }else {
                    Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}