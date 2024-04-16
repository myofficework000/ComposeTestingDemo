package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ui.theme.ComposeTestingTheme
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScrenUITest {
    @get:Rule
    val rule = createComposeRule()
    private lateinit var email: SemanticsNodeInteraction
    private lateinit var password: SemanticsNodeInteraction
    private lateinit var loginButton: SemanticsNodeInteraction


    @Before
    fun setUp() {
        rule.setContent {
            ComposeTestingTheme {
                LoginScreenUI {}
            }
        }
        with(rule){
            email = onNodeWithTag("email")
            password = onNodeWithTag("password")
            loginButton = onNodeWithTag("LoginButton")


        }
    }

    @Test
    fun verifyAllViewsAreExists() {
        with(rule) {
            email.assertExists()
            password.assertExists()
            loginButton.assertExists()
        }
    }
    @Test
    fun loginUser() {
        email.performTextInput(VALID_EMAIL)
        password.performTextInput("12345678")
        loginButton.performClick()



    }
    private companion object {
        const val VALID_EMAIL = "myofficework@gmail.com"
    }

}