package abhishek.pathak.composetesting

import abhishek.pathak.composetesting.ui.theme.ComposeTestingTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeUITest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setUp() {
        rule.setContent {
            ComposeTestingTheme {
                WelcomeScreen()
            }
        }
    }

    @Test
    fun checkWeatherContinueButtonExistsOrNot() {
        rule.onNodeWithText("Continue").assertExists()
        rule.onNodeWithText("Continue").performClick()
    }
}