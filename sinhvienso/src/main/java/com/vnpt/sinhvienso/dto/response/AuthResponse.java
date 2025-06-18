package com.vnpt.sinhvienso.dto.response;

import com.mongodb.lang.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    int code;
    String message;
    Boolean authenticated;

    @Nullable
    String token;

    @Nullable
    String token_type;

    long expires_in;
}
