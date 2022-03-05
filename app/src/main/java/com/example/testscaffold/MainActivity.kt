package com.example.testscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyMainScreen()
        }

    }
}

@Preview
@Composable
fun MyMainScreen() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopBar(scaffoldState, coroutineScope) },
        floatingActionButton = { MyFloatingActionButton() },
        bottomBar = { MyBottomNavigationBar() },
        drawerContent = { MyDrawerContent() }
    ) { paddingValues ->
        Row(Modifier.padding(paddingValues)) {
            Text(text = "This is the content area")
        }
    }
}

@Preview
@Composable
fun MyBottomNavigationBar() {

    BottomNavigation() {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Outlined.Home, contentDescription = "Button")
        }

        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Outlined.Phone, contentDescription = "Button")
        }

        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Button")
        }
    }

}

@Preview
@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(onClick = { }) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite FAB")
    }
}

@Composable
fun MyTopBar(scaffoldState: ScaffoldState, coroutineScope: CoroutineScope) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Outlined.Menu, contentDescription = "Button")
            }
        },

        title = { Text(text = "This is top bar") }
    )

}

@Preview
@Composable
fun MyDrawerContent() {
    Text(text = "This is drawer menu")
}