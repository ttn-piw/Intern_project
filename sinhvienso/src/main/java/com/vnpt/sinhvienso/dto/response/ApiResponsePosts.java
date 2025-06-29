package com.vnpt.sinhvienso.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponsePosts<T> {
    @Builder.Default
    int code = 200;

    List<T> data;
}
