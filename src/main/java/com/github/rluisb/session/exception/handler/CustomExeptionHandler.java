package com.github.rluisb.session.exception.handler;

import com.github.rluisb.session.exception.ErrorResponse;
import com.github.rluisb.session.exception.type.AgendaAlreadyBeenVotedException;
import com.github.rluisb.session.exception.type.AssociatedAlreadyVotedException;
import com.github.rluisb.session.exception.type.SessionHasEndedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExeptionHandler {

    private static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "No records found for this request.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotNullAndNotBlankPropertyException(final MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        if (hasMoreThanOneError(result)) {
            return new ErrorResponse(processFieldErrors(result.getFieldErrors()));
        }

        return new ErrorResponse(resolveLocalizedErrorMessage(result.getFieldError()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNoSuchElementExceptionException(final NoSuchElementException ex) {
        return new ErrorResponse(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(AssociatedAlreadyVotedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse handleAssociatedAlreadyVotedException(final AssociatedAlreadyVotedException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(SessionHasEndedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse handleSessionHasEndedException(final SessionHasEndedException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(AgendaAlreadyBeenVotedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse handleAgendaAlreadyBeenVotedException(final AgendaAlreadyBeenVotedException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    private boolean hasMoreThanOneError(BindingResult bindingResult) {
        return bindingResult.getErrorCount() > 1;
    }

    private List<String> processFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(this::resolveLocalizedErrorMessage)
                .collect(Collectors.toList());
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }
}
