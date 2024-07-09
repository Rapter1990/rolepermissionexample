package com.security.rolepermissionexample.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.security.rolepermissionexample.common.model.CustomError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;

/**
 * Custom implementation as {@link CustomAuthenticationEntryPoint} of {@link AuthenticationEntryPoint} used to handle authentication failures.
 * Returns an HTTP 401 Unauthorized response with a JSON error message.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    /**
     * Invoked when authentication fails. Sends an HTTP 401 Unauthorized response with a JSON error message
     * describing the authentication error.
     *
     * @param httpServletRequest       The request that resulted in an authentication failure.
     * @param httpServletResponse      The response to send to the client.
     * @param authenticationException  The authentication exception that occurred.
     * @throws IOException             If an input or output exception occurs during the response writing.
     */
    @Override
    public void commence(final HttpServletRequest httpServletRequest,
                         final HttpServletResponse httpServletResponse,
                         final AuthenticationException authenticationException) throws IOException {

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        final CustomError customError = CustomError.builder()
                .header(CustomError.Header.AUTH_ERROR.getName())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .isSuccess(false)
                .build();

        final String responseBody = OBJECT_MAPPER
                .writer(DateFormat.getDateInstance())
                .writeValueAsString(customError);

        httpServletResponse.getOutputStream()
                .write(responseBody.getBytes());

    }

}
