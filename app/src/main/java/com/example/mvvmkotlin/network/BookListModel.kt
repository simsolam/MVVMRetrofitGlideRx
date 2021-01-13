package com.example.mvvmkotlin.network

data class BookListModel(val items: ArrayList<VolumeInfo>)
data class VolumeInfo(val volumeInfo: BookInfo)
data class BookInfo(val title:String, val publisher: String, val description: String, val imageUrl:ImageUrl)
data class ImageUrl(val smallThumbnail:String)