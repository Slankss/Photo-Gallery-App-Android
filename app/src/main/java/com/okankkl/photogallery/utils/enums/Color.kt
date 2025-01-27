package com.okankkl.photogallery.utils.enums

enum class Color(
    val colorCode: Long
): FilterType {
    Black(
        colorCode = 0xff000000
    ){
        override val query = "black"
        override val label = "Black"
    },
    White(
        colorCode = 0xffffffff
    ){
        override val query = "white"
        override val label = "White"
    },
    Red(
        colorCode = 0xffef233c
    ){
        override val query = "red"
        override val label = "Red"
    },
    Yellow(
        colorCode = 0xffFFFC00
    ){
        override val query = "yellow"
        override val label = "Yellow"
    },
    Green(
        colorCode = 0xff4CFF00
    ){
        override val query = "green"
        override val label = "Green"
    },
    Blue(
        colorCode = 0xff0085FF
    ){
        override val query = "blue"
        override val label = "Blue"
    },
    Purple(
        colorCode = 0xffA600FF
    ){
        override val query = "purple"
        override val label = "Purple"
    },
    Orange(
        colorCode = 0xffFF8800
    ){
        override val query = "orange"
        override val label = "Orange"
    },
    Brown(
        colorCode = 0xff99582a
    ){
        override val query = "brown"
        override val label = "Brown"
    },
    Gray(
        colorCode = 0xffe5e5e5
    ){
        override val query = "gray"
        override val label = "Gray"
    },
    Pink(
        colorCode = 0xffffafcc
    ){
        override val query = "pink"
        override val label = "Pink"
    },
    Turquoise(
        colorCode = 0xff80ffdb
    ){
        override val query = "turquoise"
        override val label = "Turquoise"
    },
    Lilac(
        colorCode = 0xffe5b3fe
    ){
        override val query = "lilac"
        override val label = "Lilac"
    }
}