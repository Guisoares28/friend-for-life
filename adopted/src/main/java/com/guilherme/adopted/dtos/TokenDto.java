package com.guilherme.adopted.dtos;

import java.time.LocalDateTime;

public record TokenDto(
    String content,
    LocalDateTime generatedDate
) {

}
