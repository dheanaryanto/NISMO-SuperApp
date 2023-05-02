package edu.uksw.fti.pam.pam_activityintent.models

import com.google.gson.annotations.SerializedName

data class Berita(
    @SerializedName("gambar")
    var gambar: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("subtitle")
    var subtitle: String,
)
