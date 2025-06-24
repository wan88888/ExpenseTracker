package com.example.expensetracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.expensetracker.model.TransactionType
import com.example.expensetracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onAddTransaction: (Double, String, TransactionType) -> Unit
) {
    if (isVisible) {
        var amount by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var selectedType by remember { mutableStateOf(TransactionType.EXPENSE) }
        var amountError by remember { mutableStateOf(false) }
        var descriptionError by remember { mutableStateOf(false) }

        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(24.dp),
                        ambientColor = Color.Black.copy(alpha = 0.1f)
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackground)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // 标题区域
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = PrimaryBlue,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = "添加交易",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }

                    // 交易类型选择区域
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "交易类型",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // 收入选项
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = selectedType == TransactionType.INCOME,
                                        onClick = { selectedType = TransactionType.INCOME }
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedType == TransactionType.INCOME) 
                                        IncomeGreenLight.copy(alpha = 0.3f) else Color.Transparent
                                ),
                                border = if (selectedType == TransactionType.INCOME) 
                                    null else CardDefaults.outlinedCardBorder(),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        tint = if (selectedType == TransactionType.INCOME) 
                                            IncomeGreen else TextSecondary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "收入",
                                        fontWeight = FontWeight.Medium,
                                        color = if (selectedType == TransactionType.INCOME) 
                                            IncomeGreen else TextSecondary
                                    )
                                }
                            }
                            
                            // 支出选项
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = selectedType == TransactionType.EXPENSE,
                                        onClick = { selectedType = TransactionType.EXPENSE }
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedType == TransactionType.EXPENSE) 
                                        ExpenseRedLight.copy(alpha = 0.3f) else Color.Transparent
                                ),
                                border = if (selectedType == TransactionType.EXPENSE) 
                                    null else CardDefaults.outlinedCardBorder(),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = if (selectedType == TransactionType.EXPENSE) 
                                            ExpenseRed else TextSecondary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "支出",
                                        fontWeight = FontWeight.Medium,
                                        color = if (selectedType == TransactionType.EXPENSE) 
                                            ExpenseRed else TextSecondary
                                    )
                                }
                            }
                        }
                    }

                    // 金额输入
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { 
                            amount = it
                            amountError = false
                        },
                        label = { Text("金额") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = PrimaryBlue
                            )
                        },
                        prefix = { Text("¥", color = TextSecondary) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = amountError,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue,
                            unfocusedBorderColor = TextHint
                        )
                    )

                    // 描述输入
                    OutlinedTextField(
                        value = description,
                        onValueChange = { 
                            description = it
                            descriptionError = false
                        },
                        label = { Text("描述") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = PrimaryBlue
                            )
                        },
                        isError = descriptionError,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue,
                            unfocusedBorderColor = TextHint
                        )
                    )

                    // 按钮区域
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = TextSecondary
                            )
                        ) {
                            Text(
                                text = "取消",
                                fontWeight = FontWeight.Medium
                            )
                        }
                        
                        Button(
                            onClick = {
                                val amountValue = amount.toDoubleOrNull()
                                
                                amountError = amountValue == null || amountValue <= 0
                                descriptionError = description.isBlank()
                                
                                if (!amountError && !descriptionError) {
                                    onAddTransaction(amountValue!!, description.trim(), selectedType)
                                    onDismiss()
                                }
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryBlue
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "添加",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
} 