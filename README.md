# HeapGuard Lite

## 專案簡介

- HeapGuard Lite 是一個以 Java 終端機介面開發的資料結構應用專案。
- 本專案以「個人網路安全提醒」作為情境，模擬使用者可能遇到的安全事件，例如登入失敗、弱密碼、可疑檔案、釣魚訊息等。
- 本專案目前仍在開發中，並不是實際可用於真實資安防護的系統。
- 本專案的主要目的，是練習將資料結構與演算法應用到具體情境中，而不是單純寫資料結構作業。

## 專案目標

- 使用 Java 建立一個可在終端機操作的簡易分析工具。
- 將模擬的安全提醒事件轉換成程式可以處理的資料物件。
- 根據事件類型、使用者身分與訊息內容，計算每筆事件的風險分數。
- 使用資料結構協助排序與優先處理高風險事件。
- 展示 Queue、Min Heap、Stack、Merge Sort、ArrayList 等資料結構與演算法的應用方式。
- 練習撰寫可執行、可測試、可說明的 GitHub 專案。

## 目前完成進度

- 已建立 Java console menu，也就是終端機選單介面。
- 已完成基本選單輸入判斷，可處理 0 到 7 的功能選項。
- 已完成錯誤輸入處理，例如輸入非數字時不會讓程式直接崩潰。
- 已建立 `SecurityEvent` 資料模型，用來表示一筆安全事件。
- 已建立 `EventAnalyzer` 初版，用來根據規則計算事件風險分數。
- 已建立 `EventQueue`，用來以 Queue 的方式管理待處理事件。
- 已建立 `EventParser`，可從本地端文字檔讀取並解析事件資料。
- 已建立 `sample_events.txt`，作為本專案的本地端範例事件資料。
- 已建立測試檔案，用來檢查 `SecurityEvent`、`EventAnalyzer`、`EventQueue` 與 `EventParser` 是否能正常運作。
- 已完成多次 GitHub commit 與 push，用來記錄專案逐步開發進度。

## 預計功能

- 從本地端文字檔 `sample_events.txt` 讀取多筆安全事件。
- 讓使用者透過終端機選單新增自訂事件。
- 使用 Queue 模擬事件依序進入系統的流程。
- 使用 `EventAnalyzer` 自動計算每筆事件的風險分數。
- 使用 Min Heap 取出前 K 筆最高風險事件。
- 使用 Merge Sort 產生完整風險排序。
- 使用 Stack 檢查訊息中的括號是否平衡。
- 輸出分析結果到本地報告檔案。
- 在 README 或文件中補充時間複雜度分析。

## 專案結構

```text
HeapGuard-Lite/
├── src/
│   ├── Main.java
│   ├── ConsoleUI.java
│   ├── SecurityEvent.java
│   ├── EventAnalyzer.java
│   ├── EventQueue.java
│   ├── EventParser.java
│   ├── TestSecurityEvent.java
│   ├── TestEventAnalyzer.java
│   ├── TestEventQueue.java
│   └── TestEventParser.java
├── data/
│   └── sample_events.txt
├── docs/
├── output/
├── README.md
└── .gitignore
```

## 主要檔案說明

### Main.java

- 專案入口。
- 負責啟動 `ConsoleUI`。
- 不負責處理主要邏輯，避免主程式過度混亂。

### ConsoleUI.java

- 負責顯示終端機選單。
- 負責讀取使用者輸入。
- 負責根據使用者選項呼叫對應功能。
- 目前各功能仍以基本訊息輸出為主，之後會逐步接上正式功能。

### SecurityEvent.java

- 表示一筆安全事件。
- 儲存事件編號、事件類型、使用者、IP、訊息內容、風險分數、優先權與風險等級。
- 提供簡短摘要與詳細摘要輸出。

### EventAnalyzer.java

- 根據事件類型、使用者名稱與訊息內容計算風險分數。
- 根據風險分數判斷風險等級。
- 目前採用簡化的 rule-based scoring 規則。

### EventQueue.java

- 使用 Queue 的概念管理待處理事件。
- 提供 `enqueue`、`dequeue`、`peek`、`isEmpty`、`size` 等基本操作。
- 用來模擬事件依照發生順序進入系統。
- 目前使用 `ArrayList` 作為內部儲存結構，並以先進先出 FIFO 的邏輯處理事件。

### EventParser.java

