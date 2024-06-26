package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ValidateRegistration.isValidRegistrationInput
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun RegistrationPreview() {
    RegistrationForm {

    }
}

@Composable
fun RegistrationForm(
    onRegister: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.testTag("RegisterLabel"),
                text = "Register",
                fontSize = 32.sp,
                color = Color.Blue,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            SpacerSmall()

            //username
            UserTextField()
            SpacerSmall()

            //email input field
            EmailTextField()
            SpacerSmall()

            //
            TextFieldWithNumbers()
            SpacerSmall()

            //password input field
            PasswordTextField()
            SpacerSmall()

            //confirm password input field
            ConfirmPasswordTextField()
            SpacerSmall()

            //register button
            SimpleTextButton("Register", modifier = Modifier.testTag("RegisterButton")) {
                onRegister
            }
        }
    }
}

fun buttonClick(context: Context) {

    if (isValidRegistrationInput(
            username = username.value,
            password = password,
            confirmPassword = confirmPassword
        )
    ) {
        Log.d("TAG", username.value)
        Log.d("TAG", password)
        Log.d("TAG", confirmPassword)
        showToast(context = context, context.getString(R.string.registration_success))
    } else {
        showLongToast(context = context, context.getString(R.string.registration_fail))
    }
}

fun showToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun showLongToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}

@Composable
fun SimpleTextButton(
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

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.padding(4.dp))
}

@Composable
fun RegisterButton() {
    val context = LocalContext.current
    Button(
        onClick = { buttonClick(context = context) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "Register", fontSize = 16.sp)
    }
}

@Composable
fun UserTextField() {
    OutlinedTextField(
        value = username.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "userIcon") },
        onValueChange = { username.value = it },
        label = { Text(text = "Username") },
        modifier = Modifier.testTag("username")
    )
}


@Composable
fun EmailTextField() {

    OutlinedTextField(
        value = email.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = {
            email.value = it
            validateEmail(email = it)
        },
        isError = isEmailValid.value,
        label = { Text(text = "Email") },
        modifier = Modifier.testTag("email")
    )
}

@Composable
fun ConfirmPasswordTextField() {
    OutlinedTextField(
        value = confirmPassword,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = {
            confirmPassword = it
            validConfirmPassword(password = password, confirmPassword = confirmPassword)
        },
        isError = isConfirmPasswordInvalid.value,
        label = { Text(text = "Confirm Password") },
        modifier = Modifier.testTag("confirmPassword")
    )
}

@Composable
fun InputFields() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleTextField()
        Spacer(modifier = Modifier.padding(4.dp))
        LabelAndPlaceHolder()
        Spacer(modifier = Modifier.padding(4.dp))
        OutlineTextField()
        Spacer(modifier = Modifier.padding(4.dp))
        TextFieldWithIcons()
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.padding(4.dp))
        TextFieldWithNumbers()
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { newText ->
        text = newText
    })
}

@Composable
fun LabelAndPlaceHolder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { text = it },
        label = { Text(text = "Username") },
        placeholder = { Text(text = "username") }
    )
}

@Composable
fun OutlineTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = "Enter Your Name") }
    )
}

@Composable
fun TextFieldWithIcons() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = { text = it },
        label = { Text(text = "Email") }
    )
}

@Composable
fun TextFieldWithNumbers() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = { text = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "phoneIcon") },
        label = { Text(text = "Mobile Number") },
        modifier = Modifier.testTag("mobile")
    )
}

@Composable
private fun PasswordTextField() {

    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { password = it },
        label = { Text(text = "Password") },
        modifier = Modifier.testTag("password")
    )
}

fun validateEmail(email: String) {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        isEmailValid.value = true
        emailErrMsg.value = "Input proper email id"
    } else {
        isEmailValid.value = false
        emailErrMsg.value = ""
    }
}

fun validConfirmPassword(password: String, confirmPassword: String) {
    if (password != confirmPassword) {
        isConfirmPasswordInvalid.value = true
        emailErrMsg.value = "Password is not matched"
    } else {
        isConfirmPasswordInvalid.value = false
        emailErrMsg.value = ""
    }
}

var regUser: RegisterUser = RegisterUser()
var username: MutableState<String> = mutableStateOf(regUser.name)
var password: String by mutableStateOf("")
var confirmPassword: String by mutableStateOf("")
var isConfirmPasswordInvalid: MutableState<Boolean> = mutableStateOf(false)
var email: MutableState<String> = mutableStateOf(regUser.email)
var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
var emailErrMsg: MutableState<String> = mutableStateOf("")

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)

