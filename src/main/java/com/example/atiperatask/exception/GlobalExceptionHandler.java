package com.example.atiperatask.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        String jsonString = extractJsonString(ex.getMessage());
        String message = extractMessageFromJson(jsonString);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                message
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private static String extractJsonString(String response) {
        Pattern pattern = Pattern.compile("\\{.*\\}");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static String extractMessageFromJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            return rootNode.path("message").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
