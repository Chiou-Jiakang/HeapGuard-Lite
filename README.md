# HeapGuard Lite

## 專案簡介

- HeapGuard Lite 是一個以 Java 終端機介面開發的資料結構應用專案。
- 本專案以「個人網路安全提醒」作為情境，模擬使用者可能遇到的安全事件，例如登入失敗、弱密碼、可疑檔案、釣魚訊息、疑似 SQL injection 與疑似 XSS 攻擊等。
- 本專案並不是實際可用於真實資安防護的系統，而是一個以資料結構與 Java 實作練習為核心的作品。
- 本專案的主要目的，是展示如何將 Queue、Min Heap、Merge Sort、ArrayList 等資料結構與演算法應用到具體情境中。
- 本專案目前採用本地端文字檔作為資料來源，不需要連接網路、資料庫或外部 API。

## 專案目標

- 使用 Java 建立一個可在終端機操作的簡易安全事件分析工具。
- 將模擬的安全提醒事件轉換成程式可以處理的 `SecurityEvent` 物件。
- 根據事件類型、使用者身分與訊息內容，計算每筆事件的風險分數。
- 使用 Queue 模擬事件依序進入系統的處理流程。
- 使用 Min Heap 取出前 K 筆最高風險事件。
- 使用 Merge Sort 產生完整風險排序。
- 將分析結果輸出成文字報告。
- 練習撰寫可執行、可測試、可說明、可持續更新的 GitHub 專案。

## 目前完成進度

- 已建立 Java console menu，也就是終端機選單介面。
- 已完成基本選單輸入判斷，可處理 0 到 7 的功能選項。
- 已完成錯誤輸入處理，例如輸入非數字時不會讓程式直接崩潰。
- 已建立 `SecurityEvent` 資料模型，用來表示一筆安全事件。
- 已建立 `EventAnalyzer`，可根據規則計算事件風險分數與風險等級。
- 已建立 `EventQueue`，用來以 Queue 的方式管理待處理事件。
- 已建立 `EventParser`，可從本地端文字檔讀取並解析事件資料。
- 已建立 `sample_events.txt`，作為本專案的本地端範例事件資料。
- 已建立 `MinHeap`，可根據事件 priority 取出高風險事件。
- 已將 `Show Top-K alerts` 選單功能接上 `MinHeap`。
- 已建立 `EventSorter`，並使用 Merge Sort 產生完整風險排序。
- 已將 `Show full ranking` 選單功能接上 `EventSorter`。
- 已建立 `ReportGenerator`，可將分析結果輸出成文字報告。
- 已將 `Export report` 選單功能接上 `ReportGenerator`。
- 已建立多個測試檔案，用來檢查各模組是否能正常運作。
- 已透過多次 GitHub commit 與 push 記錄專案逐步開發進度。

## 目前可使用功能

- `1. Load sample events`
  - 從 `data/sample_events.txt` 讀取本地端範例事件資料。
  - 使用 `EventParser` 將文字資料轉換成 `SecurityEvent` 物件。
  - 將讀取到的事件放入 `EventQueue`。

- `2. Add custom event`
  - 預計讓使用者自行新增一筆安全事件。
  - 目前尚未接上正式輸入流程。

- `3. Analyze events`
  - 使用 `EventAnalyzer` 分析已載入的所有事件。
  - 根據事件類型、使用者名稱與訊息內容計算風險分數。
  - 根據風險分數設定風險等級與 priority。

- `4. Show Top-K alerts`
  - 使用 `MinHeap` 顯示前 5 筆最高風險事件。
  - 使用前需要先執行 `Load sample events` 與 `Analyze events`。
  - 若尚未載入或尚未分析，系統會顯示提示訊息。

- `5. Show full ranking`
  - 使用 `EventSorter` 與 Merge Sort 顯示完整風險排序。
  - 排序依據為 `riskScore`，風險分數越高者越前面。
  - 使用前需要先執行 `Load sample events` 與 `Analyze events`。

- `6. Export report`
  - 使用 `ReportGenerator` 將分析結果輸出到 `output/sample_report.txt`。
  - 報告內容包含總事件數、完整風險排序、每筆事件詳細資訊與複雜度摘要。
  - 使用前需要先執行 `Load sample events` 與 `Analyze events`。

