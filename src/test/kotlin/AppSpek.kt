import models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions
import org.mockito.BDDMockito
import org.mockito.Mockito

class AppSpek : Spek({
    val mockedDAL: DataAccessLayer = Mockito.mock(DataAccessLayer::class.java)
    val logger: Logger = LogManager.getLogger()
    describe("Tests of app class and services that it uses") {
        logger.info("Starting spek tests for App class")
        it("Authentication: UnknownLogin") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678"))
            val sampleApp = App(mockedDAL, handler)
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(false)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.UnknownLogin.value, result)
        }
        it("Authentication: IncorrectLoginFormat") {
            val handler = Handler(arrayOf("-login", "@rtB#kk", "-pass", "3678"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.IncorrectLoginFormat.value, result)
        }
        it("Authentication: WrongPassword") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678"))
            val sampleApp = App(mockedDAL, handler)
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59jkcb971c7ea74110",
                    "c69de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.WrongPassword.value, result)
        }
        it("Authentication: Success") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678"))
            val sampleApp = App(mockedDAL, handler)
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.Success.value, result)
        }
        it("Authorization: no such role") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678", "-role", "DELETE", "-res", "A"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.UnknownRole.value, result)
        }
        it("Authorization: No access") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A"))
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableListOf())
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.NoAccess.value, result)
        }
        it("Authorization: Success") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A"))
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableListOf("A"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.Success.value, result)
        }
        it("Authorization: No Access") {
            val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A"))
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            val mutableList = mutableListOf("A.C.D", "A.D.CD", "D.E")
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableList)
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.NoAccess.value, result)
        }
        it("Accounting: Incorrect activity (Invalid volume)") {
            val handler = Handler(
                arrayOf(
                    "-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A",
                    "-vol", "10001", "-ds", "11-10-2020", "-de", "13-10-2020"
                )
            )
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableListOf("A"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.IncorrectActivity.value, result)
        }
        it("Accounting: Incorrect activity (Invalid date)") {
            val handler = Handler(
                arrayOf(
                    "-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A",
                    "-vol", "100", "-ds", "11-10-2020", "-de", "13242020"
                )
            )
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableListOf("A"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.IncorrectActivity.value, result)
        }
        it("Accounting: Success") {
            val handler = Handler(
                arrayOf(
                    "-login", "ArtBekk", "-pass", "3678", "-role", "READ", "-res", "A",
                    "-vol", "110", "-ds", "11-10-2020", "-de", "13-10-2020"
                )
            )
            BDDMockito.given(mockedDAL.userExists("ArtBekk")).willReturn(true)
            BDDMockito.given(mockedDAL.getUser("ArtBekk")).willReturn(
                User(
                    "ArtBekk",
                    "6d0f708df8a2ef0b6f8ddd64370303b371b0843038bc0a59decb971c7ea74110",
                    "c86de8b9a29e7cc29f3eb54f86568eb315358c02eead90b48ca0a4742448ad99"
                )
            )
            BDDMockito.given(mockedDAL.getUserAccessInfo("ArtBekk", "READ")).willReturn(mutableListOf("A"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.Success.value, result)
        }
        it("Shows usage") {
            val handler = Handler(arrayOf("-h"))
            val sampleApp = App(mockedDAL, handler)
            val result = sampleApp.run()
            Assertions.assertEquals(ExitCode.Success.value, result)
        }
    }
})