- 負責讀取本地端文字檔中的事件資料。
- 將每一行文字資料解析成 `SecurityEvent` 物件。
- 目前讀取的範例資料位於 `data/sample_events.txt`。
- 此模組讓專案不再只依賴測試檔中手動建立的事件，而是可以從本地資料檔批次載入事件。

### TestSecurityEvent.java

- 用來測試 `SecurityEvent` 是否能正確建立事件資料。
- 用來檢查風險分數、優先權與事件摘要是否正常。

### TestEventAnalyzer.java

- 用來測試 `EventAnalyzer` 是否能正確計算風險分數。
- 用來確認事件理由、風險等級與優先權是否正確。

### TestEventQueue.java

- 用來測試 `EventQueue` 是否符合先進先出 FIFO 的處理邏輯。
- 用來確認事件加入與取出的順序是否正確。

### TestEventParser.java

- 用來測試 `EventParser` 是否能正確讀取 `sample_events.txt`。
- 用來確認文字資料是否能成功轉換為 `SecurityEvent` 物件。

### sample_events.txt

- 儲存本專案目前使用的本地端範例事件資料。
- 每一行代表一筆模擬安全事件。
- 每筆事件包含事件類型、使用者、IP 與訊息內容。
- 目前資料以英文為主，避免初期開發時出現中文編碼問題。

## 執行方式

- 編譯所有 Java 檔案：

```bash
javac src/*.java
```

- 執行主程式：

```bash
java -cp src Main
```

- 執行 `SecurityEvent` 測試檔：

```bash
java -cp src TestSecurityEvent
```

- 執行 `EventAnalyzer` 測試檔：

```bash
java -cp src TestEventAnalyzer
```

- 執行 `EventQueue` 測試檔：

```bash
java -cp src TestEventQueue
```

- 執行 `EventParser` 測試檔：

```bash
java -cp src TestEventParser
```

## 目前選單功能

- `1. Load sample events`
  - 預計用來載入本地端範例事件資料。
  - 目前已完成 `EventParser` 與 `sample_events.txt`，但尚未正式接上 `ConsoleUI` 的選單功能。

- `2. Add custom event`
  - 預計讓使用者自行新增一筆安全事件。
  - 目前尚未接上正式輸入流程。

- `3. Analyze events`
  - 預計用來分析所有已載入或新增的事件。
  - 目前已完成 `EventAnalyzer` 初版，但尚未接上完整事件佇列與分析流程。

- `4. Show Top-K alerts`
  - 預計使用 Min Heap 顯示前 K 筆最高風險事件。
  - 目前尚未接上 Min Heap 功能。

- `5. Show full ranking`
  - 預計使用 Merge Sort 顯示完整風險排序。
  - 目前尚未接上排序功能。

- `6. Export report`
  - 預計輸出分析結果到 `output` 資料夾。
  - 目前尚未接上報告輸出功能。

- `7. Show complexity summary`
  - 顯示本專案預計使用的資料結構與時間複雜度。
  - 目前已可在終端機顯示基本複雜度說明。

- `0. Exit`
  - 離開程式。

## 範例事件資料格式

- 本專案目前使用以下格式表示一筆事件：

```text
EVENT_TYPE,user=username,ip=ip_address,message=event message
```

- 範例：

```text
LOGIN_FAIL,user=admin,ip=192.168.1.10,message=failed login attempt
```

- 上述事件可被解析為：

```text
Type: LOGIN_FAIL
User: admin
IP: 192.168.1.10
Message: failed login attempt
```

## 範例事件分析

- 一筆模擬事件可能包含以下資訊：

```text
Type: LOGIN_FAIL
User: admin
IP: 192.168.1.10
Message: failed login attempt with password=123456
```

- 這筆事件可以被分析為：

```text
LOGIN_FAIL: +40
admin user: +20
password keyword: +20
Total risk score: 80
Risk level: CRITICAL
```

## 風險分數設計

- 本專案目前使用簡化的規則式計分方式。
- 不同事件類型會有不同基礎分數。
- 若事件中出現特定使用者或關鍵字，會額外增加風險分數。
- 風險分數最高限制為 100 分。
- 優先權計算方式為：

```text
priority = 100 - riskScore
```

- 因為本專案預計使用 Min Heap，所以 priority 越小的事件會越早被取出。
- 換句話說，riskScore 越高，priority 越低，事件越需要優先處理。

