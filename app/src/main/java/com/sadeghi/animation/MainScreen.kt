package com.sadeghi.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Ali Sadeghi
 * on 20,Oct,2024
 */

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onVisibleInvisibleClick: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = onVisibleInvisibleClick) {
            Text(text = "Visible/Invisible")
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}