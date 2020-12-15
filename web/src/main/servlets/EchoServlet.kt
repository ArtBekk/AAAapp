import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ["/echo-servlet", "/echo/get/*"])
class EchoServlet : HttpServlet() {

    @Throws(ServletException::class)
    override fun init(config: ServletConfig?) {
        super.init(config)
        log("LOG_init")
    }

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest?, resp: HttpServletResponse) {
        super.service(req, resp)
        resp.writer.write("service\n")
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val uri = req.requestURI
        val login = req.getParameter("login")
        resp.writer.write("doGet\n")
    }

    override fun destroy() {
        log("LOG_destroy")
    }
}