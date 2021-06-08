package com.bangkitsubmission.pastiin_ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FruitDatas(
    var id_Fruit : Int?,
    var name_Fruit : String?,
    var fact_Fruit : String?,
    var pict_Fruit : String
): Parcelable
