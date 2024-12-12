package com.nttdata.customer.infrastructure.util;

import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebExchangeBindException;

@UtilityClass
@SuppressWarnings("java:S1118")
public final class ErrorUtils {

    @NonNull
    public static String buildErrorCode(final int status) {
        return String.format("SP-%d", status);
    }

    @NonNull
    public static String buildMessageFromWebExchangeBindException(
            @NonNull final WebExchangeBindException webExchangeBindException
    ) {
        return webExchangeBindException.getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(", "));
    }
}
