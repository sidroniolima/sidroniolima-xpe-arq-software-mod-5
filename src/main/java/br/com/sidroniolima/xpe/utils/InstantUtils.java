package br.com.sidroniolima.xpe.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public interface InstantUtils {

    static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }
}
