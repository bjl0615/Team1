package com.example.team_project

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.team_project.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity(){

    private lateinit var binding : ActivityLoginBinding

    private val callback: (OAuthToken?, Throwable?) -> Unit = {token , error ->
        if(error != null) {
            Log.e("loginActivity" , "error $error")
        }else if(token != null) {
            //로그인 성공

            Log.e("loginActivity", "login in with kakao account token : $token")
        }
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this, "2233adf20972a7f833326d9607a219dc")

        binding.kakaoTalkLoginButton.setOnClickListener{
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                // 카카오톡 로그인

                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->

                    if(error != null) {
                        // 카카오톡 로그인 실패

                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if(token != null) {
                        Log.e("loginActivity" , "token== $token")
                        //로그인을 성공
                    }

                }
            }else {
                // 카카오 계정 로그인

                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}