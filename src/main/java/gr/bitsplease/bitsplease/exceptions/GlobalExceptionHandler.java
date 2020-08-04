package gr.bitsplease.bitsplease.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    /** Provides handling for exceptions throughout this service. */
    @ExceptionHandler({ ApplicantNotFoundException.class, JobOfferNotFoundException.class, MatchNotFoundException.class, SkillNotFoundException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof ApplicantNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApplicantNotFoundException anfe = (ApplicantNotFoundException) ex;

            return handleApplicantNotFoundException(anfe, headers, status, request);

        }if (ex instanceof JobOfferNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            JobOfferNotFoundException jonfe = (JobOfferNotFoundException) ex;

            return handleJobOfferNotFoundException(jonfe, headers, status, request);

        }if (ex instanceof MatchNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            MatchNotFoundException mnfe = (MatchNotFoundException) ex;

            return handleMatchNotFoundException(mnfe, headers, status, request);

        }if (ex instanceof SkillNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            SkillNotFoundException snfe = (SkillNotFoundException) ex;

            return handleSkillNotFoundException(snfe, headers, status, request);

        }else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /** Customize the response for ApplicantNotFoundException. */
    protected ResponseEntity<ApiError> handleApplicantNotFoundException(ApplicantNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(status, errors), headers, HttpStatus.BAD_REQUEST, request);
    }
    protected ResponseEntity<ApiError> handleJobOfferNotFoundException(JobOfferNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(status, errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleMatchNotFoundException(MatchNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(status, errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleSkillNotFoundException(SkillNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(status, errors), headers, status, request);
    }
    /** A single place to customize the response body of all Exception types. */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
