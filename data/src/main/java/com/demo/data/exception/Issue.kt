package com.demo.data.exception

enum class EmailIssue {
    EMPTY,
    INVALID,
    VALID
}

enum class PasswordIssue {
    EMPTY,
    INVALID,
    VALID,
    NOT_MATCH
}