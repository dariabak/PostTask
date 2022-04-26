package test.savedPosts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0.8f, 0.8f, 0.8f, 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .defaultMinSize(minWidth = 150.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(5.dp, 0.dp))
            Text(
                text = text
            )
        }
    }
}


@Composable
@Preview
fun LoadingPreview() {
    Loading("Loading")
}