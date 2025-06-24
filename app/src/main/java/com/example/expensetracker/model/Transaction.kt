package com.example.expensetracker.model

import java.util.Date
import java.util.UUID

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val description: String,
    val type: TransactionType,
    val timestamp: Date = Date()
)

enum class TransactionType {
    INCOME,
    EXPENSE
} 