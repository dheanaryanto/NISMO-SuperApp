package edu.uksw.fti.pam.pam_activityintent.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import edu.uksw.fti.pam.pam_activityintent.R

sealed class BottomNavItems (
    var title: Int,
    val icon: Int,
    val screen_route: String
    ) {
    object Home: BottomNavItems(R.string.home, R.drawable.rumaz, "Home")
    object Parts: BottomNavItems(R.string.parts, R.drawable.ban, "Parts")
    object Shop: BottomNavItems(R.string.cart, R.drawable.cartu, "Shop")
    object Apparel: BottomNavItems(R.string.aparl, R.drawable.kaos, "Apparel")
    object Profile: BottomNavItems(R.string.account, R.drawable.akun, "Profile")

}