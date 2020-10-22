import models.Resource
import models.User
import services.hashSalt

val Users: Array<User> = arrayOf(User("ArtBekk", hashSalt("3678"),
        arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
        arrayOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
        arrayOf(Resource("GG"))),
        User("AdamHiggs", hashSalt("3678"),
                arrayOf(Resource("AA"), Resource("AD.BD"), Resource("AC.BAE")),
                arrayOf(Resource("AV"), Resource("BB"), Resource("CD.E")),
                arrayOf(Resource("GG"))))//create passwords