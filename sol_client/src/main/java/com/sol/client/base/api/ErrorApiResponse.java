package com.sol.client.base.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public class ErrorApiResponse<Error> {
    //Массив ошибок
    private List<Error> errors;

    //Статус
    private int status;

    //Путь запроса, вызвавшего исключение
    private String path;

    //Время ошибки
    private LocalDateTime timestamp = LocalDateTime.now();

    //Идентификатор для отслеживания (полезно для поиска в логих)
    private String traceId = UUID.randomUUID().toString();

    public ErrorApiResponse(List<Error> errors, int status) {
        this.errors = errors;
        this.status = status;
    }

    public ErrorApiResponse(List<Error> errors, int status, String path) {
        this.errors = errors;
        this.status = status;
        this.path = path;
    }

    public static <Error> ErrorApiResponse<Error> of(List<Error> errors, int status, String path) {
        return new ErrorApiResponse<>(errors, status, path);
    }

    public static <Error> ErrorApiResponse<Error> badRequest(List<Error> errors, String path) {
        return new ErrorApiResponse<>(errors, 400, path);
    }

    public static <Error> ErrorApiResponse<Error> auth(List<Error> errors, String path) {
        return new ErrorApiResponse<>(errors, 401, path);
    }

    public static <Error> ErrorApiResponse<Error> notFound(List<Error> errors, String path) {
        return new ErrorApiResponse<>(errors, 404, path);
    }

    public static <Error> ErrorApiResponse<Error> internalServerError(List<Error> errors, String path) {
        return new ErrorApiResponse<>(errors, 500, path);
    }

}
