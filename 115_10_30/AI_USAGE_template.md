# 第四週 AI 使用紀錄

每欄簡短填寫，不必貼完整對話。沒用 AI，第一欄填「未使用」及原因，其餘免填；課堂測試仍要完成。使用模擬建議時，第一欄寫「教師模擬建議，未使用 AI 服務」，其餘依本人操作填寫。

| 欄位 | 記錄 |
|---|---|
| 使用工具 | claude |
| 使用目的與提問摘要 | 先請 AI 解釋 MissionLog 程式的流程（for 迴圈、switch 指令規則、三條日誌清單的用途）。理解後，再請 AI 依題目的 TODO 1～5 逐題提供寫法，並與題目要求交叉比對，最後整合成一份完整程式。 |
| AI 建議與本人處理 | 我採納 AI 的建議，並把 starter 中多餘的占位程式換成實際功能。新增內容：
1. TODO 2：在 switch 結束後加入 commandLog.add(command)、energyLog.add(energy)、resultLog.add(result)，拒絕與返回也會被記錄。
2. TODO 1：countCommands 用 for-each 走訪日誌，指令在 0..3 才執行 counts[command]++。
3. TODO 3：printReplay 用相同索引讀三條清單並逐行輸出。
4. TODO 4：用 Collections.frequency 計算 OK 與 RETURN，rejected = steps - ok - returned，ignored = scriptLength - steps。
5. TODO 5：先用 isEmpty 判斷，空清單印 N/A，否則用 Collections.min 印最小能源。
移除的多餘程式：兩行占位輸出 "TODO 4: summary" 與 "TODO 5: lowest energy"。 |
| 實際驗證結果 | 修改前（starter）用 {1,1,2} 執行：replay 為空、counts=[0,0,0,0]，summary 只印出 TODO 占位文字。
修改後同樣用 {1,1,2} 重新編譯執行，輸出：replay 三筆（energy 14、8、6，皆 OK）、counts[0..3]=[0, 2, 1, 0]、steps=3, ok=3, rejected=0, ignored=0、lowest energy=6、final energy=6, samples=5, actionCount=3 -> SUCCESS、battery=30.0。結果與預期一致，完整輸出可記在 LAB_REPORT F3。|

![1030](https://picgocloud.com/m/b35a6759-cde7-46f4-9909-9e8706da3b5a.png)

