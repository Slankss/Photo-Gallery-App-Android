package com.okankkl.photogallery.utils.enums

enum class Order : FilterType {
    Popular {
        override val query: String = "popular"
        override val label: String = "Popular"
    },
    Latest{
        override val query: String = "latest"
        override val label: String = "Latest"
    }
}