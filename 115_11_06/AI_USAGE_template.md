# 第五週 AI 使用紀錄

另存 AI_USAGE.md。每欄簡短填寫，不貼完整對話。使用真實服務、教師示範、模擬建議要據實標示。未使用 AI，第一欄寫「未使用」及原因，其餘可填不適用；本人課堂測試仍填 LAB_REPORT。不要上傳個資、密碼、憑證或未授權程式。

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 |claude |
| 目的與提問摘要 |請 AI 依題目的 TODO 逐題提供 MissionStep 與 MissionLog 的寫法（建構子與 getter、isSuccessful 用 equals 比內容、ArrayList<MissionStep> 記錄日誌、回放與摘要統計），並整合成完整程式。過程中也請 AI 協助排除編譯與執行錯誤。|
| 建議與本人處理 |我採納 AI 的建議並完成：
1. MissionStep TODO 1～3：建構子用 this 保存三個欄位，三個 getter 回傳對應欄位，isSuccessful 用 result.equals("OK")。
2. MissionLog TODO 4：switch 結束後加入 history.add(new MissionStep(command, energy, result))，拒絕、無效與返回也會被記錄。
3. TODO 5：printReplay 用索引讀取每個 MissionStep 並逐行輸出。
4. TODO 6：for-each 走訪 history，用 isSuccessful() 計算 OK，並計算 RETURN 筆數。
排錯：file not found: MissionStep.java 是因為檔案在 starter 資料夾，改成 javac -encoding UTF-8 -d out starter/MissionStep.java starter/MissionLog.java。
未處理：VS Code 的 PROBLEMS 提示，不影響編譯與執行。 |
| 本人實際驗證 |修改前：用 {1,1,2} 執行，replay 為空、counts[0..3]=[0, 0, 0, 0]、steps=0, ok=0, rejected=0, ignored=3、lowest energy=N/A。
修改後：重新編譯執行，replay 三筆（energy 14、8、6，皆 OK）、counts[0..3]=[0, 2, 1, 0]、steps=3, ok=3, rejected=0, ignored=0、lowest energy=6、final energy=6, samples=5, actionCount=3 -> SUCCESS、battery=30.0。結果與預期一致。 |
![EXAMPLES](https://picgocloud.com/m/6b2f4fcb-a9b0-4d6a-b1e9-52ebd51db591.png)
![EXAMPLES](https://picgocloud.com/m/69c1869f-7983-4d33-ac69-cc54dc0e63ad.png)
![說1106](https://picgocloud.com/m/c537fb50-ee35-4546-8579-19f3fb91878d.png)

