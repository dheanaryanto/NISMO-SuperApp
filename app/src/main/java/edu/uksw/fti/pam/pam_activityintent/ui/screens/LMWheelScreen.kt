package edu.uksw.fti.pam.pam_activityintent.ui.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.ContentView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.WheelActivity

@Composable
fun LMWheelScreen(){
    Column(
        modifier = Modifier
            .background(coklatmuda)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)

    ){
        hidir()
        detail()
    }
}

@Composable
fun detail(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(815.dp)
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = 0.dp,
        backgroundColor = hitamkren
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp, top = 10.dp)
        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//            ){
//                Text(text = stringResource(id = R.string.dompet), fontFamily = anekMedium, color = Color.White)
//                Image(
//                    painter = painterResource(id = R.drawable.wallet),
//                    contentDescription = "wallet" ,
//                    modifier = Modifier
//                        .height(20.dp)
//                        .padding(start = 5.dp)
//                )
//            }
//            Text(
//                text = stringResource(id = R.string.saldo),
//                fontFamily = anekLight,
//                color = grey,
//                fontSize = 13.sp
//            )
            Text(
                text = "How To Buy NISMO Products",
                fontFamily = anekBold,
                color = Color.White,
                fontSize = 41.sp
            )
            Text(text = "Here are some points from the official Nissan Motorsport regulations :", fontFamily = anekMedium, color = grey)
            val listya = listOf(
                "To keep our exclusivity, all of transaction need to be offline by an authorized dealer",
                "Every items that above IDR 2.000.000 will get a tax fee according to their country of domicile",
                "If there is a problem with your local custom protection, NISSAN local dealership will takeover the problem",
                "Products insurance are included in the price"
            )
            val context = LocalContext.current
            LazyColumn {
                items(listya) {
                    Row(Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                        Canvas(modifier = Modifier
                            .padding(end = 8.dp)
                            .size(6.dp)){
                            drawCircle(Color.White)
                        }
                        Text(text = it,fontSize = 14.sp, color = grey)
                    }
                }
            }
            Text(text = "So, here's step by step if you want to buy our products", fontFamily = anekMedium, color = Color.White, modifier = Modifier.padding(top = 10.dp))
            val lesty = listOf(
                "1. First you need to need to make appointment with your local Nissan Dealership",
                "2. If you have trouble finding the closest dealership you can click button bellow to email to your closest dealership",
                "3. Copy product link or show the employee the product",
                "4. Show your NISMO account code / your unique qr code to validate your order",
                "5. All transaction will be done in the authorized dealership"
            )
            LazyColumn {
                items(lesty) {
                    Row(Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                        Text(text = it,fontSize = 14.sp, color = white)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .width(100.dp)
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                    onClick = { context.sendMail(to = "NISSANMotorSalatiga@gmail.com", subject = "Appointment For NISMO Buyers")
                    },
                ) {
                    Text(text = "Email", fontFamily = anekMedium, color = white,fontSize = 15.sp)
                }
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .width(100.dp)
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                    onClick = { context.dial(phone = "081586022232")
                    },
                ) {
                    Text(text = "Call", fontFamily = anekMedium, color = white,fontSize = 15.sp)
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/d38G1eFU4Jt7cskM8")) }
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .width(300.dp)
                        .padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                    onClick = { context.startActivity(intent)
                    },
                ) {
                    Text(text = "Nearest Dealership", fontFamily = anekMedium, color = white,fontSize = 15.sp)
                }
            }

//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//            ){
//                Text(
//                    text = stringResource(id = R.string.pengluwaran),
//                    fontFamily = anekLight,
//                    color = grey,
//                    fontSize = 15.sp,
//                    modifier = Modifier
//                )
//                Text(
//                    text = "Rp100.000.000",
//                    fontFamily = anekBold,
//                    color = Color.White,
//                    fontSize = 16.sp,
//                )
//            }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.plus),
//                    contentDescription = "plus" ,
//                    modifier = Modifier
//                        .height(15.dp)
//                )
//                Text(
//                    text = "Top Up",
//                    fontFamily = anekLight,
//                    color = Color.White,
//                    fontSize = 15.sp,
//                    modifier = Modifier
//                        .padding(start = 10.dp)
//
//                )
//            }
        }

    }
}
fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}

fun Context.dial(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (t: Throwable) {
        // TODO: Handle potential exceptions
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultsPreviewz() {
    PAM_ActivityIntentTheme {
        LMWheelScreen()
    }
}