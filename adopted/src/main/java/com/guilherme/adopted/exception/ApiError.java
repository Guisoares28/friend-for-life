package com.guilherme.adopted.exception;

import java.util.List;

public record ApiError(
    Integer status,
    String message,
    String path,
    List<ErrorField> errorsField
) {

}
