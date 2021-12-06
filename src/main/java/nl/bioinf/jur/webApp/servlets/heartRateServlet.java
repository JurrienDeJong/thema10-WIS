package nl.bioinf.jur.webApp.servlets; //change to your situation!
import nl.bioinf.jur.webApp.config.WebConfig; //change to your situation!
import nl.bioinf.jur.webApp.model.HeartRateZone;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "heartRateServlet", urlPatterns = "/heart-rate-zones", loadOnStartup = 1)
public class heartRateServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    @Override
    public void init() throws ServletException {
        final ServletContext servletContext = this.getServletContext();
        WebConfig.createTemplateEngine(servletContext);
    }
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebConfig.configureResponse(response);
        String max_heart_rate = request.getParameter("max_heart_rate");
        Locale locale = request.getLocale();
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                locale);
        ctx.setVariable("max_heart_rate", max_heart_rate);
        WebConfig.createTemplateEngine(getServletContext()).
                process("max_heart_rate", ctx, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebConfig.configureResponse(response);
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        int max_heart_rate = Integer.parseInt(request.getParameter("max_heart_rate"));
        List<HeartRateZone> zones = new ArrayList<>();
        if(max_heart_rate > 0 && max_heart_rate < 300) {
            zones.add(new HeartRateZone(max_heart_rate * 0.5, max_heart_rate * 0.6));
            zones.add(new HeartRateZone(max_heart_rate * 0.6, max_heart_rate * 0.7));
            zones.add(new HeartRateZone(max_heart_rate * 0.7, max_heart_rate * 0.8));
            zones.add(new HeartRateZone(max_heart_rate * 0.8, max_heart_rate * 0.9));
            zones.add(new HeartRateZone(max_heart_rate * 0.9, max_heart_rate));
            ctx.setVariable("zones", zones);
            WebConfig.createTemplateEngine(getServletContext()).
                    process("max_heart_rate_out", ctx, response.getWriter());
        }
        else
        {
            WebConfig.createTemplateEngine(getServletContext()).
                    process("max_heart_rate_fail", ctx, response.getWriter());
        }
    }
}