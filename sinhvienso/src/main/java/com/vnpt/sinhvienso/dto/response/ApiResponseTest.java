package com.vnpt.sinhvienso.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponseTest<T> {

    @Builder.Default
    private int code = 1000;

    String message;

    T result;
}
