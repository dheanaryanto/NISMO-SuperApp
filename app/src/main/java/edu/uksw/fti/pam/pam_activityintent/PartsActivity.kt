package edu.uksw.fti.pam.pam_activityintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.pam_activityintent.models.ApparelViewModel
import edu.uksw.fti.pam.pam_activityintent.models.PartViewModel
import edu.uksw.fti.pam.pam_activityintent.ui.screens.*
import edu.uksw.fti.pam.pam_activityintent.ui.theme.PAM_ActivityIntentTheme
import edu.uksw.fti.pam.pam_activityintent.ui.theme.coklatmuda

class PartsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parts)
        setContent {
            PAM_ActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                PartsScreen()
            }
        }
    }
}