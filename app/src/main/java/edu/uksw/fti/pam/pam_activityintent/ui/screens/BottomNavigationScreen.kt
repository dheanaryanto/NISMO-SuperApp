package edu.uksw.fti.pam.pam_activityintent.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Icon
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.pam_activityintent.AccountActivity
import edu.uksw.fti.pam.pam_activityintent.PartsActivity
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.models.ApparelViewModel
import edu.uksw.fti.pam.pam_activityintent.models.PartViewModel
import edu.uksw.fti.pam.pam_activityintent.ui.BottomNavItems
import edu.uksw.fti.pam.pam_activityintent.ui.theme.coklatTua
import edu.uksw.fti.pam.pam_activityintent.ui.theme.merahNismo

@Composable
fun NavigationGraph(
    navController :NavHostController
){
    val context = LocalContext.current

    // Launch MainActivity
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        // handle result here
    }

    NavHost(
        navController = navController,
        startDestination = BottomNavItems.Home.screen_route){
        composable(BottomNavItems.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItems.Parts.screen_route) {
            PartsScreen()
        }
        composable(BottomNavItems.Shop.screen_route) {
            ShopScreen()
        }
        composable(BottomNavItems.Apparel.screen_route) {
            ApparelScreen()
        }
        composable(BottomNavItems.Profile.screen_route) {
            launcher.launch(Intent(context, AccountActivity::class.java))
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController
){
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Parts,
//        BottomNavItems.Shop,
        BottomNavItems.Apparel,
//        BottomNavItems.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = coklatTua,
        contentColor = Color.Black,
//        modifier = Modifier.height(60.dp)
//        backgroundColor = colorResource(id = R.color.teal_200),
//        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painter = painterResource(id = item.icon),
                    modifier = Modifier.size(25.dp),
                    tint = Color.Unspecified,
                    contentDescription = "${item.title} icon")

                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        fontSize = 9.sp)
                },
                selectedContentColor = merahNismo,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

