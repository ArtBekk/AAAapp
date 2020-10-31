package domains

import models.Resource
import models.User

val Users: Array<User> = arrayOf(
        User("ArtBekk", "e858c98a18d35a86c7c7206dc13ba9cb6643f6a4223d8ea8d05fda2ef7da5023",
                arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
                arrayOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
                arrayOf(Resource("GG"))),

        User("AdamHiggs", "89cd4d1f29716d8262789a7444320538ee9b401b6384a9c92351046edbd9ebf5",
                arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
                arrayOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
                arrayOf(Resource("GG"))))