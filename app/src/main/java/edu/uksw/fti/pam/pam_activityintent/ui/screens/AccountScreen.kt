package edu.uksw.fti.pam.pam_activityintent.ui.screens


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import edu.uksw.fti.pam.pam_activityintent.HomeActivity
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.WheelActivity
import edu.uksw.fti.pam.pam_activityintent.models.Apparel
import edu.uksw.fti.pam.pam_activityintent.models.ApparelViewModel
import edu.uksw.fti.pam.pam_activityintent.models.UserViewModel
import edu.uksw.fti.pam.pam_activityintent.models.Users

@Composable
fun AccountScreen() {

    Column(
        modifier = Modifier
            .background(coklatmuda)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)

    ){
        juduldanlogo()
        namadanakun()
        dompet()
        order()
        settings()
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun juduldanlogo(){
    Column(
        modifier = Modifier.background(coklatTua)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, top = 18.dp, bottom = 11.dp)
        ) {
            val lContext = LocalContext.current
            Image(painter = painterResource(id = R.drawable.backred), contentDescription = "logonismo",
                modifier = Modifier
                    .width(110.dp)
                    .height(30.dp)
                    .padding(top = 1.dp)
                    .offset(x = (-25).dp)
                    .clickable {
                        lContext.startActivity(
                            Intent(lContext, HomeActivity::class.java)
                        )  }

            )
//        Button(
//            onClick = {
//            lContext.startActivity(
//                Intent(lContext, HomeActivity::class.java)
//            ) },
//            modifier = Modifier
//                .clip(RoundedCornerShape(10.dp))
//                .padding(top = 20.dp)
//                .background(merahNismo)
//        ){
//            Text(text = stringResource(R.string.back))
//        }
            Text(text = stringResource(id = R.string.account), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren, modifier = Modifier.padding(top = 3.dp))
            Image(painter = painterResource(id = R.drawable.nismo), contentDescription = "logonismo",
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
                    .padding(top = 1.dp)

            )
        }
        Divider(
            color = coklatTua,
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 0.dp, start = 7.dp, end = 10.dp, bottom = 1.dp))
    }

}

@Composable
fun namadanakun(){
    val viewModel: UserViewModel = viewModel()

    // Ambil email dari dokumen Firestore
    val currentUser = Firebase.auth.currentUser
    val userEmail = currentUser?.email ?: ""

    // Fetch nama depan user dari Firestore menggunakan LaunchedEffect
    LaunchedEffect(true) {
        viewModel.fetchFirstNameByEmail(userEmail)
    }

    // Observasi nama depan dari UserViewModel
    val firstName by viewModel.firstName.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, bottom = 5.dp, top = 15.dp)
    ){
        Row(

        ) {
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val context = LocalContext.current
            val bitmap = remember { mutableStateOf<Bitmap?>(null) }

            val storage = Firebase.storage
            val storageRef = storage.reference.child("profil").child("foto")

            val launcher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                    imageUri = uri
                }

            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                ,
            ) {
                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.akun), contentDescription = null,
                        modifier = Modifier
                            .clickable { launcher.launch("image/*") }
                            .width(100.dp)
                            .height(100.dp))
                }
                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    bitmap.value?.let { btm ->
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,

                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .clip(CircleShape)
                                .clickable(enabled = true, onClick = { })
                        )
                        val inputStream = context.contentResolver.openInputStream(imageUri!!)
                        val bytes = inputStream!!.readBytes()

                        // Upload the byte array to Firebase Storage.
                        val uploadTask = storageRef.putBytes(bytes)
                        uploadTask.addOnSuccessListener { taskSnapshot ->
                            val downloadUrl = taskSnapshot.metadata?.reference?.downloadUrl?.toString()
                            // Do something with the download URL, such as saving to Firestore.
                        }.addOnFailureListener {
                            // Handle errors.
                        }
                    }
                }


            }
            Column(
                modifier = Modifier
                    .padding(top = 3.dp, start = 10.dp)
            ) {
                // Tampilkan nama depan pada Composable Text
                firstName?.let {
                    Text(
                        text = "$it",
                        fontFamily = anekBold,
                        color = Color.Black,
                        fontSize = 23.sp,
                        modifier = Modifier
                    )
                    Image(
                        painter = painterResource(id = R.drawable.member),
                        contentDescription = "member",
                        modifier = Modifier
                            .height(17.dp)

                    )
                    Text(
                        text = "0821356273276",
                        fontFamily = anekLight,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
            }
        }
        Divider(
            color = coklatTua,
            thickness = 3.dp,
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        )
    }
}


