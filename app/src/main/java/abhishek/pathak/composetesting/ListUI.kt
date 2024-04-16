package abhishek.pathak.composetesting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListUI(list: SnapshotStateList<String>) {

    LazyColumn {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.Cyan, RoundedCornerShape(10.dp))
            ) {
                Text(text = item, modifier = Modifier.padding(10.dp).align(Alignment.Center))
            }
        }
    }
}