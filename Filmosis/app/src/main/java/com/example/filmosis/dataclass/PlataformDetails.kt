package com.example.filmosis.dataclass

import com.google.gson.annotations.SerializedName

data class PlatformDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logoUrl: String,
//    val provider_id : Int

)
