package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ui.theme.ComposeTestingTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUITest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            ComposeTestingTheme {
                LoginScreen {}
            }
        }
    }

    @Test
    fun verifyAllViewsAreExists() {
        rule.onNodeWithTag("LoginLabel").assertExists()
        rule.onNodeWithTag("email").assertExists()
        rule.onNodeWithTag("password").assertExists()
        rule.onNodeWithTag("LoginButton").assertExists()
    }

    @Test
    fun verifyEmailAddressValidation() {
        val validEmail = "test@example.com"
        rule.onNodeWithTag("email").apply {
            performTextInput(validEmail)
            assert(hasText(validEmail))
        }
    }

    @Test
    fun loginUser() {
        rule.onNodeWithTag("email").performTextInput("myemail@example.com")
        rule.onNodeWithTag("password").performTextInput("12345678")
        rule.onNodeWithTag("LoginButton").performClick()
    }
}
