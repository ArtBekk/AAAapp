import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.*

var mockedDAL: DataAccessLayer = mock(DataAccessLayer::class.java)

class AppSpek : Spek({
    given("App class") {
        val handler = Handler(arrayOf("-login", "ArtBekk", "-pass", "3678"))
        val sampleApp = App(mockedDAL, handler)
        on("launch") {
            val result = sampleApp.run()
            it("doesn't account") {
                Assertions.assertEquals(result, ExitCode.Success)
            }
        }
    }
})