package com.example.hola.screens

import com.example.hola.R
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.hola.dataclass.NavItem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun Main() {
    val navController = rememberAnimatedNavController()
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val hideBottomNavRoutes = listOf("splash", "login", "signup", "forget_password")

    val navItems = listOf(
        NavItem("home", R.drawable.home_icon),
        NavItem("explore", R.drawable.explore_icon),
        NavItem("createpost", R.drawable.createpost_icon),
        NavItem("location", R.drawable.location_icon),
        NavItem("profile", R.drawable.profile_icon)
    )

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            selectedIndex = navItems.indexOfFirst { it.label == backStackEntry.destination.route }
        }
    }

    Scaffold(
        topBar = {
            if (currentRoute !in hideBottomNavRoutes) TopBar()
        },
        bottomBar = {
            if (currentRoute !in hideBottomNavRoutes) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .shadow(24.dp, RoundedCornerShape(14.dp))
                        .background(Color(0xFFF7F7F7), RoundedCornerShape(14.dp))
                ) {
                    NavigationBar(
                        containerColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                    ) {
                        navItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedIndex == index,
                                onClick = {
                                    selectedIndex = index
                                    navController.navigate(item.label) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(
                                                if (selectedIndex == index) Color.Blue else Color.Transparent
                                            )
                                            .padding(8.dp)
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Icon(
                                                painter = painterResource(id = item.icon),
                                                contentDescription = item.label,
                                                tint = if (selectedIndex == index) Color.White else Color.Black,
                                                modifier = Modifier.size(22.dp)
                                            )
                                            Text(
                                                text = item.label.capitalize(),
                                                color = if (selectedIndex == index) Color.White else Color.Black,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Medium,
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color.White,
                                    unselectedIconColor = Color.Black,
                                    unselectedTextColor = Color.Black,
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) +
                        fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300)) +
                        fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300)) +
                        fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) +
                        fadeOut(animationSpec = tween(300))
            }
        ) {
            composable("home") { HomeScreen() }
            composable("explore") { ExploreScreen() }
            composable("createpost") { CreatePost() }
            composable("location") { LocationPage() }
            composable("profile") { ProfilePage() }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF000001), shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.hola_icon),
            contentDescription = "Logo",
            modifier = Modifier.height(55.dp).width(85.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.chat_icon),
            contentDescription = "Chat",
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun displayInFullScreen(image: String, dismiss: () -> Unit) {
    var offsetY by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragEnd = {
                        if (offsetY < -245 || offsetY > 245) dismiss() else offsetY = 0f
                    },
                    onVerticalDrag = { _, dragAmount ->
                        offsetY += dragAmount
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Full Screen Image",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = offsetY },
            contentScale = ContentScale.Fit
        )
    }
}
