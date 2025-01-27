package com.okankkl.photogallery.utils.enums

enum class Screen(
    var route: String,
    var title: String
) {
    Gallery("gallery", "Gallery"),
    PhotoDetail("photo_detail", "Photo Detail")
}