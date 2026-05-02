# HeapGuard Lite 複雜度分析

## 一、符號定義

- `n`：事件數量。
- `k`：要取出的 Top-K 高風險事件數量。
- `m`：單筆事件訊息的平均長度。

## 二、EventParser

- 功能：
  - 從 `sample_events.txt` 讀取事件資料。
  - 將每一行解析成 `SecurityEvent` 物件。

- 時間複雜度：
  - 若共有 n 筆事件，讀取與解析的時間複雜度約為 O(n)。

- 空間複雜度：
  - 需要儲存 n 筆事件，因此為 O(n)。

## 三、EventAnalyzer

- 功能：
  - 根據事件類型、使用者名稱與訊息內容計算風險分數。

- 時間複雜度：
  - 若共有 n 筆事件，每筆訊息平均長度為 m，時間複雜度約為 O(n × m)。
  - 每筆事件會進行固定數量的字串檢查，而字串檢查與訊息長度有關。

- 空間複雜度：
  - 若每筆事件記錄固定數量的分析原因，整體約為 O(n)。

## 四、EventQueue

- 功能：
  - 保存事件進入系統的順序。

- `enqueue`
  - 目前使用 `ArrayList.add()`。
  - 平均時間複雜度為 O(1)。

- `dequeue`
  - 目前使用 `ArrayList.remove(0)`。
  - 因為移除第一個元素後，其餘元素需要往前移動，所以時間複雜度為 O(n)。

- 改進方向：
  - 若改用 LinkedList 或 circular array，可以讓 `dequeue` 接近 O(1)。

## 五、MinHeap

- 功能：
  - 根據事件 priority 取出高風險事件。

- `insert`
  - 插入後需要 heapify up。
  - 時間複雜度為 O(log n)。

- `extractMin`
  - 取出根節點後需要 heapify down。
  - 時間複雜度為 O(log n)。

- Top-K extraction
  - 若取出 k 筆事件，每次 `extractMin` 為 O(log n)。
  - 總時間複雜度為 O(k log n)。

## 六、EventSorter / Merge Sort

- 功能：
  - 將所有事件依 `riskScore` 由高到低排序。

- 時間複雜度：
  - Merge Sort 的時間複雜度為 O(n log n)。

- 空間複雜度：
  - Merge Sort 需要額外列表儲存左右子陣列與合併結果。
  - 空間複雜度約為 O(n)。

## 七、ReportGenerator

- 功能：
  - 將完整風險排序、事件詳細資訊與複雜度摘要輸出到文字檔。

- 時間複雜度：
  - 若輸出 n 筆事件，時間複雜度約為 O(n)。

- 空間複雜度：
  - 報告內容直接寫入檔案，額外記憶體使用量不高。
  - 主要空間仍由事件清單本身決定。

## 八、整體流程複雜度

若使用者執行以下主流程：

```text
1. Load sample events
3. Analyze events
4. Show Top-K alerts
5. Show full ranking
6. Export report
```

整體主要成本包含：

- 讀取事件：O(n)
- 分析事件：O(n × m)
- Top-K：O(k log n)
- 完整排序：O(n log n)
- 報告輸出：O(n)

因此整體可概略表示為：O(n × m + n log n + k log n)，若 k 遠小於 n，完整排序與事件分析通常是主要成本。
