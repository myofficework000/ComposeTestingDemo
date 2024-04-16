package abhishek.pathak.composetesting
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
private fun LoginScreenUIPrev() {

    LoginScreen{}
}

@Composable
fun LoginScreen(    onLogin: () -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            color = Color.Blue,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.testTag("LoginLabel")
        )
        SpacerSmallLogin()

        EmailTextField(email) { email = it }
        SpacerSmallLogin()

        PasswordTextField(password) { password = it }
        SpacerSmallLogin()

        SimpleTextButton("Login") {
        }
    }
}


@Composable
fun EmailTextField(email: TextFieldValue, onEmailChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = email,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = { onEmailChange(it) },
        label = { Text(text = "Email") },
        modifier = Modifier.testTag("email")
    )
}

@Composable
fun PasswordTextField(password: TextFieldValue, onPasswordChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = password,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { onPasswordChange(it) },
        label = { Text(text = "Password") },
        modifier = Modifier.testTag("password")
    )
}

@Composable
fun SimpleTextButton(buttonMessage: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.testTag("LoginButton"),
        colors = ButtonDefaults.buttonColors(Color.Blue)
    ) {
        Text(text = buttonMessage, color = Color.White)
    }
}

@Composable
fun SpacerSmallLogin() {
    Spacer(modifier = Modifier.padding(4.dp))
}

fun showToastLogin(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

@Composable
fun showToastMessageLogin(context: Context, message: String) {
    showToastLogin(context, message)
}

fun showLongToastLogin(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
