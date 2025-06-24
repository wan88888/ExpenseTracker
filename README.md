# 费用追踪器 (Expense Tracker)

一个使用 Kotlin 和 Jetpack Compose 构建的简单费用追踪 Android 应用。

## 功能特性

### 🏠 主要功能
- **余额显示**：实时显示总余额、总收入和总支出
- **添加交易**：支持添加收入和支出记录
- **交易列表**：按时间顺序显示所有交易记录
- **删除交易**：可以删除不需要的交易记录

### 📱 用户界面
- **现代化设计**：使用 Material Design 3 风格
- **中文界面**：完全中文化的用户界面
- **直观操作**：简单易用的交互设计
- **响应式布局**：适配不同尺寸的设备

## 技术栈

- **开发语言**：Kotlin
- **UI 框架**：Jetpack Compose
- **架构模式**：MVVM (Model-View-ViewModel)
- **状态管理**：Compose State
- **构建工具**：Gradle

## 应用结构

```
app/src/main/java/com/example/expensetracker/
├── model/
│   └── Transaction.kt          # 交易数据模型
├── viewmodel/
│   └── ExpenseViewModel.kt     # 业务逻辑处理
├── ui/
│   └── components/
│       ├── BalanceCard.kt      # 余额卡片组件
│       ├── TransactionItem.kt  # 交易项组件
│       └── AddTransactionDialog.kt # 添加交易对话框
├── data/
│   └── SampleData.kt          # 示例数据
└── MainActivity.kt            # 主活动
```

## 如何使用

### 📊 查看余额
- 应用启动后，主屏幕顶部显示余额卡片
- 显示总余额、总收入和总支出
- 余额为正数时显示绿色，负数时显示红色

### ➕ 添加交易
1. 点击右下角的 "+" 按钮
2. 选择交易类型（收入或支出）
3. 输入金额和描述
4. 点击"添加"按钮保存

### 📝 查看交易列表
- 所有交易按时间倒序排列
- 每条记录显示描述、金额、时间和类型
- 收入显示为绿色正数，支出显示为红色负数

### 🗑️ 删除交易
- 点击交易记录右侧的删除按钮
- 删除后余额会自动更新

## 构建和运行

### 前置要求
- Android Studio Arctic Fox 或更高版本
- Android SDK API Level 28 或更高
- Kotlin 1.9.0 或更高版本

### 构建步骤
1. 克隆或下载项目
2. 在 Android Studio 中打开项目
3. 等待 Gradle 同步完成
4. 连接 Android 设备或启动模拟器
5. 点击运行按钮或使用 `./gradlew assembleDebug`

## 示例数据

应用包含以下预设的示例数据：
- 工资收入：¥5,000
- 兼职收入：¥1,000
- 超市购物：¥200
- 电费：¥300
- 咖啡：¥50

## 未来改进

- [ ] 数据持久化存储
- [ ] 交易分类管理
- [ ] 统计图表显示
- [ ] 导出功能
- [ ] 备份和恢复
- [ ] 多币种支持

## 许可证

此项目仅供学习和演示使用。 