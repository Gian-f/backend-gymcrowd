package org.acme.domain.exception

import io.quarkus.security.ForbiddenException

class InvalidPasswordException: ForbiddenException("Senha inválida")