- `7. Show complexity summary`
  - 在終端機顯示目前專案主要資料結構與演算法的時間複雜度摘要。

- `0. Exit`
  - 離開程式。

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
│   ├── MinHeap.java
│   ├── EventSorter.java
│   ├── ReportGenerator.java
│   ├── TestSecurityEvent.java
│   ├── TestEventAnalyzer.java
│   ├── TestEventQueue.java
│   ├── TestEventParser.java
│   ├── TestMinHeap.java
│   └── TestEventSorter.java
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
- 負責建立並啟動 `ConsoleUI`。
- 不負責處理主要邏輯，避免主程式過度混亂。

### ConsoleUI.java

- 負責顯示終端機選單。
- 負責讀取使用者輸入。
- 負責根據使用者選項呼叫對應功能。
- 目前已接上事件載入、事件分析、Top-K 高風險事件、完整風險排序、報告輸出與複雜度摘要功能。

### SecurityEvent.java

- 表示一筆安全事件。
- 儲存事件編號、事件類型、使用者、IP、訊息內容、風險分數、優先權與風險等級。
- 提供簡短摘要與詳細摘要輸出。
- 其中 `priority = 100 - riskScore`，用來配合 Min Heap 取出高風險事件。

### EventAnalyzer.java

- 根據事件類型、使用者名稱與訊息內容計算風險分數。
- 根據風險分數判斷風險等級。
- 目前採用簡化的 rule-based scoring 規則。
- 可偵測的條件包含事件類型、admin/root 使用者、password 關鍵字、`.exe`、`OR 1=1` 與 `<script>` 等。

### EventQueue.java

- 使用 Queue 的概念管理待處理事件。
- 提供 `enqueue`、`dequeue`、`peek`、`isEmpty`、`size`、`clear` 等基本操作。
- 用來模擬事件依照發生順序進入系統。
- 目前使用 `ArrayList` 作為內部儲存結構。

### EventParser.java

- 負責讀取本地端文字檔中的事件資料。
- 將每一行文字資料解析成 `SecurityEvent` 物件。
- 目前讀取的範例資料位於 `data/sample_events.txt`。
- 此模組讓專案可以從本地資料檔批次載入事件，而不只依賴測試檔手動建立事件。

### MinHeap.java

- 使用 Min Heap 管理事件優先權。
- 根據 `SecurityEvent` 的 `priority` 欄位排序事件。
- 因為 `priority = 100 - riskScore`，所以風險分數越高的事件會有越低的 priority，並會越早被取出。
- 用於支援 `Show Top-K alerts` 功能。

### EventSorter.java

- 使用 Merge Sort 進行完整事件排序。
- 排序依據為 `riskScore`，分數越高者越前面。
- 用於支援 `Show full ranking` 功能。
- 此模組展示 Divide and Conquer 的排序概念。

### ReportGenerator.java

- 負責將分析結果輸出成文字報告。
- 目前輸出位置為 `output/sample_report.txt`。
- 報告內容包含完整風險排序、每筆事件詳細資訊與複雜度摘要。
- 報告檔案屬於程式輸出結果，目前不一定會被 Git 追蹤。

### sample_events.txt

- 儲存本專案目前使用的本地端範例事件資料。
- 每一行代表一筆模擬安全事件。
- 每筆事件包含事件類型、使用者、IP 與訊息內容。
- 目前資料以英文為主，避免初期開發時出現中文編碼問題。

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

### TestMinHeap.java

- 用來測試 `MinHeap` 是否能依照 priority 正確取出事件。
- 用來確認高風險事件會優先被取出。

### TestEventSorter.java

- 用來測試 `EventSorter` 是否能依照風險分數進行排序。
- 用來確認 Merge Sort 排序結果是否為風險分數由高到低。

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

- 執行 `MinHeap` 測試檔：

```bash
java -cp src TestMinHeap
```

- 執行 `EventSorter` 測試檔：

```bash
java -cp src TestEventSorter
```

## 建議操作流程

- 執行主程式：

```bash
java -cp src Main
```

- 在終端機中依序輸入：

```text
1
3
4
5
6
0
```

- 對應效果如下：

