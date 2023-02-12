package com.hsrt.hsrtllapi.utils.response;

import lombok.Getter;

@Getter
public enum Response {
    SUCCESS(200,"success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer code;
    private final String message;
    Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
