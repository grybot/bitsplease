package gr.bitsplease.bitsplease.controller;


import gr.bitsplease.bitsplease.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
/**
 * The type Custom error controller.
 */
@RestController
public class CustomErrorController
        implements ErrorController {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    /**
     * Handle error string.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");


        if (exception instanceof ApplicantException){return exception.getMessage();}
        if (exception instanceof JobOfferException){return exception.getMessage();}
        if (exception instanceof MatchException){return exception.getMessage();}
        if (exception instanceof SkillException){return exception.getMessage();}

        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * Index string.
     *
     * @return the string
     */


}