package com.sadeghi.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Ali Sadeghi
 * on 24,Oct,2024
 */

@Composable
fun ContentScreen() {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier =
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = { count++ }) {
            Text("+++")
        }
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = { count-- }) {
            Text("---")
        }

        Spacer(Modifier.height(8.dp))

        Row {
            Text(
                text = "Count: ",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    slideInVertically { height -> height }.togetherWith(slideOutVertically { height -> -height })
                },
            ) { targetCount ->
                Text(
                    text = "$targetCount",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}