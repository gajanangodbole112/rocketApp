package com.gajanan.rocketapp.modalClass

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull
import javax.annotation.Nonnull

@kotlinx.serialization.Serializable
@Entity(tableName = "rockets")
data class RocketDetailResponse(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Nonnull
    var id: String,

    @ColumnInfo("active")
    var active: Boolean?=null,

    @ColumnInfo("company")
    var company: String?=null,

    @ColumnInfo("cost_per_launch")
    var cost_per_launch: Int?=null,


    @ColumnInfo("country")
    var country: String?=null,

    @ColumnInfo("description")
    var description: String?=null,

    @Embedded var diameter: Diameter?=null,

    @Embedded var engines: Engines?=null,
    @ColumnInfo("flickr_images")
    val flickr_images: List<String>?=null,

    @Embedded var height: Height?=null,
     @ColumnInfo("name")
    var name: String?=null,

    @ColumnInfo("success_rate_pct")
    var success_rate_pct: Int?=null,

    @ColumnInfo("wikipedia")
    var wikipedia: String?= null,
    ){

}


@Entity
data class Diameter(
    @ColumnInfo("diameter_feet")
    @PrimaryKey(autoGenerate = false)
    var feet: Double

)


@Entity
data class Engines(
    @PrimaryKey(autoGenerate = false)
    var number: Int
)


@Entity
data class Height(
    @ColumnInfo("height_feet")
    @PrimaryKey(autoGenerate = false)
    var feet: Double

)
