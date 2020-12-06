import models.User
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions
import org.mockito.BDDMockito
import org.mockito.Mockito
import services.getDBConnection

val mockedHandler: Handler = Mockito.mock(Handler::class.java)
val dal = DataAccessLayer(getDBConnection("jdbc:h2:file:./db/aaa", "ArtBekk", "3678"))

internal class DataAccessLayerTestSpec : Spek({
    describe("Class tests for working with data") {
        it("User name is found") {
            BDDMockito.given(mockedHandler.login).willReturn("ArtBekk")
            val result = dal.userExists(mockedHandler.login!!)
            Assertions.assertEquals(true, result)
        }

        it("User name not found") {
            BDDMockito.given(mockedHandler.login).willReturn("AAA")
            val result = dal.userExists(mockedHandler.login!!)
            Assertions.assertEquals(false, result)
        }

        it("Getting information about user access") {
            BDDMockito.given(mockedHandler.login).willReturn("ArtBekk")
            BDDMockito.given(mockedHandler.role).willReturn("READ")
            val result = dal.getUserAccessInfo(mockedHandler.login!!, mockedHandler.role!!)
            Assertions.assertEquals(mutableListOf("AV", "BB", "CD.E"), result)
        }

        it("The user is found") {
            BDDMockito.given(mockedHandler.login).willReturn("ArtBekk")
            val result = dal.getUser(mockedHandler.login!!)
            Assertions.assertEquals(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                ).login,
                result.login
            )
            Assertions.assertEquals(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                ).hash,
                result.hash
            )
            Assertions.assertEquals(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                ).salt,
                result.salt
            )
        }

        it("The user is not found") {
            BDDMockito.given(mockedHandler.login).willReturn("Unknown")
            val result = dal.getUser(mockedHandler.login!!)
            Assertions.assertEquals(
                User("", "", "").login,
                result.login
            )
            Assertions.assertEquals(
                User("", "", "").hash,
                result.hash
            )
            Assertions.assertEquals(
                User("", "", "").salt,
                result.salt
            )
        }
    }
})
