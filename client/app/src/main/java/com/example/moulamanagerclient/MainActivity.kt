package com.example.moulamanagerclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
//import com.example.moulamanagerclient.ui.login.LoginActivity
import com.example.moulamanagerclient.ui.navbar.NavigationHost
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moulamanagerclient.data.repositories.ApiHeader
import com.example.moulamanagerclient.ui.navbar.NavigationHost
import com.example.moulamanagerclient.ui.theme.MoulamanagerclientTheme
import com.example.moulamanagerclient.utils.SharedPreferences

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val token = SharedPreferences.getKey(context=this, key="Authorization")
        token?.let{
            ApiHeader.setAccessToken(it)
        }

        super.onCreate(savedInstanceState)
        setContent {
            MoulamanagerclientTheme {
                NavigationHost(rememberNavController())
            }
        }
    }
}