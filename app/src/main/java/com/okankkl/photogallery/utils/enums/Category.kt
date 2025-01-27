package com.okankkl.photogallery.utils.enums

enum class Category : FilterType {
    Background{
        override val query = "backgrounds"
        override val label = "Backgrounds"
    },
    Fashion{
        override val query = "fashion"
        override val label = "Fashion"
    },
    Nature{
        override val query = "nature"
        override val label = "Nature"
    },
    Science{
        override val query = "science"
        override val label = "Science"
    },
    Education{
        override val query = "education"
        override val label = "Education"
    },
    Feelings{
        override val query = "feelings"
        override val label = "Feelings"
    },
    Health{
        override val query = "health"
        override val label = "Health"
    },
    People{
        override val query = "people"
        override val label = "People"
    },
    Religion{
        override val query = "religion"
        override val label = "Religion"
    },
    Places{
        override val query = "places"
        override val label = "Places"
    },
    Animals{
        override val query = "animals"
        override val label = "Animals"
    },
    Industry{
        override val query = "industry"
        override val label = "Industry"
    },
    Computer{
        override val query = "computer"
        override val label = "Computer"
    },
    Sports{
        override val query = "sports"
        override val label = "Sports"
    },
    Transportation{
        override val query = "transportation"
        override val label = "Transportation"
    },
    Travel{
        override val query = "travel"
        override val label = "Travel"
    },
    Buildings{
        override val query = "buildings"
        override val label = "Buildings"
    },
    Music{
        override val query = "music"
        override val label = "Music"
    }
}