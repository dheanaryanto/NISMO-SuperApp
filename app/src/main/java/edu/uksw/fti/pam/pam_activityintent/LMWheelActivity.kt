package edu.uksw.fti.pam.pam_activityintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.pam_activityintent.ui.screens.PartsScreen
import edu.uksw.fti.pam.pam_activityintent.ui.screens.ProfileScreen


import edu.uksw.fti.pam.pam_activityintent.ui.theme.PAM_ActivityIntentTheme

class LMWheelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_ActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PartsScreen()
                }
            }
        }
    }
}