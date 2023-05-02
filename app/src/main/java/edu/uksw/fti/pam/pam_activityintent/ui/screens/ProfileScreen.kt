package edu.uksw.fti.pam.pam_activityintent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pam_activityintent.ui.theme.anekBold
import edu.uksw.fti.pam.pam_activityintent.ui.theme.coklatmuda

@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(coklatmuda)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile Screen",
            fontFamily = anekBold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp

        )
    }
}