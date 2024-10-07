package com.android.encora.weather.model

import android.os.Parcel
import android.os.Parcelable

/*
ParcelableResult is a class made for the tranfer of object from screen to screen via Activities
*/
class ParcelableResult(
    val artistId: Int?,
    val artistName: String?,
    val artistViewUrl: String?,
    val artworkUrl100: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val collectionCensoredName: String?,
    val collectionExplicitness: String?,
    val collectionId: Int?,
    val collectionName: String?,
    val collectionPrice: Double?,
    val collectionViewUrl: String?,
    val country: String?,
    val currency: String?,
    val discCount: Int?,
    val discNumber: Int?,
    val kind: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackCensoredName: String?,
    val trackCount: Int?,
    val trackExplicitness: String?,
    val trackId: Int?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackTimeMillis: Long?,
    val trackViewUrl: String?,
    val wrapperType: String?,
           ) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<ParcelableResult> {
            override fun createFromParcel(parcel: Parcel) = ParcelableResult(parcel)
            override fun newArray(size: Int) = arrayOfNulls<ParcelableResult>(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(artistId!!)
        parcel.writeString(artistName)
        parcel.writeString(artistViewUrl)
        parcel.writeString(artworkUrl100)
        parcel.writeString(artworkUrl30)
        parcel.writeString(artworkUrl60)
        parcel.writeString(collectionCensoredName)
        parcel.writeString(collectionExplicitness)
        parcel.writeInt(collectionId!!)
        parcel.writeString(collectionName)
        parcel.writeDouble(collectionPrice!!)
        parcel.writeString(collectionViewUrl)
        parcel.writeString(country)
        parcel.writeString(currency)
        parcel.writeInt(discCount!!)
        parcel.writeInt(discNumber!!)
        parcel.writeString(kind)
        parcel.writeString(previewUrl)
        parcel.writeString(primaryGenreName)
        parcel.writeString(releaseDate)
        parcel.writeString(trackCensoredName)
        parcel.writeInt(trackCount!!)
        parcel.writeString(trackExplicitness)
        parcel.writeInt(trackId!!)
        parcel.writeString(trackName)
        parcel.writeInt(trackNumber!!)
        parcel.writeDouble(trackPrice!!)
        parcel.writeLong(trackTimeMillis!!)
        parcel.writeString(trackViewUrl)
        parcel.writeString(wrapperType)
    }

    private constructor(parcel: Parcel) : this(
        artistId = parcel.readInt(),
        artistName = parcel.readString().toString(),
        artistViewUrl = parcel.readString().toString(),
        artworkUrl100 = parcel.readString().toString(),
        artworkUrl30 = parcel.readString().toString(),
        artworkUrl60 = parcel.readString().toString(),
        collectionCensoredName = parcel.readString().toString(),
        collectionExplicitness = parcel.readString().toString(),
        collectionId = parcel.readInt(),
        collectionName = parcel.readString().toString(),
        collectionPrice = parcel.readDouble(),
        collectionViewUrl = parcel.readString().toString(),
        country = parcel.readString().toString(),
        currency = parcel.readString().toString(),
        discCount = parcel.readInt(),
        discNumber = parcel.readInt(),
        kind = parcel.readString().toString(),
        previewUrl = parcel.readString().toString(),
        primaryGenreName = parcel.readString().toString(),
        releaseDate = parcel.readString().toString(),
        trackCensoredName = parcel.readString().toString(),
        trackCount = parcel.readInt(),
        trackExplicitness = parcel.readString().toString(),
        trackId = parcel.readInt(),
        trackName = parcel.readString().toString(),
        trackNumber = parcel.readInt(),
        trackPrice = parcel.readDouble(),
        trackTimeMillis = parcel.readLong(),
        trackViewUrl = parcel.readString().toString(),
        wrapperType = parcel.readString().toString(),
    )

    override fun describeContents() = 0
}