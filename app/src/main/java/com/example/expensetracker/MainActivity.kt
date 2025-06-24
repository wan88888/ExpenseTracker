package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensetracker.model.TransactionType
import com.example.expensetracker.ui.components.AddTransactionDialog
import com.example.expensetracker.ui.components.BalanceCard
import com.example.expensetracker.ui.components.TransactionItem
import com.example.expensetracker.ui.theme.*
import com.example.expensetracker.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerTheme {
                ExpenseTrackerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseTrackerApp(
    viewModel: ExpenseViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = PrimaryBlue,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "费用追踪器",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceLight,
                    titleContentColor = TextPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                modifier = Modifier.shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "添加交易",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 余额卡片
            BalanceCard(
                balance = viewModel.balance,
                totalIncome = viewModel.totalIncome,
                totalExpense = viewModel.totalExpense
            )

            // 交易列表区域
            if (viewModel.transactions.isNotEmpty()) {
                // 交易列表标题
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "交易记录",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${viewModel.transactions.size} 笔",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }

                // 交易列表
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewModel.transactions.sortedByDescending { it.timestamp }) { transaction ->
                        TransactionItem(
                            transaction = transaction,
                            onDelete = { transactionId ->
                                viewModel.deleteTransaction(transactionId)
                            }
                        )
                    }
                    // 底部间距，避免被FAB遮挡
                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            } else {
                // 空状态
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = TextHint,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "暂无交易记录",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            color = TextSecondary
                        )
                        Text(
                            text = "点击右下角的 + 按钮\n添加您的第一笔交易",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextHint,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // 添加交易对话框
        AddTransactionDialog(
            isVisible = showDialog,
            onDismiss = { showDialog = false },
            onAddTransaction = { amount, description, type ->
                viewModel.addTransaction(amount, description, type)
                showDialog = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseTrackerAppPreview() {
    ExpenseTrackerTheme {
        ExpenseTrackerApp()
    }
}