package abhishek.pathak.composetesting

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListUITest {
    @get:Rule
    val composeRule = createComposeRule()
    val stateList = mutableStateListOf<String>()

    @Before
    fun setUp(){
        stateList.add("One")
        stateList.add("Two")
        stateList.add("Three")
        stateList.add("Four")
        stateList.add("Five")

        composeRule.setContent {
            ListUI(list = stateList)
        }
    }

    @Test
    fun testList() {
        // check all items displayed
        stateList.forEach { content ->
            composeRule
                .onNodeWithText(content).assertExists()
        }
    }

    @Test
    fun testScrollOfList(){
        // check scroll
        composeRule
            .onNodeWithText("Five").performScrollTo()
        composeRule
            .onNodeWithText("Five").assertIsDisplayed()

        // remove item and assert not displayed
        stateList.remove("Four")
        composeRule
            .onNodeWithText("Four").assertDoesNotExist()


        // add item and assert  displayed
        stateList.add("Six")
        composeRule
            .onNodeWithText("Six").assertIsDisplayed()
    }
}