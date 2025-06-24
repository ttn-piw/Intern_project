package com.vnpt.sinhvienso.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponseStudent<T> {

    @Builder.Default
    private int code = 1000;

    List<T> data;
}
