package edu.uksw.fti.pam.pam_activityintent

import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pam_activityintent.ui.screens.*
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setContent {
            PAM_ActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen()
                BottomNavigationMainScreenView()
//                Column(
//                    modifier = Modifier
//                        .background(coklatmuda)
//                        .fillMaxSize()
//                        .verticalScroll(rememberScrollState())
//
//                ){
//                    HeaderProfileComponent()
//                    jduduldanlogo()
//                    berita()
//                    jduduldanlogo2()
//                    produk()
//                    aksesoris()
//                    Spacer(modifier = Modifier.weight(1f))
////                    BottomComponent()
//                    BottomNavigationMainScreenView()
//                }

            }
        }
    }
}




