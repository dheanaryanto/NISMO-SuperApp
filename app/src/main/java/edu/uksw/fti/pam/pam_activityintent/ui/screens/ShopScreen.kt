package edu.uksw.fti.pam.pam_activityintent.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*


@Composable
fun ShopScreen() {
    Column(
        modifier = Modifier
            .background(coklatmuda)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)

    ){
        hidir()
        jdulcart()
        MyCart()
        DividerEtImpera2()
        MenuBayar()
    }
}

@Composable
fun jdulcart(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Text(text = stringResource(id = R.string.cart), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren)
        Image(painter = painterResource(id = R.drawable.nismo), contentDescription = "logonismo",
            modifier = Modifier
                .width(110.dp)
                .height(30.dp)
                .padding(top = 1.dp)

        )
    }
}

@Composable
fun MyCart(){
    Row(
        modifier = Modifier
            .height(480.dp)
            .fillMaxWidth()
    ) {
        Divider(
            color = kaburz,
            modifier = Modifier
                .fillMaxHeight()
                .width(10.dp)
                .padding(start = 6.dp, top = 6.dp, bottom = 6.dp)

        )
        LazyColumn(
            contentPadding = PaddingValues(start = 0.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
            modifier = Modifier
                .height(475.dp),
            content = {
//                items(list.size) {index ->
//                    GridItemCart(testData3 = list[index] )
//                }
//                items(vm.cartList.size) { index ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(4.dp),
//                        backgroundColor = hitamkren,
//                        elevation = 12.dp,
//                        shape = RoundedCornerShape(6.dp)
//
//                    ) {
//                        Column(modifier = Modifier.fillMaxWidth()) {
//                            Row(
//                            ) {
//                                Image(
//                                    painter = rememberAsyncImagePainter(vm.cartList[index].gambar),
//                                    contentDescription = "",
//                                    modifier = Modifier
//                                        .width(100.dp)
//                                        .height(100.dp)
//                                        .aspectRatio(1f)
//                                        .padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//                                        .clip(RoundedCornerShape(6.dp)),
//                                    contentScale = ContentScale.Crop
//                                )
//                                Column(
//                                    modifier = Modifier.background(hitamkren)
//                                ){
//                                    Text(
//                                        text = vm.cartList[index].judul, fontSize = 13.sp, color = Color.White, fontFamily = anekMedium,
//                                        modifier = Modifier.padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//
//                                    )
//                                    Row() {
//                                        Text(
//                                            text = vm.cartList[index].harga, fontSize = 12.sp, color = kaburz, fontFamily = anekLight,
//                                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
//                                            modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//                                        )
//                                        Text(
//                                            text = vm.cartList[index].hargadis, fontSize = 12.sp, color = Color.White, fontFamily = anekLight,
//                                            modifier = Modifier.padding(top = 0.dp, start = 0.dp, end = 6.dp, bottom = 6.dp)
//                                        )
//                                    }
//                                    Text(
//                                        text = stringResource(id = R.string.estimation), fontSize = 12.sp, color = kaburz, fontFamily = anekLight,
//                                        modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//                                    )
//                                    Row(){
//                                        Text(text =  stringResource(id = R.string.qty), fontSize = 12.sp, color = kaburz, fontFamily = anekLight,
//                                            modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//                                        )
//
//                                    }
//
//
//                                }
//
//                            }
//                            Row() {
//                                Image(
//                                    painter = painterResource(id = R.drawable.vocput),
//                                    contentDescription = "voucher putih",
//                                    modifier = Modifier
//                                        .padding(start = 6.dp, top = 3.dp)
//                                        .height(10.dp)
//                                )
//                                Text(
//                                    text = stringResource(id = R.string.diskz), fontSize = 12.sp, color = Color.White, fontFamily = anekLight,
//                                    modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
//                                )
//                            }
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                horizontalArrangement = Arrangement.End
//                            ) {
//                                val checkedState = remember { mutableStateOf(true) }
//                                Text(
//                                    text = stringResource(id = R.string.cekot),
//                                    fontSize = 14.sp,
//                                    fontFamily = anekLight,
//                                    modifier = Modifier
//                                        .padding(top = 0.dp)
//                                )
//                                Checkbox(
//                                    checked = checkedState.value,
//                                    modifier = Modifier
//                                        .padding(top = 6.dp)
//                                        .height(2.dp),
//                                    onCheckedChange = { checkedState.value = it },
//                                )
//                            }
//                        }
//
//                    }
//                }
            })
    }


}

@Composable
fun DividerEtImpera2(){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 10.dp)
    ) {
        Divider(
            color = hitamkren,
            thickness = 1.dp,
            modifier = Modifier
                .width(130.dp)
                .padding(top = 9.dp, end = 6.dp)
        )
        Text(
            text = stringResource(id = R.string.mnucekot),
            fontSize = 13.sp,
            fontFamily = anekMedium,
            color = hitamkren
        )
        Divider(
            color = hitamkren,
            thickness = 1.dp,
            modifier = Modifier
                .width(130.dp)
                .padding(top = 9.dp, start = 6.dp)
        )
    }
}

