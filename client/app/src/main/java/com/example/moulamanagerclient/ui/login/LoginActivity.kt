package com.example.moulamanagerclient.ui.login

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.ui.theme.Colors
import com.example.moulamanagerclient.utils.SharedPreferences


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginActivity(viewModel: LoginViewModel = viewModel()) {
    val loginResult by viewModel.loginResult.collectAsState()
    val inputPassword by viewModel.inputPassword.collectAsState()
    val inputUsername by viewModel.inputUsername.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors =  TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Colors.BLACK_0,
                    titleContentColor = Colors.WHITE,
                ),
                title = {
                    Text("MoulaManager")
                }
            )
        },
        content = {paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = Colors.BLACK_1
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .wrapContentSize()
                            .border(width = 1.dp, color = colorResource(R.color.black_3), shape = RoundedCornerShape(2) )
                            .padding(20.dp)
                    ){
                        Text(modifier = Modifier.drawBehind {
                            val strokeWidthPx = 1.dp.toPx()
                            val verticalOffset = size.height - 2.sp.toPx()

                            drawLine(
                                color = Colors.YELLOW_1,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        },
                            fontSize = 36.sp,
                            color = Colors.WHITE,
                            text = "Sign-in")
                        OutlinedTextField(
                            textStyle = TextStyle(
                                color = Colors.BLACK_4,
                                textDecoration = TextDecoration.None
                            ),
                            value = inputUsername,
                            onValueChange = { viewModel.setUsername(it)},
                            label = { Text(text = "Username") },
                        )
                        OutlinedTextField(
                            textStyle = TextStyle(
                                color = Colors.BLACK_4,
                                textDecoration = TextDecoration.None
                            ),
                            value = inputPassword,
                            onValueChange = { viewModel.setPassword(it)},
                            label = { Text(text = "Password") },
                        )
                        OutlinedButton(
                            shape = RoundedCornerShape(10),
                            border = BorderStroke(1.dp, Colors.YELLOW_1),
                            onClick = {
                                viewModel.performLogin(context=context)
                            }
                        ) {
                            Text("Login", color = Colors.WHITE)
                        }
                        loginResult?.let { result: LoginResponse ->
                            Text("Login Result: ${result.token}", color = Colors.WHITE) // Replace someProperty with the actual property in LoginResponse
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginActivity()
}
