package com.okankkl.photogallery.utils.enums

enum class Orientation(
): FilterType {
    Horizontal{
        override val query: String = "horizontal"
        override val label: String = "Horizontal"
    },
    Vertical{
        override val query: String = "vertical"
        override val label: String = "Vertical"
    }
}