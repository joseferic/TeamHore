package com.example.login_post.kuis

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quizzes(
        var id_Quiz: Int,
        var pict_Quiz: String,
        var type_Quiz: String,
        var option1_Quiz: String,
        var option2_Quiz: String,
        var correctanswer_Quiz: Int
):Parcelable