@Composable
fun dompet(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(start = 15.dp, end = 15.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = 0.dp,
        backgroundColor = hitamkren
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp, top = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Text(text = stringResource(id = R.string.dompet), fontFamily = anekMedium, color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.wallet),
                    contentDescription = "wallet" ,
                    modifier = Modifier
                        .height(20.dp)
                        .padding(start = 5.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "HGTG3234I",
                    fontFamily = anekBold,
                    color = Color.White,
                    fontSize = 59.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.pengluwaran),
                    fontFamily = anekLight,
                    color = grey,
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Classic",
                    fontFamily = anekBold,
                    color = Color.White,
                    fontSize = 16.sp,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus" ,
                    modifier = Modifier
                        .height(15.dp)
                )
                Text(
                    text = "Upgrade Member",
                    fontFamily = anekLight,
                    color = Color.White,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)

                )
            }
        }

    }
}

@Composable
fun order(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = 0.dp,
        backgroundColor = hitamkren
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, bottom = 10.dp, top = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Text(text = stringResource(id = R.string.myorder), fontFamily = anekMedium, color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.myorder),
                    contentDescription = "myorder" ,
                    modifier = Modifier
                        .height(20.dp)
                        .padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.qr),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }

        }

    }
}

@Composable
fun settings(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp,start = 5.dp, end = 5.dp, bottom = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
            onClick = {
                context.dial(phone = "081586022232")
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.tilpun),
                contentDescription = "call" ,
                modifier = Modifier
                    .height(20.dp)
                    .padding(start = 5.dp, end = 5.dp),
            )
            Text(text = "Call Center", fontFamily = anekMedium, color = white,fontSize = 15.sp)
        }
//        Divider(
//            color = coklatTua,
//            thickness = 1.dp,
//            modifier = Modifier
//                .padding(top = 0.dp, start = 10.dp, end = 10.dp))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 5.dp)
//                .clickable { context.dial(phone = "081586022232") },
//        ){
//            Image(
//                painter = painterResource(id = R.drawable.call),
//                contentDescription = "call" ,
//                modifier = Modifier
//                    .height(20.dp)
//                    .padding(start = 5.dp, end = 5.dp)
//            )
//            Text(
//                text = stringResource(id = R.string.call),
//                fontFamily = anekMedium,
//                color = hitamkren,
//                modifier = Modifier
//                    .padding(start = 5.dp)
//            )
//        }

    }
}

//@Composable
//fun bawahan( ){
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(coklatTua)
//            .height(60.dp)
//    ){
//
//        Image(
//            painter = painterResource(id = R.drawable.cart),
//            contentDescription = "cart",
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .size(48.dp)
//                .padding(top = 5.dp)
//        )
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Image(
//                painter = painterResource(id = R.drawable.home),
//                contentDescription = "home" ,
//                modifier = Modifier
//                    .height(55.dp)
//                    .padding(start = 0.dp, end = 12.dp, top = 5.dp)
//            )
//            Image(
//                painter = painterResource(id = R.drawable.parts),
//                contentDescription = "parts" ,
//                modifier = Modifier
//                    .height(55.dp)
//                    .padding(start = 12.dp, end = 39.dp, top = 5.dp)
//            )
//            Image(
//                painter = painterResource(id = R.drawable.apparelbna),
//                contentDescription = "apparel" ,
//                modifier = Modifier
//                    .height(55.dp)
//                    .padding(start = 39.dp, end = 12.dp, top = 5.dp)
//            )
//            Image(
//                painter = painterResource(id = R.drawable.news),
//                contentDescription = "news" ,
//                modifier = Modifier
//                    .height(55.dp)
//                    .padding(start = 12.dp, end = 0.dp, top = 5.dp)
//            )
//        }
//    }
//}



//@Preview(showBackground = true)
//@Composable
//fun DefaultssPreview() {
//    PAM_ActivityIntentTheme {
//        namadanakun("")
//    }
//}
