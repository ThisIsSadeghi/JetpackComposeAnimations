package com.sadeghi.animation.visibility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sadeghi.animation.R
import com.sadeghi.animation.components.HorizontalCheckBox
import com.sadeghi.animation.utils.CheckBoxItem

/**
 * Created by Ali Sadeghi
 * on 20,Oct,2024
 */

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VisibilityScreen() {
    var visible by remember { mutableStateOf(true) }

    var enterAnimations by remember {
        mutableStateOf(
            listOf(
                CheckBoxItem(id = 1, title = "Fade In", isChecked = true) to fadeIn(),
                CheckBoxItem(id = 2, title = "Slide In") to slideIn(initialOffset = { IntOffset.Zero }),
                CheckBoxItem(id = 3, title = "Slide In Horizontally") to slideInHorizontally(),
                CheckBoxItem(id = 4, title = "Slide In Vertically") to slideInVertically(),
                CheckBoxItem(id = 5, title = "Scale In") to scaleIn(),
                CheckBoxItem(id = 6, title = "Expand In") to expandIn(),
                CheckBoxItem(id = 7, title = "Expand Horizontally") to expandHorizontally(),
                CheckBoxItem(id = 8, title = "Expand Vertically") to expandVertically(),
            ),
        )
    }

    var exitAnimations by remember {
        mutableStateOf(
            listOf(
                CheckBoxItem(id = 1, title = "Fade Out", isChecked = true) to fadeOut(),
                CheckBoxItem(id = 2, title = "Slide Out") to slideOut(targetOffset = { IntOffset.Zero }),
                CheckBoxItem(id = 3, title = "Slide Out Horizontally") to slideOutHorizontally(),
                CheckBoxItem(id = 4, title = "Slide Out Vertically") to slideOutVertically(),
                CheckBoxItem(id = 5, title = "Scale Out") to scaleOut(),
                CheckBoxItem(id = 6, title = "Shrink Out") to shrinkOut(),
                CheckBoxItem(id = 7, title = "Shrink Horizontally") to shrinkHorizontally(),
                CheckBoxItem(id = 8, title = "Shrink Vertically") to shrinkVertically(),
            ),
        )
    }

    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }

        Spacer(Modifier.height(8.dp))

        Text(text = "Enter Animations", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        FlowRow(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            enterAnimations.forEach { pair ->
                pair.first.let { item ->
                    HorizontalCheckBox(
                        title = item.title,
                        checked = item.isChecked,
                        onCheckedChange = {
                            enterAnimations =
                                enterAnimations.map {
                                    it.first.copy(
                                        isChecked =
                                            if (it.first.id == item.id)
                                                it.first.isChecked.not()
                                            else
                                                it.first.isChecked,
                                    ) to it.second
                                }
                        },
                    )

                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(text = "Exit Animations", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        FlowRow(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            exitAnimations.forEach { pair ->
                pair.first.let { item ->
                    HorizontalCheckBox(
                        title = item.title,
                        checked = item.isChecked,
                        onCheckedChange = {
                            exitAnimations =
                                exitAnimations.map {
                                    it.first.copy(
                                        isChecked =
                                            if (it.first.id == item.id)
                                                it.first.isChecked.not()
                                            else
                                                it.first.isChecked,
                                    ) to it.second
                                }
                        },
                    )

                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = visible,
            enter = getTransitionsFromList(enterAnimations),
            exit = getTransitionsFromList(exitAnimations),
        ) {
            Image(painter = painterResource(R.drawable.cat), contentDescription = "Cat Image")
        }
    }
}

private fun getTransitionsFromList(list: List<Pair<CheckBoxItem, EnterTransition>>): EnterTransition {
    if (list.count { it.first.isChecked } == 0)
        return fadeIn() + expandVertically() // DefaultEnterTransitions
    return list.filter { it.first.isChecked }.map { it.second }.reduce { acc, enterTransition ->
        acc + enterTransition
    }
}

private fun getTransitionsFromList(list: List<Pair<CheckBoxItem, ExitTransition>>): ExitTransition {
    if (list.count { it.first.isChecked } == 0)
        return fadeOut() + shrinkVertically() // DefaultExitTransitions
    return list.filter { it.first.isChecked }.map { it.second }.reduce { acc, enterTransition ->
        acc + enterTransition
    }
}