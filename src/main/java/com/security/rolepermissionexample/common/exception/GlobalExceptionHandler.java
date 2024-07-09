package com.security.rolepermissionexample.common.exception;

import com.security.rolepermissionexample.auth.exception.*;
import com.security.rolepermissionexample.common.model.CustomError;
import com.security.rolepermissionexample.product.exception.ProductAlreadyExistException;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Global exception handler named {@link GlobalExceptionHandler} for handling various types of exceptions in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException thrown when validation on an argument annotated with @Valid fails.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return ResponseEntity with CustomError containing details of validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {

        List<CustomError.CustomSubError> subErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    subErrors.add(
                            CustomError.CustomSubError.builder()
                                    .field(fieldName)
                                    .message(message)
                                    .build()
                    );
                }
        );

        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message("Validation failed")
                .subErrors(subErrors)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handles ConstraintViolationException thrown when a bean validation constraint is violated.
     *
     * @param constraintViolationException The ConstraintViolationException instance.
     * @return ResponseEntity with CustomError containing details of constraint violations.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handlePathVariableErrors(final ConstraintViolationException constraintViolationException) {

        List<CustomError.CustomSubError> subErrors = new ArrayList<>();
        constraintViolationException.getConstraintViolations()
                .forEach(constraintViolation ->
                        subErrors.add(
                                CustomError.CustomSubError.builder()
                                        .message(constraintViolation.getMessage())
                                        .field(StringUtils.substringAfterLast(constraintViolation.getPropertyPath().toString(), "."))
                                        .value(constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString() : null)
                                        .type(constraintViolation.getInvalidValue().getClass().getSimpleName())
                                        .build()
                        )
                );

        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message("Constraint violation")
                .subErrors(subErrors)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handles RuntimeException thrown for general runtime exceptions.
     *
     * @param runtimeException The RuntimeException instance.
     * @return ResponseEntity with CustomError containing details of the runtime exception.
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(final RuntimeException runtimeException) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.API_ERROR.getName())
                .message(runtimeException.getMessage())
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AccessDeniedException thrown when access to a resource is denied due to insufficient permissions.
     *
     * @param accessDeniedException The AccessDeniedException instance.
     * @return ResponseEntity with CustomError indicating access denial.
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDeniedException(final AccessDeniedException accessDeniedException) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .header(CustomError.Header.AUTH_ERROR.getName())
                .message(accessDeniedException.getMessage())
                .build();

        return new ResponseEntity<>(customError, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles PasswordNotValidException thrown when a password does not meet validation criteria.
     *
     * @param ex The PasswordNotValidException instance.
     * @return ResponseEntity with CustomError indicating password validation failure.
     */
    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<CustomError> handlePasswordNotValidException(final PasswordNotValidException ex) {

        CustomError error = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles PermissionNotFoundException thrown when a requested permission is not found.
     *
     * @param ex The PermissionNotFoundException instance.
     * @return ResponseEntity with CustomError indicating permission not found.
     */
    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity<CustomError> handlePermissionNotFoundException(final PermissionNotFoundException ex) {

        CustomError error = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles RoleNotFoundException thrown when a requested role is not found.
     *
     * @param ex The RoleNotFoundException instance.
     * @return ResponseEntity with CustomError indicating role not found.
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<CustomError> handleRoleNotFoundException(final RoleNotFoundException ex) {

        CustomError error = CustomError.builder()
                .time(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles TokenAlreadyInvalidatedException thrown when an attempt is made to use an already invalidated token.
     *
     * @param ex The TokenAlreadyInvalidatedException instance.
     * @return ResponseEntity with CustomError indicating token already invalidated.
     */
    @ExceptionHandler(TokenAlreadyInvalidatedException.class)
    public ResponseEntity<CustomError> handleTokenAlreadyInvalidatedException(final TokenAlreadyInvalidatedException ex) {
        CustomError error = CustomError.builder()
                .time(LocalDateTime.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles UserAlreadyExistException thrown when an attempt is made to register a user with an existing email.
     *
     * @param ex The UserAlreadyExistException instance.
     * @return ResponseEntity with CustomError indicating user already exists.
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<CustomError> handleUserAlreadyExistException(final UserAlreadyExistException ex) {
        CustomError error = CustomError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .header(CustomError.Header.ALREADY_EXIST.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles UserNotFoundException thrown when a requested user is not found.
     *
     * @param ex The UserNotFoundException instance.
     * @return ResponseEntity with CustomError indicating user not found.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomError> handleUserNotFoundException(final UserNotFoundException ex) {
        CustomError error = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserStatusNotValidException thrown when a user status is not valid for an operation.
     *
     * @param ex The UserStatusNotValidException instance.
     * @return ResponseEntity with CustomError indicating invalid user status.
     */
    @ExceptionHandler(UserStatusNotValidException.class)
    public ResponseEntity<CustomError> handleUserStatusNotValidException(final UserStatusNotValidException ex) {
        CustomError error = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ProductAlreadyExistException thrown when an attempt is made to create a product that already exists.
     *
     * @param ex The ProductAlreadyExistException instance.
     * @return ResponseEntity with CustomError indicating product already exists.
     */
    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<CustomError> handleProductAlreadyExistException(final ProductAlreadyExistException ex) {
        CustomError error = CustomError.builder()
                .time(LocalDateTime.now())
                .httpStatus(HttpStatus.CONFLICT)
                .header(CustomError.Header.ALREADY_EXIST.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles ProductNotFoundException thrown when a requested product is not found.
     *
     * @param ex The ProductNotFoundException instance.
     * @return ResponseEntity with CustomError indicating product not found.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomError> handleProductNotFoundException(final ProductNotFoundException ex) {
        CustomError error = CustomError.builder()
                .time(LocalDateTime.now())
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(ex.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
