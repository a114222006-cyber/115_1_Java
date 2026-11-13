/**
 * @date 2026-11-06
 * @author 黃于甄
 * @project 115_1_Java / 115_11_06 MissionStep
 *
 * problem:  用一個物件保存探測車任務中「某一步」的快照，
 *           包含指令、執行後的能源與執行結果。
 * interface: MissionStep(int command, int energy, String result)
 *            getCommand / getEnergy / getResult / isSuccessful
 * strategy: 用 private 欄位保存資料，建構子以 this 指定值，
 *           isSuccessful 以 equals 做內容比較，判斷 result 是否為 "OK"。
 *
 * By claude
 */
public class MissionStep {
    private int command;
    private int energy;
    private String result;

    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    public int getCommand() {
        return command;
    }

    public int getEnergy() {
        return energy;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccessful() {
        return result.equals("OK");
    }
}