## 目前風險規則範例

- `LOGIN_FAIL`
  - 登入失敗事件。
  - 基礎風險分數：+40。

- `SQL_INJECTION`
  - 疑似 SQL injection 事件。
  - 基礎風險分數：+90。

- `XSS_ATTEMPT`
  - 疑似 XSS 攻擊事件。
  - 基礎風險分數：+80。

- `WEAK_PASSWORD`
  - 弱密碼相關事件。
  - 基礎風險分數：+70。

- `SUSPICIOUS_FILE`
  - 可疑檔案相關事件。
  - 基礎風險分數：+70。

- `PHISHING_SMS`
  - 可疑簡訊或釣魚訊息事件。
  - 基礎風險分數：+80。

- `NORMAL_LOGIN`
  - 正常登入事件。
  - 基礎風險分數：+10。

- `admin`
  - 若使用者為 admin，額外增加風險分數。
  - 額外分數：+20。

- `root`
  - 若使用者為 root，額外增加風險分數。
  - 額外分數：+20。

- `password`
  - 若訊息中出現 password 關鍵字，額外增加風險分數。
  - 額外分數：+20。

- `.exe`
  - 若訊息中出現可執行檔副檔名，額外增加風險分數。
  - 額外分數：+20。

## 預計資料結構應用

- Queue
  - 用來模擬事件依照發生順序進入系統。
  - 符合先進先出 FIFO 的資料處理邏輯。
  - 目前已完成 `EventQueue` 初版。

- Min Heap
  - 用來取出最高風險事件。
  - 本專案會透過 `priority = 100 - riskScore`，讓高風險事件在 Min Heap 中具有較小 priority。
  - 目前尚未實作。

- Merge Sort
  - 用來產生完整事件風險排序。
  - 適合展示 Divide and Conquer 的演算法概念。
  - 目前尚未實作。

- Stack
  - 預計用來檢查事件訊息中的括號是否平衡。
  - 可作為可疑輸入判斷的輔助功能。
  - 目前尚未實作。

- ArrayList
  - 用來儲存事件資料、風險原因與排序資料。
  - 作為專案內部資料管理的基本結構。
  - 目前已用於 `SecurityEvent` 與 `EventQueue`。

## 預計時間複雜度分析

- Queue enqueue / dequeue
  - 時間複雜度：O(1) 或 O(n)，取決於實作方式。
  - 目前 `EventQueue` 使用 `ArrayList.remove(0)`，因此 `dequeue` 在目前版本中為 O(n)。
  - 後續若改用 Linked List 或環狀陣列，可使 `dequeue` 更接近 O(1)。

- Min Heap insertion
  - 時間複雜度：O(log n)。

- Min Heap extraction
  - 時間複雜度：O(log n)。

- Top-K extraction
  - 時間複雜度：O(k log n)。

- Merge Sort
  - 時間複雜度：O(n log n)。

- Stack bracket checking
  - 時間複雜度：O(m)。

- EventParser file loading
  - 若共有 n 筆事件資料，讀取與解析的時間複雜度約為 O(n)。

- 其中：
  - `n` 代表事件數量。
  - `k` 代表要取出的高風險事件數量。
  - `m` 代表事件訊息長度。

## 專案限制

- 本專案目前只使用模擬資料。
- 本專案目前使用簡化的規則式計分方式。
- 本專案不是實際的資安防護系統。
- 本專案不會連接網路、資料庫或外部 API。
- 本專案不會執行真正的攻擊偵測。
- 本專案的主要重點是資料結構應用與 Java 實作練習。
- 目前 `Load sample events`、`Analyze events` 等選單項目尚未全部與正式模組整合完成。

## 未來改進方向

- 將 `EventParser` 正式接上 `ConsoleUI` 的 `Load sample events` 功能。
- 將 `EventQueue` 與 `EventAnalyzer` 串接，形成完整事件分析流程。
- 新增 `MinHeap`，用來處理 Top-K 高風險事件。
- 新增 `EventSorter`，使用 Merge Sort 產生完整排序。
- 新增 `ReportGenerator`，輸出分析報告。
- 新增 `BracketChecker`，使用 Stack 檢查括號是否平衡。
- 新增 `PasswordChecker`，進行簡化弱密碼判斷。
- 補充更多測試資料與測試案例。
- 補充更完整的設計說明與複雜度分析文件。
