/**
 * @date    2026-10-30
 * @author  黃于甄
 * @project RoverMission（探測車任務）
 *
 * 題目：
 *   探測車初始能源 20、樣本 0。玩家輸入指令操作，
 *   樣本收集到 5 份即任務成功；能源耗盡則任務失敗；輸入 0 可中途返回。
 *
 * 介面（指令編號）：
 *   1 = Explore 探索：耗能源 6，得樣本 2
 *   2 = Scan    掃描：耗能源 2，得樣本 1
 *   3 = Charge  充電：能源 +5，上限 20
 *   0 = Return  返回：結束任務
 *   其他數字 = 無效指令
 *
 * 策略：
 *   1. 用 while 迴圈重複讀取指令，直到成功、耗盡或返回。
 *   2. 用 switch 依指令分流，每個指令先判斷「條件是否足夠」。
 *   3. 只有「成功執行」的行動才讓 actionCount +1。
 *   4. 條件不足時只印提示，不修改任何數值。
 *
 * By claude
 */
import java.util.Scanner; // 匯入 Scanner，用來讀取鍵盤輸入

public class RoverMission {

    // 程式進入點
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // 建立鍵盤輸入物件
        runMission(input, 20, 0);               // 開始任務：初始能源 20、樣本 0
        input.close();                          // 用完關閉輸入
    }

    // 教師提供的方法骨架：本週只補 TODO，不必自行重寫輸入及顯示。
    // 參數：input = 鍵盤輸入；energy = 目前能源；samples = 目前樣本數
    static void runMission(Scanner input, int energy, int samples) {
        int actionCount = 0;      // 成功行動的次數
        boolean running = true;   // 旗標：true 表示任務繼續，輸入 0 會改成 false

        showDashboard(energy, samples, actionCount); // 開始前先顯示一次初始狀態

        // 迴圈繼續的條件（三個要同時成立）：
        // 還沒按返回 && 能源還有剩 && 樣本還沒收集滿 5 份
        while (running && energy > 0 && samples < 5) {
            System.out.println("1 Explore | 2 Scan | 3 Charge | 0 Return");
            int command = input.nextInt(); // 讀取玩家輸入的指令數字

            switch (command) {
                case 1:
                    // D1：足夠能源才扣 6、加 2 份樣本並計次；不足只提示。
                    if (energy >= 6) {        // 能源 >= 6 才夠（剛好 6 也可以）
                        energy -= 6;          // 扣掉 6 點能源（等於 energy = energy - 6）
                        samples += 2;         // 樣本增加 2 份
                        actionCount++;        // 成功行動，次數 +1
                    } else {
                        System.out.println("NOT_ENOUGH_ENERGY"); // 能源不足：只提示，數值都不變
                    }
                    break; // 離開 switch（沒寫 break 會繼續執行下一個 case）

                case 2:
                    // D2：掃描成本 2、樣本加 1；只有成功行動才計次。
                    if (energy >= 2) {        // 能源 >= 2 才能掃描
                        energy -= 2;          // 扣 2 點能源
                        samples += 1;         // 樣本增加 1 份
                        actionCount++;        // 成功行動，次數 +1
                    } else {
                        System.out.println("NOT_ENOUGH_ENERGY"); // 能源不足：只提示
                    }
                    break;

                case 3:
                    // D3：未滿電才加 5，封頂 20；滿電不計次。
                    if (energy < 20) {        // 還沒滿電才能充電
                        // Math.min 取兩數中較小的，確保能源不會超過 20
                        // 例如 18 + 5 = 23，取 min(23, 20) = 20
                        energy = Math.min(energy + 5, 20);
                        actionCount++;        // 充電成功，次數 +1
                    } else {
                        System.out.println("BATTERY_FULL"); // 已滿電：只提示，不計次
                    }
                    break;

                case 0:
                    // break 只離開 switch，旗標讓下一次 while 判斷停止。
                    running = false; // 把旗標設為 false，下次檢查 while 條件時就會結束迴圈
                    break;

                default:
                    // 輸入 0~3 以外的數字都會跑到這裡
                    System.out.println("UNKNOWN_COMMAND");
                    break;
            }

            // 拒絕及返回時也顯示，可觀察到狀態沒有被修改。
            showDashboard(energy, samples, actionCount); // 每輪結束都印出最新狀態
        }

        // 迴圈結束後，判斷任務結果（順序很重要，上面的條件優先）
        // 同一行動可能同時耗盡能源與完成任務，依需求優先判斷成功。
        if (samples >= 5) {
            System.out.println("SUCCESS");    // 樣本收集完成 → 成功
        } else if (energy == 0) {
            System.out.println("EXHAUSTED");  // 能源耗盡且樣本不足 → 失敗
        } else {
            System.out.println("RETURNED");   // 其餘情況 = 玩家主動返回
        }
    }

    // 計算電量百分比
    // 用 20.0（小數）而不是 20，才不會變成整數除法
    // 例如 energy = 8：8 / 20.0 = 0.4，再 * 100 = 40.0
    // 若寫成 8 / 20 會得到 0（整數除法會捨去小數）
    static double calculateBatteryPercent(int energy) {
        return energy / 20.0 * 100;
    }

    // 顯示目前狀態：能源、樣本數、行動次數、電量百分比
    static void showDashboard(int energy, int samples, int actionCount) {
        System.out.println("energy=" + energy + ", samples=" + samples
                + ", actionCount=" + actionCount
                + ", battery=" + calculateBatteryPercent(energy));
    }
}