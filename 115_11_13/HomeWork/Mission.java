import java.util.ArrayList;

/**
 * @date 2026-11-13
 * @author 黃于甄
 * @project 115_1_Java / 115_11_13 Mission
 *
 * problem:  一次探測車任務：依序執行腳本，每一步存成 MissionStep 放進日誌，
 *           並提供結果、指令次數、成功與拒絕筆數、最低能源等查詢。
 * interface: run / getOutcome / getStepCount / getStep /
 *            countCommands / countOk / countRejected / lowestEnergy
 * strategy: 狀態與行動規則交給 Rover，Mission 只負責流程與日誌；
 *           每執行一筆指令就把「指令、行動後能源、結果」存成 MissionStep。
 *
 * By claude
 */
public class Mission {
    // rover：執行這次任務的探測車。
    private Rover rover;
    // history：任務日誌，順序就是執行順序。
    private ArrayList<MissionStep> history;
    // returned：是否已收到返回指令（指令 0）。
    private boolean returned;

    // 建構子：記住探測車，並準備一份空的日誌。
    public Mission(Rover rover) {
        this.rover = rover;
        history = new ArrayList<>();
        returned = false;
    }

    //1. run：依序執行腳本並記錄日誌，直到腳本讀完、收到返回指令或探測車停止。
    public void run(int[] script) {
        for (int i = 0; i < script.length; i++) {
            if (rover.isStopped()) {
                break;
            }
            int command = script[i];
            String result = rover.execute(command);
            history.add(new MissionStep(command, rover.getEnergy(), result));
            if (command == 0) {
                returned = true;
                break;
            }
        }
    }

    // getOutcome：回傳任務結果，依「成功、耗盡、返回、未完成」的順序判斷。
    public String getOutcome() {
        if (rover.isSuccess()) { return "任務成功"; }
        if (rover.isExhausted()) { return "能源耗盡"; }
        if (returned) { return "已返回"; }
        return "任務未完成";
    }

    // getStepCount：回傳日誌筆數。
    public int getStepCount() { return history.size(); }

    // getStep：回傳第 index 筆紀錄（從 0 開始），不交出整個 history。
    public MissionStep getStep(int index) { return history.get(index); }

    // countCommands：統計指令 0～3 各出現幾次；無效指令不計入。
    public int[] countCommands() {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) { counts[command]++; }
        }
        return counts;
    }

    //2. countOk：回傳日誌中結果為「成功」的筆數。
    public int countOk() {
        int ok = 0;
        for (MissionStep step : history) {
            if (step.isSuccessful()) {
                ok++;
            }
        }
        return ok;
    }

    // countRejected：拒絕筆數 = 全部筆數 − 成功筆數 − 返回筆數。
    public int countRejected() {
        int returns = 0;
        for (MissionStep step : history) {
            if ("返回".equals(step.getResult())) { returns++; }
        }
        return history.size() - countOk() - returns;
    }

    //3. lowestEnergy：回傳日誌中最低的執行後能源；日誌是空的回傳 -1。
    public int lowestEnergy() {
        if (history.isEmpty()) {
            return -1;
        }
        int lowest = history.get(0).getEnergy();
        for (MissionStep step : history) {
            if (step.getEnergy() < lowest) {
                lowest = step.getEnergy();
            }
        }
        return lowest;
    }
}