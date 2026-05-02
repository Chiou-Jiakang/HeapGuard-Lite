# HeapGuard Lite 設計說明

## 一、專案情境設計

- 本專案以「個人網路安全提醒」作為應用情境。
- 使用者可能遇到的事件包含可疑簡訊、弱密碼、可疑檔案、登入失敗、疑似 SQL injection、疑似 XSS 攻擊與正常登入等。
- 本專案並不是實際資安防護系統，而是將資安事件簡化為可被資料結構處理的模擬資料。
- 這樣的情境設計可以讓 Queue、Min Heap、Merge Sort 等資料結構有較具體的應用位置，而不是單純展示抽象操作。

## 二、資料流程設計

- 本專案目前支援兩種事件來源：
  - 從 `data/sample_events.txt` 載入本地端範例事件。
  - 由使用者透過終端機自行新增一筆事件。
- 事件進入系統後會被轉換成 `SecurityEvent` 物件。
- 使用者執行 `Analyze events` 後，系統會根據事件類型、使用者名稱與訊息內容計算風險分數。
- 分析後的事件可以被用於：
  - Top-K 高風險事件查詢。
  - 完整風險排序。
  - 分析報告輸出。

## 三、主要類別分工

- `Main.java`
  - 作為程式入口，負責啟動 `ConsoleUI`。

- `ConsoleUI.java`
  - 負責終端機選單、使用者輸入與功能呼叫。
  - 是使用者與系統互動的主要控制層。

- `SecurityEvent.java`
  - 表示一筆安全事件。
  - 儲存事件基本資料、風險分數、priority、風險等級與分析原因。

- `EventAnalyzer.java`
  - 根據規則計算事件風險分數。
  - 將分析結果寫回 `SecurityEvent`。

- `EventParser.java`
  - 從本地文字檔讀取事件資料。
  - 將文字格式轉換成 `SecurityEvent` 物件。

- `EventQueue.java`
  - 保存載入或新增後的事件。
  - 用來模擬事件依序進入系統。

- `MinHeap.java`
  - 根據 priority 取出高風險事件。
  - 支援 Top-K alerts 功能。

- `EventSorter.java`
  - 使用 Merge Sort 產生完整風險排序。

- `ReportGenerator.java`
  - 將分析結果輸出成文字報告。

## 四、為什麼使用 Queue

- Queue 符合事件依照發生順序進入系統的概念。
- 本專案在載入範例資料或新增自訂事件後，會將事件加入 `EventQueue`。
- 目前分析流程主要仍透過 `loadedEvents` 進行，這是為了保留完整事件清單以支援排序與報告輸出。
- 後續若要更貼近事件串流處理，可以改成從 Queue 逐筆 dequeue 後分析，再放入 analyzed list。

## 五、為什麼使用 Min Heap

- Top-K 高風險事件不需要完整排序所有資料。
- Min Heap 適合用來快速取出優先權最高的事件。
- 本專案將 priority 設計為：`text priority = 100 - riskScore`

## 六、為什麼使用 Merge Sort

- 完整風險排序需要將所有事件依照 riskScore 排列。
- Merge Sort 是典型的 Divide and Conquer 排序演算法。
- 本專案使用 Merge Sort 展示資料結構課程中的排序觀念。
- 排序結果依照 riskScore 由高到低排列。

## 七、目前設計限制

- 本專案使用模擬資料，不是真實資安偵測工具。
- 風險分數採用 rule-based scoring，尚未使用機器學習或進階資安分析。
- Top-K 的 K 值目前固定為 5。
- EventQueue 目前主要作為事件進入系統的模擬結構，尚未完全取代 loadedEvents 成為分析流程的唯一資料來源。
- 報告輸出為本地文字檔，尚未提供 GUI 或網頁介面。

## 八、後續可改進方向

- 允許使用者自行輸入 Top-K 的 K 值。
- 新增 PasswordChecker，更完整判斷弱密碼。
- 新增 BracketChecker，使用 Stack 檢查括號是否平衡。
- 改善 Queue 實作，例如使用 LinkedList 或 circular array。
- 增加更多事件類型與風險規則。
- 將測試檔整理到獨立 test 資料夾。