```text
1：載入 sample_events.txt 中的事件資料
3：分析所有已載入事件
4：顯示前 5 筆最高風險事件
5：顯示完整風險排序
6：輸出分析報告
0：離開程式
```

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

- 因為本專案使用 Min Heap，所以 priority 越小的事件會越早被取出。
- 換句話說，riskScore 越高，priority 越低，事件越需要優先處理。

## 目前風險規則範例

- `PHISHING_SMS`
  - 可疑簡訊或釣魚訊息事件。
  - 基礎風險分數：+80。

- `WEAK_PASSWORD`
  - 弱密碼相關事件。
  - 基礎風險分數：+70。

- `SUSPICIOUS_FILE`
  - 可疑檔案相關事件。
  - 基礎風險分數：+70。

- `LOGIN_FAIL`
  - 登入失敗事件。
  - 基礎風險分數：+40。

- `SQL_INJECTION`
  - 疑似 SQL injection 事件。
  - 基礎風險分數：+90。

- `XSS_ATTEMPT`
  - 疑似 XSS 攻擊事件。
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

- `OR 1=1`
  - 若訊息中出現常見 SQL injection pattern，額外增加風險分數。
  - 額外分數：+30。

- `<script>`
  - 若訊息中出現 script tag，額外增加風險分數。
  - 額外分數：+30。

## 資料結構應用

- Queue
  - 用來模擬事件依照發生順序進入系統。
  - 符合先進先出 FIFO 的資料處理邏輯。
  - 目前已完成 `EventQueue` 初版。

- Min Heap
  - 用來取出最高風險事件。
  - 本專案透過 `priority = 100 - riskScore`，讓高風險事件在 Min Heap 中具有較小 priority。
  - 目前已用於 `Show Top-K alerts` 功能。

- Merge Sort
  - 用來產生完整事件風險排序。
  - 適合展示 Divide and Conquer 的演算法概念。
  - 目前已用於 `Show full ranking` 功能。

- ArrayList
  - 用來儲存事件資料、風險原因與排序資料。
  - 作為專案內部資料管理的基本結構。
  - 目前已用於 `SecurityEvent`、`EventQueue`、`EventParser`、`EventSorter` 等模組。

- Stack
  - 預計用來檢查事件訊息中的括號是否平衡。
  - 可作為可疑輸入判斷的輔助功能。
  - 目前尚未實作。

## 時間複雜度分析

- EventParser file loading
  - 若共有 n 筆事件資料，讀取與解析的時間複雜度約為 O(n)。

- EventAnalyzer risk scoring
  - 若共有 n 筆事件，每筆訊息長度約為 m，時間複雜度約為 O(n × m)。

- Queue enqueue
  - 目前時間複雜度為 O(1)。

- Queue dequeue
  - 目前 `EventQueue` 使用 `ArrayList.remove(0)`，因此 dequeue 在目前版本中為 O(n)。
  - 後續若改用 LinkedList 或環狀陣列，可讓 dequeue 更接近 O(1)。

- Min Heap insertion
  - 時間複雜度：O(log n)。

- Min Heap extraction
  - 時間複雜度：O(log n)。

- Top-K extraction
  - 時間複雜度：O(k log n)。

- Merge Sort
  - 時間複雜度：O(n log n)。

- Report generation
  - 若輸出 n 筆事件，時間複雜度約為 O(n)。

- Stack bracket checking
  - 預計時間複雜度：O(m)。
  - 目前尚未實作。

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
- 目前 `Add custom event` 尚未接上正式輸入流程。
- 目前尚未加入 Stack-based bracket checking。
- 目前尚未加入更完整的 password weakness detection。

## 未來改進方向

- 將 `Add custom event` 接上正式使用者輸入流程。
- 新增 `BracketChecker`，使用 Stack 檢查括號是否平衡。
- 新增 `PasswordChecker`，進行簡化弱密碼判斷。
- 補充更多測試資料與測試案例。
- 補充更完整的設計說明與複雜度分析文件。
- 改善 `EventQueue` 的 dequeue 實作方式，例如改用 LinkedList 或環狀陣列。
- 允許使用者自行輸入 Top-K 的 K 值。
- 增加更多事件類型與風險規則。
