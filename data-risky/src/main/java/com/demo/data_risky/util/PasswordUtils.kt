package com.demo.data_risky.util

import java.util.regex.Pattern

class PasswordUtils {
    fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN =
            Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*()_+])(?!.*\\s).{8,100}\$")
        return PASSWORD_PATTERN.matcher(password).matches() &&
                password.isNotEmpty()
    }
}