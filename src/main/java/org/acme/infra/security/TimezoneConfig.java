package org.acme.infra.security;


import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

import java.util.TimeZone;

@Singleton
public class TimezoneConfig {
    public void setTimezone(@Observes StartupEvent startupEvent) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Fortaleza"));
    }
}

