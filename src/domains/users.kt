package domains

import models.Resource
import models.User

val Users: Array<User> = arrayOf(
        User("ArtBekk", "e858c98a18d35a86c7c7206dc13ba9cb6643f6a4223d8ea8d05fda2ef7da5023",
                arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
                arrayOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
                arrayOf(Resource("GG.WP"))),

        User("AdamHiggs", "89cd4d1f29716d8262789a7444320538ee9b401b6384a9c92351046edbd9ebf5",
                arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.NAJ")),
                arrayOf(Resource("AH"), Resource("BB"), Resource("LR.E")),
                arrayOf(Resource("ZB"))),

        User("user", "de5d44500b6d1649d10165845e2df21ba323aa94e9f157ca970a03d87c100b48",
                null,
                arrayOf(Resource("AC"), Resource("B.EX.DFADS"), Resource("CD.E")),
                arrayOf(Resource("GL.HF"))))