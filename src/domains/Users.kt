package domains

import models.Resource
import models.User

val Users: List<User> = listOf(
        User("ArtBekk", "e858c98a18d35a86c7c7206dc13ba9cb6643f6a4223d8ea8d05fda2ef7da5023",
                write = listOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
                read = listOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
                execute = listOf(Resource("GG.WP"))),

        User("AdamHiggs", "89cd4d1f29716d8262789a7444320538ee9b401b6384a9c92351046edbd9ebf5",
                write = listOf(Resource("AA"), Resource("AD.BD"), Resource("AC.NAJ")),
                read = listOf(Resource("AH"), Resource("BB"), Resource("LR.E")),
                execute = listOf(Resource("ZB"))),

        User("user", "de5d44500b6d1649d10165845e2df21ba323aa94e9f157ca970a03d87c100b48",
                write = listOf(),
                read = listOf(Resource("AC"), Resource("B.EX.DFADS"), Resource("CD.E")),
                execute = listOf(Resource("GL.HF"))))
