package com.example.expensetracker.data

import com.example.expensetracker.model.Transaction
import com.example.expensetracker.model.TransactionType
import java.util.*

object SampleData {
    fun getSampleTransactions(): List<Transaction> {
        val calendar = Calendar.getInstance()
        
        return listOf(
            Transaction(
                amount = 5000.0,
                description = "工资",
                type = TransactionType.INCOME,
                timestamp = Date(calendar.timeInMillis - 86400000L) // 1天前
            ),
            Transaction(
                amount = 200.0,
                description = "超市购物",
                type = TransactionType.EXPENSE,
                timestamp = Date(calendar.timeInMillis - 3600000L) // 1小时前
            ),
            Transaction(
                amount = 50.0,
                description = "咖啡",
                type = TransactionType.EXPENSE,
                timestamp = Date(calendar.timeInMillis - 1800000L) // 30分钟前
            ),
            Transaction(
                amount = 1000.0,
                description = "兼职收入",
                type = TransactionType.INCOME,
                timestamp = Date(calendar.timeInMillis - 7200000L) // 2小时前
            ),
            Transaction(
                amount = 300.0,
                description = "电费",
                type = TransactionType.EXPENSE,
                timestamp = Date(calendar.timeInMillis - 172800000L) // 2天前
            )
        )
    }
} 