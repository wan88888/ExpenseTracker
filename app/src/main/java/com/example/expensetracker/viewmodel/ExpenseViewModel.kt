package com.example.expensetracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.SampleData
import com.example.expensetracker.model.Transaction
import com.example.expensetracker.model.TransactionType

class ExpenseViewModel : ViewModel() {
    private var _transactions = mutableStateOf<List<Transaction>>(SampleData.getSampleTransactions())
    val transactions: List<Transaction> by _transactions

    var balance: Double by mutableStateOf(0.0)
        private set

    var totalIncome: Double by mutableStateOf(0.0)
        private set

    var totalExpense: Double by mutableStateOf(0.0)
        private set

    init {
        updateBalances()
    }

    fun addTransaction(amount: Double, description: String, type: TransactionType) {
        val newTransaction = Transaction(
            amount = amount,
            description = description,
            type = type
        )

        _transactions.value = _transactions.value + newTransaction
        updateBalances()
    }

    private fun updateBalances() {
        totalIncome = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        totalExpense = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        balance = totalIncome - totalExpense
    }

    fun deleteTransaction(transactionId: String) {
        _transactions.value = _transactions.value.filter { it.id != transactionId }
        updateBalances()
    }
} 