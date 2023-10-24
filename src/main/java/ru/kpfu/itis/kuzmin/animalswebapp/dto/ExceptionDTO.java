package ru.kpfu.itis.kuzmin.animalswebapp.dto;

public class ExceptionDTO {
    private String uri;
    private Integer statusCode;
    private String message;

    public ExceptionDTO(String uri, Integer statusCode, String message) {
        this.uri = uri;
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
