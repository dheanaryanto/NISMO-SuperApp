package edu.uksw.fti.pam.pam_activityintent.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.pam_activityintent.ui.theme.anekBold
import edu.uksw.fti.pam.pam_activityintent.ui.theme.cokz
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.ui.theme.PAM_ActivityIntentTheme
import edu.uksw.fti.pam.pam_activityintent.ui.theme.merahNismo
import kotlinx.coroutines.launch

@Composable
fun SignUpForm(
    onClickAction: (String, String, String, String, String, String) -> Unit
) {
    val lContext = LocalContext.current

    var firstnameInput by remember { mutableStateOf("") }
    var lastnameInput by remember { mutableStateOf("") }
    var usernameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var paskon by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, end = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(painter = painterResource(id = R.drawable.nism_o), contentDescription = "logonismo",
            modifier = Modifier
                .height(40.dp)

        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 230.dp)
            .width(600.dp)

    ) {
        Text(
            text = stringResource(id = R.string.signup),
            fontFamily = anekBold,
            fontSize = 40.sp
        )
        Row (){
            TextField(
                value = firstnameInput.toString(),
                onValueChange = {firstnameInput = it},
                label = { Text(text =  stringResource(R.string.fisnem)) },
                modifier = Modifier
                    .padding(end = 5.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(cokz)

            )
            TextField(
                value = lastnameInput.toString(),
                onValueChange = {lastnameInput = it},
                label = { Text(text =  stringResource(R.string.lasnem)) },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(cokz)

            )
        }
        TextField(
            value = usernameInput.toString(),
            onValueChange = {usernameInput = it},
            label = { Text(text =  stringResource(id = R.string.label_username)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(cokz),
        )
        TextField(
            value = emailInput.toString(),
            onValueChange = {emailInput = it},
            label = { Text(text =  stringResource(id = R.string.label_email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(cokz),
        )
        TextField(
            value = passwordInput.toString(),
            onValueChange = {passwordInput = it},
            label = { Text(text =  stringResource(id = R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(cokz),
        )
        TextField(
            value = paskon.toString(),
            onValueChange = {paskon = it},
            label = { Text(text =  stringResource(R.string.paskon)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(cokz),
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Button(
                onClick = {
                    if (passwordInput == paskon){
                        onClickAction(firstnameInput, lastnameInput, emailInput, usernameInput, passwordInput, paskon)
                    } else {
                        Toast.makeText(lContext, R.string.pwd_incorrect, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(top = 20.dp)
            ) {
                Text(text = stringResource(R.string.signup), color = merahNismo
                )

            }
        }

    }
}
