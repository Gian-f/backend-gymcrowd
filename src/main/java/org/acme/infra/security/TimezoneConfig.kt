package org.acme.infra.security

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.event.Observes
import jakarta.inject.Singleton
import java.util.*


@Singleton
class TimezoneConfig {
    fun setTimezone(@Observes startupEvent: StartupEvent?) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Fortaleza"))
    }
}