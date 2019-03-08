package recruitment.presentation.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles all exceptions thrown and HTTP errors.
 */
@Controller
@ControllerAdvice
public class ExceptionHandlers implements ErrorController {
    public static final String ERROR_PAGE_URL = "error";
    public static final String ERROR_TYPE_KEY = "errorType";
    public static final String ERROR_INFO_KEY = "errorInfo";
    public static final String GENERIC_ERROR = "Operation Failed";
    public static final String GENERIC_ERROR_INFO = "Sorry, it didn't work. Please try again.";
    public static final String FORBIDDEN_ERROR = "Forbidden";
    public static final String FORBIDDEN_ERROR_INFO = "You have no permission to see this page.";
    public static final String HTTP_404 = "Oops! 404";
    public static final String HTTP_404_INFO = "We can't seem to find the page you're looking for.";
    static final String ERROR_PATH = "failure";

    /**
     * Handle exceptions thrown by the application
     * @param exception that have been thrown
     * @param model object used by error page
     * @return URL error page
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        model.addAttribute(ERROR_TYPE_KEY, GENERIC_ERROR);
        exception.printStackTrace();
        return ERROR_PAGE_URL;
    }

    /**
     * Handles HTTP errors
     * @param request the actual HTTP request
     * @param response to the client
     * @param model object used by error page
     * @return URL to error page
     */
    @GetMapping("/" + ERROR_PATH)
    public String handleHttpError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int statusCode = Integer.parseInt(extractHttpStatusCode(request));
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute(ERROR_TYPE_KEY, HTTP_404);
            model.addAttribute(ERROR_INFO_KEY, HTTP_404_INFO);
            response.setStatus(statusCode);
        } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
            model.addAttribute(ERROR_TYPE_KEY, FORBIDDEN_ERROR);
            model.addAttribute(ERROR_INFO_KEY, FORBIDDEN_ERROR_INFO);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        else {
            model.addAttribute(ERROR_TYPE_KEY, GENERIC_ERROR);
            System.out.println("login error " + statusCode);

            model.addAttribute(ERROR_INFO_KEY, GENERIC_ERROR_INFO);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ERROR_PAGE_URL;
    }

    private String extractHttpStatusCode(HttpServletRequest request) {
        return request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
    }

    @Override
    public String getErrorPath() {
        return "/" + ERROR_PATH;
    }
}
