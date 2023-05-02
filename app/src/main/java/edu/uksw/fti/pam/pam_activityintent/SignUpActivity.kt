package edu.uksw.fti.pam.pam_activityintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.pam_activityintent.ui.screens.SignUpForm
import edu.uksw.fti.pam.pam_activityintent.ui.theme.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class  SignUpActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    val fFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            PAM_ActivityIntentTheme {
                // A surface container using the 'background' color from the theme
                Card(
                    modifier = Modifier
                        .background(coklatTua)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bgsatu),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )
                    SignUpForm(onClickAction = ::sendUsernameBackToLoginPage)
                }
            }
        }
    }

private fun sendUsernameBackToLoginPage(
    fnama: String?,
    lnama: String?,
    email: String?,
    paskon: String?,
    username: String?,
    password: String?,
) {

    auth.createUserWithEmailAndPassword(email!!, password!!)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                val result = Intent().putExtra("email", email)
                val email = FirebaseAuth.getInstance().currentUser!!.email
                val data = hashMapOf(
                    "username" to username,
                    "fnama" to fnama,
                    "lnama" to lnama,
                    "email" to email,
                )
                fFirestore.collection("users")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(applicationContext, "Berhasil Daftar",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{ exception ->
                        Toast.makeText(applicationContext, "Data gagal ditambahkan",Toast.LENGTH_SHORT).show()
                    }
                setResult(Activity.RESULT_OK, result)
                finish()
            } else {
                Toast.makeText(applicationContext, "Gagal membuat User", Toast.LENGTH_SHORT).show()
            }
        }

//    val fFirestore = Firebase.firestore
//    val data = hashMapOf(
//        "username" to username,
//        "fnama" to fnama,
//        "lnama" to lnama,
//        "email" to email,
//    )
//
//    fFirestore.collection("users")
//        .add(data)
//        .addOnSuccessListener {
//            // Data berhasil disimpan ke Firestore
//        }
//        .addOnFailureListener { e ->
//            // Handle error jika data gagal disimpan
//        }
//
//    auth.createUserWithEmailAndPassword(email!!, password!!)
//        .addOnCompleteListener {
//            if (it.isSuccessful) {
//                val result = Intent().putExtra("email", email)
//                setResult(Activity.RESULT_OK, result)
//                finish()
//            } else {
//                Toast.makeText(applicationContext, "Error Create User", Toast.LENGTH_SHORT).show()
//            }
//        }
}
}

