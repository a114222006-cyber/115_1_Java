# 第五週 AI 使用紀錄

另存 AI_USAGE.md。每欄簡短填寫，不貼完整對話。使用真實服務、教師示範、模擬建議要據實標示。未使用 AI，第一欄寫「未使用」及原因，其餘可填不適用；本人課堂測試仍填 LAB_REPORT。不要上傳個資、密碼、憑證或未授權程式。

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 | Claude（網頁聊天介面），貼程式碼與截圖提問 |
| 目的與提問摘要 | 請 AI 解釋 MissionLog 題目與程式（探測車指令、ArrayList<MissionStep> 日誌）；確認 MissionStep 的 TODO 1～3；比對老師第六週預告的 UML 類別圖與我的程式是否一致；整理 MissionStep.java 與 MissionLog.java 的標頭註解；協助排除執行結果不對的問題。 |
| 建議與本人處理 | 1. MissionStep TODO 1～3（建構子用 this 保存三個欄位、三個 getter 回傳對應欄位、isSuccessful 用 result.equals("OK") 比內容）：AI 確認我寫的是正確的，我保留。<br>2. MissionLog 的 history.add(new MissionStep(command, energy, result))、printReplay、printSummary（TODO 5、6）：AI 確認邏輯正確，我只刪掉 TODO 字樣的註解。<br>3. UML 比對：MissionLog 的 6 個 static 方法（main 為 public，其餘為套件存取）、MissionStep 的 3 個 private 欄位、建構子、3 個 getter、isSuccessful 都與老師 UML 相符。<br>4. 標頭註解：採用 AI 整理的格式（@date、@author、@project、problem、interface、strategy、By claude）。<br>5. 排錯：執行時印出中文的 starter 版輸出，AI 判斷是 Java 擴充套件用了舊的編譯快取（專案中有 starter、teacher、HomeWork 三個同名 MissionLog），建議清除 Java Language Server 快取，或在 HomeWork 資料夾用 javac -encoding UTF-8 MissionLog.java MissionStep.java 後執行 java MissionLog。<br>未處理：VS Code PROBLEMS 的提示，主要來自 starter 資料夾，與 HomeWork 無關。 |
| 本人實際驗證 | 修改前：在 VS Code 按 Run，輸出為中文的 starter 版結果：指令次數[0..3]=[0, 0, 0, 0]、步數=0, 成功=0, 拒絕=0, 略過=3、最低能源=無紀錄、最終能源=20, 樣本數=0 -> 任務未完成。<br>修改後：清除快取（或手動 javac）後重新執行 HomeWork 的 MissionLog，用 {1,1,2}、energy=20、samples=0，輸出 replay 三筆（energy 14、8、6，皆 OK）、counts[0..3]=[0, 2, 1, 0]、steps=3, ok=3, rejected=0, ignored=0、lowest energy=6、final energy=6, samples=5, actionCount=3 -> SUCCESS、battery=30.0。 |

![H.W](https://picgocloud.com/m/543a53fe-09df-47b1-9319-f5653b160d4d.png)
![H.W TODO1](https://picgocloud.com/m/e9f9be58-e800-4eaf-a96f-aa0e21c2e1b4.png)
![H.W TODO2](https://picgocloud.com/m/fd65d45a-f505-4755-94e2-60bf67e349a8.png)
![H.W Results](https://picgocloud.com/m/4e986324-775d-4ff0-811a-c783901af660.png)

## 我的反思

第五週讓我學到，把一步的資料包成物件，比用多條平行清單更不容易出錯。第四週要同時維護 commandLog、energyLog、resultLog 三條清單，三條要同步新增，位置容易對不上。第五週改成 MissionStep 物件，每一步的指令、能源與結果綁在一起，放進同一條 ArrayList<MissionStep>，之後回放、統計和摘要都只靠這一條清單。不過 MissionLog 還是同時管狀態、行動規則、迴圈、結果與報表，內容仍然偏多，這是第六週要繼續拆開的地方。
