# HeapGuard Lite 測試指南

## 一、測試前先做什麼

- 請先確認目前終端機的位置是在專案根目錄，也就是 `HeapGuard-Lite`。
- 如果終端機路徑最後不是 `HeapGuard-Lite`，請先切換到專案資料夾。
- 本專案是 Java console 程式，所以測試時主要會在終端機輸入選單數字。

## 二、編譯專案

- 每次修改 `.java` 檔案後，建議先重新編譯一次。

```bash
javac src/*.java
```

- 如果沒有出現錯誤訊息，代表編譯成功。
- 如果出現錯誤，通常要先看是哪一個 `.java` 檔案、哪一行出問題，不要直接硬跑，電腦不會通靈。

## 三、執行主程式

- 編譯成功後，執行主程式：

```bash
java -cp src Main
```

- 正常情況下，終端機會顯示主選單：

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

---

## 四、主流程測試：使用 sample data

這組是最基本、最重要的測試。  
如果這組過了，代表專案主線基本上是正常的。

請依序輸入：

```text
1
3
4
5
6
0
```

### 預期結果

- 輸入 `1`
  - 系統會讀取 `data/sample_events.txt`。
  - 預期看到：

```text
Loaded 10 events into the event queue.
```

- 輸入 `3`
  - 系統會分析所有已載入事件。
  - 預期看到：

```text
Analyzed 10 events.
```

- 輸入 `4`
  - 系統會顯示前 5 筆高風險事件。
  - 通常會看到 `SQL_INJECTION`、`PHISHING_SMS`、`XSS_ATTEMPT` 等高風險事件排在前面。

- 輸入 `5`
  - 系統會顯示完整風險排序。
  - 排序應該大致依照 `Risk` 分數由高到低排列。

- 輸入 `6`
  - 系統會輸出報告。
  - 預期看到：

```text
Exporting report...
Report exported to output/sample_report.txt
```

- 輸入 `0`
  - 系統正常離開。
  - 預期看到：

```text
Exiting HeapGuard Lite.
```

---

## 五、自訂事件測試：手動新增一筆事件

這組測試是確認 `Add custom event` 真的有接上，不是放在選單裡當裝飾品。

請執行主程式後依序輸入：

```text
2
4
admin
192.168.1.99
failed login attempt with password=123456
3
5
0
```

### 這組輸入代表什麼

- `2`
  - 選擇新增自訂事件。

- `4`
  - 選擇事件類型 `LOGIN_FAIL`。

- `admin`
  - 使用者名稱。

- `192.168.1.99`
  - IP 位址。

- `failed login attempt with password=123456`
  - 事件訊息。

- `3`
  - 分析事件。

- `5`
  - 顯示完整風險排序。

- `0`
  - 離開程式。

### 預期結果

新增的事件應該會被分析成高風險事件：

```text
[CRITICAL] LOGIN_FAIL | User: admin | Risk: 80 | Priority: 20
```

原因是：

```text
LOGIN_FAIL: +40
admin: +20
password keyword: +20
Total: 80
```

---

## 六、混合測試：先載入 sample，再新增事件

這組是比較完整的整合測試。  
它會測到 sample data、自訂事件、分析、Top-K、完整排序與報告輸出。

請依序輸入：

```text
1
2
5
guest
10.0.0.99
' OR 1=1 --
3
4
5
6
0
```

### 預期結果

- 一開始會載入 10 筆 sample events。
- 接著新增 1 筆自訂 SQL injection 事件。
- 分析後應該看到：

```text
Analyzed 11 events.
```

- Top-K 和完整排序裡面應該會包含你新增的 SQL injection 事件。
- 報告輸出後，`output/sample_report.txt` 也應該包含這筆事件。

---

## 七、防呆測試

這些測試不是主功能，但很重要。  
因為使用者不會永遠乖乖照順序操作，尤其人類這個物種，懂的都懂。

### 測試 1：還沒載入資料就看 Top-K

請輸入：

```text
4
0
```

預期結果：

```text
No events loaded. Please load sample events first.
```

---

### 測試 2：載入資料但還沒分析就看 Top-K

請輸入：

```text
1
4
0
```

預期結果：

```text
Events have not been analyzed yet. Please analyze events first.
```

---

### 測試 3：還沒載入資料就輸出報告

請輸入：

```text
6
0
```

預期結果：

```text
No events loaded. Please load sample events first.
```

---

### 測試 4：取消新增自訂事件

請輸入：

```text
2
0
0
```

預期結果：

```text
Custom event creation cancelled.
Exiting HeapGuard Lite.
```

---

### 測試 5：輸入非數字

請輸入：

```text
abc
0
```

預期結果：

```text
Invalid input. Please enter a number.
```

接著輸入 `0` 後，程式應該正常離開。

---

### 測試 6：輸入超出範圍的選項

請輸入：

```text
9
0
```

預期結果：

```text
Invalid option. Please choose 0-7.
```

---

## 八、確認報告輸出

當你執行過：

```text
1
3
6
```

之後，請打開: output/sample_report.txt，裡面應該包含：
HeapGuard Lite Report
Total Events: 10
Full Risk Ranking:
Detailed Event Information:
Complexity Summary

如果有另外新增自訂事件，`Total Events` 可能會變成 11 或更多。

## 九、單一模組測試

除了主程式之外，也可以分別測試各個類別。  
每次測試前先編譯：

```bash
javac src/*.java
```

然後依序可以執行：

```bash
java -cp src TestSecurityEvent
java -cp src TestEventAnalyzer
java -cp src TestEventQueue
java -cp src TestEventParser
java -cp src TestMinHeap
java -cp src TestEventSorter
```

這些測試檔主要是用來確認各模組的基本行為，例如：

- `SecurityEvent` 是否能正確儲存事件資料。
- `EventAnalyzer` 是否能正確計算風險分數。
- `EventQueue` 是否符合 FIFO 概念。
- `EventParser` 是否能讀取 sample data。
- `MinHeap` 是否能優先取出高風險事件。
- `EventSorter` 是否能依照風險分數排序。

## 十、目前測試重點總結

- 主流程要能正常跑：1 → 3 → 4 → 5 → 6 → 0

- 自訂事件要能正常加入： 2 → 選事件類型 → 輸入 user / IP / message → 3 → 5
- 報告要能正常輸出到：output/sample_report.txt
- 錯誤操作不能讓程式直接崩潰。
- 重複執行 `Analyze events` 時，事件原因不應該重複累積。
