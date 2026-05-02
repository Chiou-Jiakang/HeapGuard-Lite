# HeapGuard Lite 範例輸出

## 一、主選單畫面

```text
====================================
 HeapGuard Lite
====================================
1. Load sample events
2. Add custom event
3. Analyze events
4. Show Top-K alerts
5. Show full ranking
6. Export report
7. Show complexity summary
0. Exit
Choose an option:
```

## 二、載入範例事件

```text
Choose an option: 1
Loaded 10 events into the event queue.
```

## 三、分析事件

```text
Choose an option: 3
Analyzed 10 events.
```

## 四、Top-K 高風險事件

```text
Choose an option: 4

Top 5 High-Risk Alerts:
1. [CRITICAL] SQL_INJECTION | User: guest | Risk: 100 | Priority: 0
2. [CRITICAL] PHISHING_SMS | User: bob | Risk: 100 | Priority: 0
3. [CRITICAL] XSS_ATTEMPT | User: guest | Risk: 100 | Priority: 0
4. [CRITICAL] SUSPICIOUS_FILE | User: alice | Risk: 90 | Priority: 10
5. [CRITICAL] WEAK_PASSWORD | User: tim | Risk: 90 | Priority: 10
```

## 五、完整風險排序

```text
Choose an option: 5

Full Risk Ranking:
1. [CRITICAL] SQL_INJECTION | User: guest | Risk: 100 | Priority: 0
2. [CRITICAL] XSS_ATTEMPT | User: guest | Risk: 100 | Priority: 0
3. [CRITICAL] PHISHING_SMS | User: bob | Risk: 100 | Priority: 0
4. [CRITICAL] WEAK_PASSWORD | User: tim | Risk: 90 | Priority: 10
5. [CRITICAL] SUSPICIOUS_FILE | User: tim | Risk: 90 | Priority: 10
6. [CRITICAL] SUSPICIOUS_FILE | User: alice | Risk: 90 | Priority: 10
7. [CRITICAL] PHISHING_SMS | User: tim | Risk: 80 | Priority: 20
8. [CRITICAL] LOGIN_FAIL | User: root | Risk: 80 | Priority: 20
9. [HIGH] LOGIN_FAIL | User: admin | Risk: 60 | Priority: 40
10. [LOW] NORMAL_LOGIN | User: mary | Risk: 10 | Priority: 90
```

## 六、新增自訂事件

```text
Choose an option: 2

Select event type:
1. PHISHING_SMS
2. WEAK_PASSWORD
3. SUSPICIOUS_FILE
4. LOGIN_FAIL
5. SQL_INJECTION
6. XSS_ATTEMPT
7. NORMAL_LOGIN
0. Cancel
Choose event type: 4
Enter user: admin
Enter IP: 192.168.1.99
Enter message: failed login attempt with password=123456
Custom event added successfully.
[UNKNOWN] LOGIN_FAIL | User: admin | Risk: 0 | Priority: 100
Please run Analyze events before viewing rankings or exporting reports.
```

## 七、報告輸出

```text
Choose an option: 6
Exporting report...
Report exported to output/sample_report.txt
```

## 八、防呆提示範例

### 尚未載入事件就查看 Top-K

```text
Choose an option: 4
No events loaded. Please load sample events first.
```

### 尚未分析事件就查看排序

```text
Choose an option: 5
Events have not been analyzed yet. Please analyze events first.
```

### 輸入非數字

```text
Choose an option: abc
Invalid input. Please enter a number.
Choose an option:
```

### 輸入超出範圍的選項

```text
Choose an option: 9
Invalid option. Please choose 0-7.
```

## 九、離開程式

```text
Choose an option: 0
Exiting HeapGuard Lite.
```
