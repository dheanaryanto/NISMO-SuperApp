package edu.uksw.fti.pam.pam_activityintent.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import edu.uksw.fti.pam.pam_activityintent.AccountActivity
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*
import edu.uksw.fti.pam.pam_activityintent.R
import edu.uksw.fti.pam.pam_activityintent.WheelActivity
import edu.uksw.fti.pam.pam_activityintent.models.*

private val vmp = PartViewModel()

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PartsScreen() {
    Column(
        modifier = Modifier
            .background(coklatmuda)
            .fillMaxSize()

    ){
//        ExpandableSearchView(
//            searchDisplay = "",
//            onSearchDisplayChanged = {},
//            onSearchDisplayClosed = {}
//        )
        hidir()
        jdulparts()

        val partListState = remember { mutableStateOf(emptyList<Part>()) }

        LaunchedEffect(vmp.partList) {
            vmp.partList.observeForever { newList ->
                partListState.value = newList
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 60.dp),
        ) {
            items(partListState.value) { part ->
                val lContext = LocalContext.current
                val openDialog = remember { mutableStateOf(false)  }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable { openDialog.value = true },
                    elevation = 12.dp,
                    backgroundColor = hitamkren,
                    shape = RoundedCornerShape(6.dp)

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(part.gambar),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .aspectRatio(1f)
                                .padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(6.dp)),
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
                                            painter = rememberAsyncImagePainter(part.gambar),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(350.dp)
                                                .clip(RoundedCornerShape(10.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(
                                            text = part.judul, modifier = Modifier.padding(top = 17.dp), fontSize = 19.sp, fontFamily = anekMedium
                                        )
                                        Text(text =  part.harga, fontSize = 16.sp, color = grey, fontFamily = anekLight,modifier = Modifier.padding(top = 10.dp))
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
                                            val clipboardManager: ClipboardManager = LocalClipboardManager.current
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
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier.background(hitamkren)
                        ){
                            Text(
                                text = part.judul, fontSize = 12.sp, color = Color.White,
                                modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)

                            )
                            Text(
                                text = part.harga, fontSize = 10.sp, color = Color.White,
                                modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 6.dp)

                            )
                        }



                    }
                }
            }
        }

    }
}

@Composable
fun hidir(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(coklatTua)
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)

    ){
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.nism_o), contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(top = 1.dp)
            )
        }
        val lContext = LocalContext.current
        Image(
            painter = painterResource(id = R.drawable.akun),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 1.dp)
                .height(35.dp)
                .width(33.dp)
                .clickable {
                    lContext.startActivity(
                        Intent(lContext, AccountActivity::class.java)
                    )
                })

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
//        }



        }
}

//@Composable
//fun hidir(){
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
//                    .padding( top = 1.dp)
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
fun jdulparts(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Text(text = stringResource(id = R.string.parts), fontSize = 20.sp, fontFamily = anekBold, color = hitamkren)
        Image(painter = painterResource(id = R.drawable.nismo), contentDescription = "logonismo",
            modifier = Modifier
                .width(110.dp)
                .height(30.dp)
                .padding(top = 1.dp)

        )
    }
}

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = hitamkren
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }


    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon",
        tint = hitamkren,
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = hitamkren,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(coklatTua)
            .padding(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.nism_o), contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = hitamkren,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(coklatTua),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onExpandedChanged(false)
            onSearchDisplayClosed()
        }) {
            Image(painter = painterResource(id = R.drawable.nism_o), contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = white,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(textFieldFocusRequester)
                .background(hitamkren),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )

        )
    }
}
