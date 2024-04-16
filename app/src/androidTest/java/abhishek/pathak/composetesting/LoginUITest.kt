package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ui.theme.ComposeTestingTheme
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUITest {

    @get:Rule
    val rule = createComposeRule()

    private lateinit var email: SemanticsNodeInteraction
    private lateinit var password: SemanticsNodeInteraction
    private lateinit var loginButton: SemanticsNodeInteraction
    private lateinit var loginLabel: SemanticsNodeInteraction

    @Before
    fun setUp() {
        rule.setContent {
            ComposeTestingTheme {
                LoginScreen {}
            }
        }

        with(rule) {
            email = onNodeWithTag("email")
            password = onNodeWithTag("password")
            loginButton = onNodeWithTag("LoginButton")
            loginLabel = onNodeWithTag("LoginLabel")
        }
    }

    @Test
    fun verifyAllViewsAreExists() {
        with(rule) {
            email.assertExists()
            password.assertExists()
            loginLabel.assertExists()
            loginButton.assertExists()
        }
    }

    @Test
    fun verifyEmailAddressValidation() {
        with(email) {
            performTextInput(VALID_EMAIL)
            assert(hasText(VALID_EMAIL))
            assertTrue(!isEmailValid.value)
            assertTrue(emailErrMsg.value == "")
            performTextClearance()
        }
    }

    @Test
    fun verifyEmailAddressValidationWithInvalidInput() {

        val invalidEmail = "myofficework"

        with(email) {
            performTextInput(invalidEmail)
            assert(hasText(invalidEmail))
            assertNotEquals(VALID_EMAIL, hasText(invalidEmail))
            performTextClearance()
        }
    }


    @Test
    fun verifyConfirmPasswordValidationWithValidInput() {

        val input = "123456"

        with(password) {
            performTextInput(input)
            assert(hasText(input))
            assertTrue(!isConfirmPasswordInvalid.value)
            assertTrue(emailErrMsg.value == "")
            performTextClearance()
        }
    }

    @Test
    fun loginUser() {
        email.performTextInput("myemail@example.com")
        password.performTextInput("12345678")
        loginButton.performClick()
    }

    private companion object {

        const val VALID_EMAIL = "myofficework@gmail.com"
    }
}
