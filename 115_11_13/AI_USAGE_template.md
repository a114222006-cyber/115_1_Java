# 第六週AI使用紀錄

另存日期目錄AI_USAGE.md。四欄簡短填寫，不貼完整對話。使用真實AI、教師模擬回覆、教師示範與未使用AI要據實標示；未使用AI仍須填本人程式與圖的驗證。不可上傳個資、密碼或未授權資料。

| 欄位 | 本人紀錄 |
|---|---|
| 使用工具／方式 | Claude（網頁聊天介面），貼程式碼與截圖提問 |
| 目的與提問摘要 | 請 AI 解釋第 6 週 Mission、Rover、MissionStep 的分工；依 starter 的 TODO 1～3 提供 Mission.run、countOk、lowestEnergy 的寫法；整理 MissionStep 的 isSuccessful（改比「成功」）；協助排除執行結果不對的問題。 |
| 建議與本人處理 | 1. Mission TODO 1（run）：每讀一筆先檢查 rover.isStopped()，呼叫 rover.execute(command)，用 history.add(new MissionStep(command, rover.getEnergy(), result)) 記錄，指令 0 先記錄再把 returned 設為 true 並離開迴圈。我採用。<br>2. TODO 2（countOk）：for-each 走訪 history，用 isSuccessful() 計數。我採用。<br>3. TODO 3（lowestEnergy）：空清單回傳 -1，否則先取第一筆再以迴圈求最小值。我採用。<br>4. MissionStep.isSuccessful 改成 result.equals("成功")，因為這週 Rover 回傳中文結果字串。我採用。<br>5. 排錯：執行結果步數為 0，原因是 Mission.java 還沒貼上完成版或沒存檔；另外終端機不在 HomeWork 資料夾導致 javac 找不到檔案。我改成 cd 到 115_11_13\HomeWork 後再編譯執行。<br>未處理：VS Code PROBLEMS 的提示，不影響編譯與執行。 |
| 本人實際驗證 | 修改前：Mission.run 還是空的，輸出 步數=0, 成功=0, 拒絕=0, 略過=3、最低能源=無紀錄、最終能源=20, 樣本數=0 -> 任務未完成。<br>修改後：在 HomeWork 資料夾執行 javac -encoding UTF-8 *.java 與 java MissionLog，用 {1,1,2}、energy=20、samples=0，輸出步驟 1～3（能源 14、8、6，皆成功）、指令次數[0..3]=[0, 2, 1, 0]、步數=3, 成功=3, 拒絕=0, 略過=0、最低能源=6、最終能源=6, 樣本數=5, 行動次數=3 -> 任務成功、電量百分比=30.0。結果與預期一致。 |

![H.W TODO1](https://picgocloud.com/m/a50c8b3f-b65e-4d4e-9df4-e519480fae0d.png)
![H.W Results](https://picgocloud.com/m/6365caab-ad55-47bb-8fee-e75f73fffd9d.png)
![H.W Org chart](https://picgocloud.com/m/31ac57b0-1856-4f45-9067-160425e1a339.png)
![H.W](https://picgocloud.com/m/466ccc46-6ae5-4911-b400-098f50f88220.png)

## 我的反思

第六週讓我學到如何把一個程式拆得更細、更清楚。第五週雖然已經有 MissionStep，但 MissionLog 仍然同時處理狀態、行動規則、腳本迴圈與報表，內容繁瑣。這週把它拆成四個類別，各有分工：

Rover：保存能源、樣本、行動次數，並集中管理探索、掃描、充電的規則。
MissionStep：保存任務中某一步的快照。
Mission：負責腳本迴圈、日誌（history）與是否返回（returned）。
MissionLog：只負責固定腳本入口與印出報表。

欄位都設為 private，只開放需要的 public 方法，外部不能直接改內部狀態，資料比較安全。拆開之後，要改行動規則只要改 Rover，要調整報表格式只要改 MissionLog，日後查看或維護都更方便。
