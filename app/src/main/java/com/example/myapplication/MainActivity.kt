package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(

                            title = {
                                Text(
                                    "Centered Top App Bar",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.navigate("home")
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                        )
                    },
                    bottomBar = {
                        Row {
                            Button(onClick = { navController.navigate("home") }) {
                                Text(text = "home")
                            }
                            Button(onClick = { navController.navigate("about/0") }) {
                                Text(text = "About")
                            }
                        }
                    }
                ) { ip ->
                    Navigation(
                        navController,
                        Modifier
                            .padding(ip)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", builder = {
        composable("home") { Screen1(navController) }
        composable("about/{counter}") { backStackEntry ->
            Screen2(
                backStackEntry.arguments?.getString("counter"),
            )
        }
    })
}


@Composable
fun Screen1(navController: NavController) {
    var count by remember { mutableStateOf(0) }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello World!!! $count")
        Button(onClick = { count++ }) {
            Text(text = "Click me...")
        }
        Button(onClick = {
            navController.navigate("about/$count")
        }) {
            Text(text = "Go AWAY!")
        }
    }
}


@Composable
fun Screen2(
    counter: String?
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $counter times!"
        )
    }
}
