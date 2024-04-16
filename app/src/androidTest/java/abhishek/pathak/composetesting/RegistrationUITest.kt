package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ui.theme.ComposeTestingTheme
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationUITest {

    @get:Rule
    val rule = createComposeRule()

    private lateinit var username: SemanticsNodeInteraction
    private lateinit var email: SemanticsNodeInteraction
    private lateinit var mobile: SemanticsNodeInteraction
    private lateinit var password: SemanticsNodeInteraction
    private lateinit var confirmPassword: SemanticsNodeInteraction
    private lateinit var registerButton: SemanticsNodeInteraction
    private lateinit var registerlable: SemanticsNodeInteraction

    @Before
    fun setUp() {
        rule.setContent {
            ComposeTestingTheme {
                RegistrationForm {}
            }
        }

        with(rule) {
            username = onNodeWithTag("username")
            email = onNodeWithTag("email")
            mobile = onNodeWithTag("mobile")
            password = onNodeWithTag("password")
            confirmPassword = onNodeWithTag("confirmPassword")
            registerButton = onNodeWithTag("RegisterButton")
            registerlable = onNodeWithTag("RegisterLabel")
        }
    }

    @Test
    fun verifyAllViewsAreExists() {
        with(rule) {
            username.assertExists()
            email.assertExists()
            mobile.assertExists()
            password.assertExists()
            confirmPassword.assertExists()
            registerlable.assertExists()
            registerButton.assertExists()
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

        val validEmail = "myofficework"

        with(email) {
            performTextInput(validEmail)
            assert(hasText(validEmail))
            assertTrue(isEmailValid.value)
            assertTrue(emailErrMsg.value == "Input proper email id")
            performTextClearance()
        }
    }

    @Test
    fun verifyConfirmPasswordValidationWithValidInput() {

        val input = "123456"

        with(confirmPassword) {
            performTextInput(input)
            assert(hasText(input))
            assertTrue(!isConfirmPasswordInvalid.value)
            assertTrue(emailErrMsg.value == "")
            performTextClearance()
        }
    }

    @Test
    fun verifyConfirmPasswordValidationWithInValidInput() {

        val invalidInput = "123"

        with(confirmPassword) {
            performTextInput(invalidInput)
            assert(hasText(invalidInput))
            assertTrue(isConfirmPasswordInvalid.value)
            assertTrue(emailErrMsg.value == INVALID_PASSWORD)
            performTextClearance()
        }
    }


    @Test
    fun verifyPasswordAndConfirmPasswordAreSame() {

        val passwordInput = "123456"
        val confirmPasswordInput = "123456"

        with(confirmPassword) {
            performTextInput(confirmPasswordInput)
            assert(hasText(confirmPasswordInput))
        }

        with(password) {
            performTextInput(passwordInput)
            assert(hasText(passwordInput))
        }

        assertEquals(hasTextExactly(passwordInput), hasTextExactly(confirmPasswordInput))
    }

    @Test
    fun registerUser() {
        username.performTextInput("AbhishekPathak")
        email.performTextInput(VALID_EMAIL)
        mobile.performTextInput("+974 7176622")
        password.performTextInput("12345678")
        confirmPassword.performTextInput("12345678")
        registerButton.performClick()
    }

    private companion object {
        const val INVALID_PASSWORD = "Password is not matched"
        const val VALID_EMAIL = "myofficework@gmail.com"
    }
}