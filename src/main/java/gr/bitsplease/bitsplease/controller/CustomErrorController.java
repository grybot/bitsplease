package gr.bitsplease.bitsplease.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController
        implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ErrorDetails {
        private Integer errorCode;
        private String errorMessage;
    }

    @RequestMapping("error")
    public ErrorDetails handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return new ErrorDetails(statusCode, exception == null ? "N/A" : exception.getMessage());
    }
}