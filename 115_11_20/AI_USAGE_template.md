# 第七週AI使用紀錄

姓名／學號：黃于甄／A114222006
工具及來源：Claude（claude.ai 對話，截圖見下方）

## 精簡紀錄

| 步驟 | 使用目的與Prompt摘要（來源：Claude 對話） | 採納／拒絕與理由 | 本人驗證：預期與實際 |
|---|---|---|---|
| 1 | 先理解題目。問 AI：TODO 1 的 explore 和 scan 要怎麼重構？請先解釋再給做法。 | 採納。理解到要先完成 spendEnergy，再改 explore，最後獨立改 scan，順序不能反。 | 預期：重構後規則只寫一次。實際：explore、scan 都改成呼叫 spendEnergy。 |
| 2 | 寫 spendEnergy。Prompt：「spendEnergy：嘗試扣除 amount 格能源。amount 必須是正數且不超過目前能源，成功就扣除並回傳 true；否則回傳 false，能源不變。給我程式碼的建議」 | 採納。先擋掉不合法的情況再扣除，不在裡面加樣本或計次。 | 預期：能源 6 扣 6 成功變 0；能源 5 扣 6 回 false 且不變。實際：與預期相符。 |
| 3 | 比較寫法。問 AI：我的寫法和老師的寫法哪個比較好？ | 採納老師的 guard clause 寫法（`amount <= 0 \|\| amount > energy` 先回 false）。理由：兩者邏輯等價，但老師的寫法沒有巢狀，和 explore、scan 的風格一致。 | 預期：兩種寫法行為相同。實際：用邊界值 0、負數、剛好等於能源測，結果一致。 |
| 4 | 理解繼承。問 AI：「只新增鑽探次數，不再宣告 energy、samples、actionCount」是什麼意思？欄位、建構子、getDrillCount 怎麼寫？ | 採納。只新增 `private int drillCount`，建構子 super 之後設為 0。理由：重複宣告父類別欄位會造成 field hiding，兩邊數值對不上。 | 預期：新車鑽探次數 0，鑽探成功一次變 1。實際：與預期相符。 |
| 5 | 寫 drill()。問 AI：drill 不經過 execute，要怎麼寫？為什麼要先檢查停止、再檢查能源、最後才更新狀態？（附上 Rover、HeavyRover 程式） | 採納。順序：isStopped()、spendEnergy(DRILL_COST)、通過後才 addSamples、countAction、drillCount++。理由：被拒絕時在更新前就 return，狀態不會被改到。 | 預期：(8,0) 成功變 0/3/1/1；(7,0) 能源不足；(0,0)、(20,5) 任務已停止，狀態都不變。實際：與預期相符。 |
| 6 | 整體驗證。執行 HeavyRoverTry，並請 AI 對照檢查三個程式。 | 採納。沒有需要修改的地方。 | 預期：全部印「相符」。實際：（執行後確認，填「全部相符」或寫出不符的那行） |

![spendyenergy](https://picgocloud.com/m/vgrf38qv/6f001fc6-aa61-4a5c-8f4a-b2bd027836e1.png)
![result](https://picgocloud.com/m/vgrf38qv/82be105c-a502-48e3-a8c2-09e83cc3d382.png)
![drill](https://picgocloud.com/m/vgrf38qv/c2f220c6-242c-483f-8a37-520b8c34fd92.png)
![HeavyRover1](https://picgocloud.com/m/vgrf38qv/e3691761-1a85-4705-917a-235cddd96529.png)
![HeavyRover2](https://picgocloud.com/m/vgrf38qv/56c5e6f5-99d3-45d9-b062-eb12d11bd54e.png)


## 反思
## 反思心得

這週我學到繼承時子類別只需要新增自己獨有的欄位，父類別的狀態要透過 protected 方法
（spendEnergy、addSamples、countAction）來修改。另外，新的公開入口 drill 不會經過 execute，
所以要自己先檢查停止，並且先全部拒絕條件再更新狀態，這樣被拒絕時數值才不會被改到。
