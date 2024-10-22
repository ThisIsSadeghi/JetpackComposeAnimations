package com.sadeghi.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sadeghi.animation.ui.theme.JetpackComposeAnimationsTheme
import com.sadeghi.animation.visibility.VisibilityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAnimationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Destination.Main) {
                        composable<Destination.Main> {
                            MainScreen(modifier = Modifier.padding(innerPadding)) {
                                navController.navigate(Destination.Visibility)
                            }
                        }

                        composable<Destination.Visibility> {
                            VisibilityScreen()
                        }
                    }
                }
            }
        }
    }
}