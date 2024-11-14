package com.example.labexamen.domain.model

import com.example.labexamen.domain.network.util.Error

enum class DataError: Error {
    NO_INTERNET,
    GENERIC_ERROR
}