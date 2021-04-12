package com.example.plantslog.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class PlantData(
    val `data`: List<Data>,
    val links: LinksX,
    val meta: Meta
): Parcelable {
    @Parcelize
    data class Data(
        val author: String,
        val bibliography: String,
        val common_name: String,
        val family: String,
        val family_common_name: String,
        val genus: String,
        val genus_id: Int,
        val id: Int,
        val image_url: String,
        val links: Links,
        val rank: String,
        val scientific_name: String,
        val slug: String,
        val status: String,
        val synonyms: List<String>,
        val year: Int
    ) : Parcelable

    @Parcelize
    data class LinksX(
        val first: String,
        val last: String,
        val next: String,
        val self: String
    ) : Parcelable

    @Parcelize
    data class Meta(
        val total: Int
    ) : Parcelable

    @Parcelize
    data class Links(
        val genus: String,
        val plant: String,
        val self: String
    ) : Parcelable
}