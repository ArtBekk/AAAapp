import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

class JettyServer {

    fun start() {
        val handler = createHandler()
        Server(8080).apply {
            setHandler(handler)
            start()
        }
    }

    private fun createHandler(): WebAppContext {
        return WebAppContext().apply {
            resourceBase = "/"
            addServlet("EchoServlet", "echo/*")
        }
    }
}