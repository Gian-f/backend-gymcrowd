package org.acme.utils

object ValidationMessages {
    const val REQUEST_BODY_MUST_NOT_BE_NULL = "o corpo da requisição não deve ser nulo"
    const val USERNAME_MUST_BE_NOT_BLANK = "o nome de usuário não deve estar em branco"
    const val EMAIL_MUST_BE_NOT_BLANK = "o email não deve estar em branco"
    const val PASSWORD_MUST_BE_NOT_BLANK = "a senha não deve estar em branco"
    const val CPF_MUST_NOT_BE_BLANK = "O campo CPF não pode ser nulo ou vazio"
    const val CPF_ALREADY_EXISTS = "O campo CPF já existe!"
    const val INVALID_CREDENTIALS = "Credenciais estão inválidas!"
}