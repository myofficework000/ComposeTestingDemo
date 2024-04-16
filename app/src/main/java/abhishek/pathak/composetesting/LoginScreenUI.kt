package abhishek.pathak.composetesting

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreenUI(
    onLogin: ()-> Unit
){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.testTag("LoginLabel"),
                text = "Login",
                fontSize = 32.sp,
                color = Color.Blue,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            SpacerLoginSmall()

            EmailLoginTextField()
            SpacerLoginSmall()

            PasswordLoginTextField()
            SpacerLoginSmall()

            SimpleLoginTextButton("Login", modifier = Modifier.testTag("LoginButton")) {
                onLogin
            }
        }
    }
}
@Composable
fun SpacerLoginSmall() {
    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun EmailLoginTextField() {

    OutlinedTextField(
        value = emaillogin.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = {
            emaillogin.value = it
            validateEmail(email = it)
        },
        isError = isEmailValid.value,
        label = { Text(text = "Email") },
        modifier = Modifier.testTag("email")
    )
}
@Composable
private fun PasswordLoginTextField() {

    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = passwordlogin,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { passwordlogin = it },
        label = { Text(text = "Password") },
        modifier = Modifier.testTag("password")
    )
}
@Composable
fun SimpleLoginTextButton(
    buttonMessage: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    textColor: Color = Color.Unspecified,
    fontWeight: FontWeight? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier,
        enabled = enabled,
        colors = buttonColors
    ) {
        Text(
            text = buttonMessage,
            modifier = textModifier,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}

fun isValidLogin(username: String, password: String): Boolean {
    // Implement your login validation logic here
    return username.isNotEmpty() && password.isNotEmpty()
}

fun showToast(message: String,context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

var loginUser: LoginUser = LoginUser()
var emaillogin: MutableState<String> = mutableStateOf(loginUser.emaillogin)
var passwordlogin: String by mutableStateOf("")

data class LoginUser(
    var emaillogin: String="",
    var passwordlogin: String=""
)
