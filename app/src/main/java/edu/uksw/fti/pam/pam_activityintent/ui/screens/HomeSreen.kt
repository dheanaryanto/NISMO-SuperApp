package edu.uksw.fti.pam.pam_activityintent.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.pam_activityintent.HomeActivity
import edu.uksw.fti.pam.pam_activityintent.LMWheelActivity
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*
import edu.uksw.fti.pam.pam_activityintent.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import edu.uksw.fti.pam.pam_activityintent.WheelActivity
import edu.uksw.fti.pam.pam_activityintent.models.BeritaViewModel


private val vmb = BeritaViewModel ()

@Composable
fun HomeScreen () {
    Box(modifier = Modifier
        .background(coklatmuda)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(bottom = 60.dp)

    ) {
        Column {
            hidir()
//            ExpandableSearchView(
//                searchDisplay = "",
//                onSearchDisplayChanged = {},
//                onSearchDisplayClosed = {}
//            )
            jduduldanlogo()
            berita()
            jduduldanlogo2()
            Produk()
            jduduldanlogo3()
            Produk2()
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

//@Composable
//fun HeaderProfileComponent(){
//    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(coklatTua)
//            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
//
//    ){
//        Row(verticalAlignment = Alignment.CenterVertically){
//            Image(painter = painterResource(id = R.drawable.nism_o), contentDescription = "Logo",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(40.dp)
//                    .clip(CircleShape)
//            )
//        }
//
//        Row() {
//            OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                leadingIcon = { Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search Icon",
//                    tint = coklatTua,
//                ) },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = coklatTua,
//                    unfocusedBorderColor = coklatTua
//                ),
//                modifier = Modifier
//                    .padding(top = 1.dp)
//                    .height(35.dp)
//                    .width(220.dp)
//                    .background(coklatmuda, RoundedCornerShape(40.dp))
//            )
//        }
//
//
//
//    }
//}

@Composable
fun jduduldanlogo(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Text(text = stringResource(id = R.string.news), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren)
        Image(painter = painterResource(id = R.drawable.nismo), contentDescription = "logonismo",
            modifier = Modifier
                .width(110.dp)
                .height(30.dp)
                .padding(top = 1.dp)

        )
    }
}

@Composable
fun berita(){
    LaunchedEffect(
        Unit,
        block = {
            vmb.getBeritaList()
        }
    )
    LazyRow(
        modifier = Modifier
            .height(180.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(vmb.beritaList.size) { index ->
            beritaItem(imagePainter = rememberAsyncImagePainter(vmb.beritaList[index].gambar),
                title = vmb.beritaList[index].title,
                subtitle = vmb.beritaList[index].subtitle,
                backgroudColor = coklatmuda
            )
        }
//        item {
//            beritaItem(imagePainter = painterResource(id = R.drawable.berita1),
//                title = stringResource(id = R.string.jdulbrit1),
//                subtitle = stringResource(id = R.string.brit2),
//                backgroudColor = coklatmuda
//            )
//        }
//        item {
//            beritaItem(imagePainter = painterResource(id = R.drawable.berita2),
//                title = stringResource(id = R.string.jdulbrit2),
//                subtitle = stringResource(id = R.string.brit2),
//                backgroudColor = coklatmuda
//            )
//        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun beritaItem(
    title: String = "",
    subtitle: String = "",
    header: String = "",
    backgroudColor: Color = coklatmuda,
    imagePainter: Painter
){
    val lContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false)  }
    Card(
        modifier = Modifier
            .width(300.dp)
            .clickable { openDialog.value = true },
        backgroundColor = backgroudColor,
        elevation = 0.dp

    ) {
        Column {
            Image(
                painter = imagePainter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .width(300.dp)
                    .clip(RoundedCornerShape(5.dp)),

                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
            )
            if (openDialog.value) {
                Dialog(
                    onDismissRequest = { openDialog.value = false },
                    properties = DialogProperties(usePlatformDefaultWidth = false),
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(coklatmuda)
                                .padding(start = 15.dp, end = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = " ")
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .clip(RoundedCornerShape(10.dp)),

                            ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(painter = painterResource(id = R.drawable.backred), contentDescription = "Logo",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .clickable { openDialog.value = false }

                                )
                                Image(painter = painterResource(id = R.drawable.nismo), contentDescription = "logonismo",
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(30.dp)
                                        .padding(top = 1.dp)

                                )
                            }

                            Text(
                                text = title, modifier = Modifier.padding(top = 5.dp), fontSize = 24.sp, fontFamily = anekBold, color = Color.Black
                            )
                            Text(
                                text = "Senin, 1 Mei 2023 | 12:33 WIB", modifier = Modifier.padding(top = 0.dp), fontSize = 13.sp, fontFamily = anekLight, color = Color.Black
                            )
                            Text(
                                text = "Oleh : Xian Yuing Van Persie", modifier = Modifier.padding(top = 2.dp, bottom = 10.dp), fontSize = 13.sp, fontFamily = anekLight, color = Color.Black
                            )
                            Image(
                                painter = imagePainter,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Divider(
                                color = abu,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp))
                            val listya = listOf(
                                "Untuk pengemudi yang mencari tingkat kemampuan lebih tinggi, GT-R NISMO adalah versi GT-R yang lebih berorientasi trek. Selain revisi aerodinamis, NISMO GT-R 2024 mendapat manfaat dari peningkatan diferensial selip terbatas depan. pengalaman berkendara dan mencontohkan pendekatan tim NISMO untuk peningkatan iteratif yang terus membangun kemampuan GT-R.",
                            "Puncak rekayasa performa tinggi Nissan, secara mendebarkan menggabungkan mesin tenaga kuda tinggi dengan penanganan yang mengesankan dan teknologi yang berguna untuk berkendara sehari-hari. Untuk model tahun 2024, GT-R bahkan lebih menarik dengan peningkatan mendalam pada performa aerodinamisnya, the pengembalian trim khusus dan revisi paket yang tersedia.",
                            "Nissan GT-R 2024 ditawarkan dalam tiga kelas, dimulai dengan GT-R Premium dan T-spec® dengan 565 tenaga kuda, memberikan pilihan menggiurkan bagi mereka yang mencari mobil sport super. GT-R NISMO 600-tenaga kuda1 menawarkan tingkat kinerja yang lebih luar biasa, dengan teknik yang diambil langsung dari aplikasi olahraga motor."
                            )
                            LazyColumn {
                                items(listya) {
                                    Row(Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                                        Text(text ="\t \t" + it,fontSize = 14.sp, color = Color.Black,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(1.dp),
                                            textAlign = TextAlign.Justify
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 2.dp)
            ) {
                Text(text = title, fontSize = 14.sp, color = Color.Black, fontFamily = anekMedium)
                Text(text = subtitle, fontSize = 14.sp, color = Color.Black, fontFamily = anekLight)
            }

        }
    }
}

@Composable
fun jduduldanlogo2(){
    val lContext = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Text(text = stringResource(id = R.string.parts), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren)
    }
}

@Composable
fun Produk(){
    val list = createDataListAksesoris()

    LazyRow(
        modifier = Modifier
            .height(250.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(list.size) {index ->
                GridItemAksesoris(testDataAcc = list[index] )
            }
        })
}

fun createDataListAksesoris():List<TestDataAcc> {
    val list = mutableListOf<TestDataAcc>()
    list.add(TestDataAcc("LM GT4 Aluminum Road Wheel Machining Logo", R.drawable.pelkz,"IDR 90.000.000"))
    list.add(TestDataAcc("NISSAN GT-R (R35) Nissan Genuine Brake Kit", R.drawable.kaliperz, "IDR 30.000.000"))
    list.add(TestDataAcc("NISMO LightWeight Suspension Link Series", R.drawable.armz, "IDR 10.000.000"))
    return list
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GridItemAksesoris(testDataAcc: TestDataAcc){
    val lContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false)  }
    Card(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable { openDialog.value = true }
        ,
        backgroundColor = hitamkren,
        elevation = 0.dp

    ) {
        Column {
            Image(
                painter = painterResource(id = testDataAcc.gambar),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding( start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                ClickableText(
                    text = AnnotatedString(testDataAcc.judul),
                    onClick = {openDialog.value = true},
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = anekMedium
                    )
                )
                if (openDialog.value) {
                    Dialog(
                        onDismissRequest = { openDialog.value = false },
                        properties = DialogProperties(usePlatformDefaultWidth = false),
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(hitamkren)
                                    .padding(start = 15.dp, end = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = " ")
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp)
                                    .clip(RoundedCornerShape(10.dp)),

                                ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 15.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(painter = painterResource(id = R.drawable.backred), contentDescription = "Logo",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .clickable { openDialog.value = false }

                                    )
                                    Text(text = stringResource(id = R.string.det), fontFamily = anekMedium, fontSize = 21.sp, modifier = Modifier.padding(top = 2.dp))
                                }
                                Image(
                                    painter = painterResource(id = testDataAcc.gambar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(350.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = testDataAcc.judul, modifier = Modifier.padding(top = 17.dp), fontSize = 19.sp, fontFamily = anekMedium
                                )
                                Text(text =  testDataAcc.harga, fontSize = 16.sp, color = grey, fontFamily = anekLight,modifier = Modifier.padding(top = 10.dp))
                                Divider(
                                    color = abu,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 15.dp)
                                ){
                                    val context = LocalContext.current
                                    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current
                                    Image(painter = painterResource(id = R.drawable.rating), contentDescription = "", modifier = Modifier.height(20.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.share),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(25.dp)
                                            .clickable {
                                                clipboardManager.setText(AnnotatedString(("HGTG3234I-PARTS")))
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "ID Copied to Clipboard",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                    )
                                }
                                Text(text =  stringResource(id = R.string.desc), fontSize = 19.sp, color = white, fontFamily = anekLight,modifier = Modifier.padding(top = 10.dp))
                                val listya = listOf(
                                    "Original Genuine Products",
                                    "Limited Pieces Only 1000 Items In The World",
                                    "High Quality Material",
                                    "NISSAN MOTORSPORT Japan Made"
                                )
                                LazyColumn {
                                    items(listya) {
                                        Row(Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                                            Canvas(modifier = Modifier
                                                .padding(end = 8.dp)
                                                .size(6.dp)){
                                                drawCircle(Color.White)
                                            }
                                            Text(text = it,fontSize = 14.sp)
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
                                            .width(200.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                                        onClick = {
                                            lContext.startActivity(
                                                Intent(lContext, WheelActivity::class.java)
                                            ) },
                                    ) {
                                        Text(text = stringResource(id = R.string.bli), fontFamily = anekMedium, color = white,fontSize = 15.sp)
                                    }
                                }
//                                Column(
//                                    horizontalAlignment = Arrangement.End,
//                                    modifier = Modifier
//                                        .padding(top = 15.dp)
//                                ) {

//                                }
                            }
                        }
                    }
//                    AlertDialog(
//                        backgroundColor = coklatTua,
//                        onDismissRequest = {
//                            openDialog.value = false
//                        },
//                        properties = DialogProperties(usePlatformDefaultWidth = false),
//                        title = {
//                            Text(
//                                text = stringResource(id = R.string.konfbayar),
//                                fontFamily = anekBold,
//                                fontSize = 20.sp,
//                                color = hitamkren,
//                            )
//                        },
//                        text = {
//                            Column {
//                                Text(
//                                    text = stringResource(id = R.string.selektetitem),
//                                    fontFamily = anekMedium,
//                                    fontSize = 16.sp,
//                                    color = hitamkren
//                                )
//                                Divider(
//                                    color = abu,
//                                    thickness = 1.dp,
//                                    modifier = Modifier
//                                        .fillMaxWidth())
//                                LazyColumn(
//                                    content = {
//                                        items(vm.cartList.size) { index ->
//                                            Text(text = vm.cartList[index].judul, fontFamily = anekLight, fontSize = 16.sp, color = hitamkren)
//                                            Text(text = vm.cartList[index].hargadis, fontFamily = anekLight, fontSize = 16.sp, color = merahNismo)
//                                        }
//                                    })
//                                Row() {
//                                    Text(
//                                        text = "Total : ",
//                                        fontFamily = anekBold,
//                                        fontSize = 18.sp,
//                                        color = hitamkren
//                                    )
//                                    Text(
//                                        text = "IDR 41.400.000",
//                                        fontFamily = anekBold,
//                                        fontSize = 18.sp,
//                                        color = merahNismo
//                                    )
//                                }
//                                Text(
//                                    text = stringResource(id = R.string.metodi),
//                                    fontFamily = anekMedium,
//                                    fontSize = 16.sp,
//                                    color = hitamkren
//                                )
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                ) {
//                                    Image(
//                                        painter = painterResource(id = R.drawable.masterkard),
//                                        contentDescription = "masterkard",
//                                        modifier = Modifier
//                                            .fillMaxWidth(0.2f)
//                                            .clip(RoundedCornerShape(9.dp))
//                                            .background(coklatmuda)
//                                            .width(90.dp)
//                                            .clickable { }
//                                    )
//                                    Image(
//                                        painter = painterResource(id = R.drawable.paypal),
//                                        contentDescription = "paypal",
//                                        modifier = Modifier
//                                            .fillMaxWidth(0.4f)
//                                            .clip(RoundedCornerShape(9.dp))
//                                            .background(coklatmuda)
//                                            .padding(top = 5.dp, bottom = 5.dp, start = 6.dp)
//                                            .clickable { }
//                                    )
//                                }
//                            }
//
//                        },
//                        confirmButton = {
//                            Button(
//                                modifier = Modifier
//                                    .clip(RoundedCornerShape(10.dp))
//                                    .width(80.dp),
//                                onClick = { openDialog.value = false },
//                            ) {
//                                Text(text = stringResource(id = R.string.bayar), fontFamily = anekMedium, color = merahNismo,fontSize = 15.sp)
//                            }
//                        },
//                        dismissButton = {
//                            Button(
//                                modifier = Modifier
//                                    .clip(RoundedCornerShape(10.dp))
//                                    .width(80.dp),
//                                onClick = { openDialog.value = false },
//                            ) {
//                                Text(text = stringResource(id = R.string.btal), fontFamily = anekMedium, color = merahNismo, fontSize = 15.sp)
//                            }
//                        }
//                    )
                }
                Text(text =  testDataAcc.harga, fontSize = 11.sp, color = Color.White, fontFamily = anekLight)
            }

        }

    }
}

@Composable
fun jduduldanlogo3(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
    ) {
        Text(text = stringResource(id = R.string.apparel), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren)
    }
}

@Composable
fun Produk2(){
    val list = createDataListAksesoris2()

    LazyRow(
        modifier = Modifier
            .height(250.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(list.size) {index ->
                GridItemAksesoris2(testDataAcc = list[index] )
            }
        })
}

fun createDataListAksesoris2():List<TestDataAcc> {
    val list = mutableListOf<TestDataAcc>()
    list.add(TestDataAcc("Nismo Racing Basic Logo Hoodie Black on Black", R.drawable.hudi2,"IDR 90.000.000"))
    list.add(TestDataAcc("Nismo Classic Logo Hoodie Red on Black", R.drawable.hudi, "IDR 30.000.000"))
    list.add(TestDataAcc("Nismo x Motul GranPrix Racing Team Polo", R.drawable.polo, "IDR 10.000.000"))
    return list
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GridItemAksesoris2(testDataAcc: TestDataAcc){
    val lContext = LocalContext.current
    val openDialog = remember { mutableStateOf(false)  }
    Card(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable { openDialog.value = true },
        backgroundColor = hitamkren,
        elevation = 0.dp

    ) {
        Column {
            Image(
                painter = painterResource(id = testDataAcc.gambar),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding( start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                if (openDialog.value) {
                    Dialog(
                        onDismissRequest = { openDialog.value = false },
                        properties = DialogProperties(usePlatformDefaultWidth = false),
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(hitamkren)
                                    .padding(start = 15.dp, end = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = " ")
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp)
                                    .clip(RoundedCornerShape(10.dp)),

                                ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 15.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(painter = painterResource(id = R.drawable.backred), contentDescription = "Logo",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .clickable { openDialog.value = false }

                                    )
                                    Text(text = stringResource(id = R.string.det), fontFamily = anekMedium, fontSize = 21.sp, modifier = Modifier.padding(top = 2.dp))
                                }
                                Image(
                                    painter = painterResource(id = testDataAcc.gambar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(350.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = testDataAcc.judul, modifier = Modifier.padding(top = 17.dp), fontSize = 19.sp, fontFamily = anekMedium
                                )
                                Text(text =  testDataAcc.harga, fontSize = 16.sp, color = grey, fontFamily = anekLight,modifier = Modifier.padding(top = 10.dp))
                                Divider(
                                    color = abu,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 15.dp)
                                ){
                                    val context = LocalContext.current
                                    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current
                                    Image(painter = painterResource(id = R.drawable.rating), contentDescription = "", modifier = Modifier.height(20.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.share),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(25.dp)
                                            .clickable {
                                                clipboardManager.setText(AnnotatedString(("HGTG3234I-APPAREL")))
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "ID Copied to Clipboard",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                    )
                                }
                                Text(text =  stringResource(id = R.string.desc), fontSize = 19.sp, color = white, fontFamily = anekLight,modifier = Modifier.padding(top = 10.dp))
                                val listya = listOf(
                                    "Original Apparel Products",
                                    "Limited Pieces",
                                    "High Quality Material",
                                    "NISSAN MOTORSPORT Japan Made"
                                )
                                LazyColumn {
                                    items(listya) {
                                        Row(Modifier.padding(top = 8.dp),verticalAlignment = Alignment.CenterVertically) {
                                            Canvas(modifier = Modifier
                                                .padding(end = 8.dp)
                                                .size(6.dp)){
                                                drawCircle(Color.White)
                                            }
                                            Text(text = it,fontSize = 14.sp)
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
                                            .width(200.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                                        onClick = {
                                            lContext.startActivity(
                                                Intent(lContext, WheelActivity::class.java)
                                            ) },
                                    ) {
                                        Text(text = stringResource(id = R.string.bli), fontFamily = anekMedium, color = white,fontSize = 15.sp)
                                    }
                                }
                            }
                        }
                    }
                }
                Text(text = testDataAcc.judul, fontSize = 12.sp, color = Color.White, fontFamily = anekMedium)
                Text(text =  testDataAcc.harga, fontSize = 11.sp, color = Color.White, fontFamily = anekLight)
            }

        }

    }
}



data class TestDataAcc(
    val judul : String,
    val gambar : Int,
    val harga : String,
)



//@Composable
//fun aksesoris(){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//            .padding(start = 15.dp, end = 15.dp)
//            .clip(RoundedCornerShape(10.dp)),
//        elevation = 0.dp,
//        backgroundColor = hitamkren
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 5.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.nism_o),
//                    contentDescription = "logokcil" ,
//                    modifier = Modifier
//                        .size(20.dp)
//                        .padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
//                )
//                Text(text = stringResource(id = R.string.apparel), fontFamily = anekBold, color = Color.White)
//                Image(
//                    painter = painterResource(id = R.drawable.nism_o),
//                    contentDescription = "logokcil" ,
//                    modifier = Modifier
//                        .size(20.dp)
//                        .padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
//                )
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.hoodie),
//                    contentDescription = "hoodi" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.keychain),
//                    contentDescription = "keych" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 6.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.shirt),
//                    contentDescription = "shirt" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.umbrella),
//                    contentDescription = "umbr" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 6.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.pants),
//                    contentDescription = "pants" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.stickers),
//                    contentDescription = "sticker" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 6.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.headwear),
//                    contentDescription = "headwer" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.tumbker),
//                    contentDescription = "tumbler" ,
//                    modifier = Modifier
//                        .height(32.dp)
//                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
//                )
//            }
//
//        }
//
//    }
//}

//@Composable
//fun BottomComponent( ){
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(navController = navController)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultsPreview() {
    PAM_ActivityIntentTheme {
//        HomeScreen()
        BottomNavigationMainScreenView()
    }
}