@Composable
fun MenuBayar(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp, start = 9.dp, end = 9.dp),
        backgroundColor = hitamkren,
        shape = RoundedCornerShape(6.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, end = 0.dp, start = 6.dp, bottom = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                val checkedState = remember { mutableStateOf(true) }
                Text(
                    text = stringResource(id = R.string.cekotol),
                    fontSize = 14.sp,
                    fontFamily = anekLight,
                    modifier = Modifier
                        .padding(top = 0.dp)
                )
                Checkbox(
                    checked = checkedState.value,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .height(2.dp),
                    onCheckedChange = { checkedState.value = it },
                )
            }
            Text(text = "Total :",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 12.dp),
                fontFamily = anekLight,
                fontSize = 20.sp,
                color = Color.White)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "IDR 5.400.000", fontFamily = anekBold, fontSize = 21.sp, color = Color.White)
                Text(text = "FedEx International Shipping", fontFamily = anekLight, fontSize = 15.sp, color = kaburz, modifier = Modifier.padding(bottom = 3.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                val openDialog = remember { mutableStateOf(false)  }
                Button(
                    onClick = { openDialog.value = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = merahNismo),
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .height(35.dp)
                )
                {
                    Text(text = "CheckOut",color = Color.White, fontSize = 14.sp, fontFamily = anekMedium)
                }
                if (openDialog.value) {
                    AlertDialog(
                        backgroundColor = coklatTua,
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(
                                text = stringResource(id = R.string.konfbayar),
                                fontFamily = anekBold,
                                fontSize = 20.sp,
                                color = hitamkren,
                            )
                        },
                        text = {
                            Column {
                                Text(
                                    text = stringResource(id = R.string.selektetitem),
                                    fontFamily = anekMedium,
                                    fontSize = 16.sp,
                                    color = hitamkren
                                )
                                Divider(
                                    color = abu,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth())
                                LazyColumn(
                                    content = {
                                    })
                                Row() {
                                    Text(
                                        text = "Total : ",
                                        fontFamily = anekBold,
                                        fontSize = 18.sp,
                                        color = hitamkren
                                    )
                                    Text(
                                        text = "IDR 46.000.000",
                                        fontFamily = anekBold,
                                        fontSize = 18.sp,
                                        color = merahNismo
                                    )
                                }
                                Text(
                                    text = stringResource(id = R.string.metodi),
                                    fontFamily = anekMedium,
                                    fontSize = 16.sp,
                                    color = hitamkren
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.masterkard),
                                        contentDescription = "masterkard",
                                        modifier = Modifier
                                            .fillMaxWidth(0.2f)
                                            .clip(RoundedCornerShape(9.dp))
                                            .background(coklatmuda)
                                            .width(90.dp)
                                            .clickable { }
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.paypal),
                                        contentDescription = "paypal",
                                        modifier = Modifier
                                            .fillMaxWidth(0.4f)
                                            .clip(RoundedCornerShape(9.dp))
                                            .background(coklatmuda)
                                            .padding(top = 5.dp, bottom = 5.dp, start = 6.dp)
                                            .clickable { }
                                    )
                                }
                            }

                        },
                        confirmButton = {
                            Button(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .width(80.dp),
                                onClick = { openDialog.value = false },
                                ) {
                                Text(text = stringResource(id = R.string.bayar), fontFamily = anekMedium, color = merahNismo,fontSize = 15.sp)
                            }
                        },
                        dismissButton = {
                            Button(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .width(80.dp),
                                onClick = { openDialog.value = false },
                            ) {
                                Text(text = stringResource(id = R.string.btal), fontFamily = anekMedium, color = merahNismo, fontSize = 15.sp)
                            }
                        }
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultzPreview() {
    PAM_ActivityIntentTheme {
        MyCart()
    }
}

