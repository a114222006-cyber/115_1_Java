/**
 * @date 2026-11-13
 * @author 黃于甄
 * @project 115_1_Java / 115_11_13 MissionStep
 *
 * problem:  保存任務中「某一步」的快照，包含指令、執行後的能源與結果。
 * interface: MissionStep(int command, int energy, String result)
 *            getCommand / getEnergy / getResult / isSuccessful
 * strategy: private 欄位加建構子與 getter；
 *           isSuccessful 以 equals 比內容，判斷 result 是否為「成功」。
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

    // getCommand：回傳這一步的指令。
    public int getCommand() {
        return command;
    }

    // getEnergy：回傳這一步執行後的能源。
    public int getEnergy() {
        return energy;
    }

    // getResult：回傳這一步的結果字串。
    public String getResult() {
        return result;
    }

    // isSuccessful：這一步的結果是否為「成功」。
    public boolean isSuccessful() {
        return result.equals("成功");
    }
}