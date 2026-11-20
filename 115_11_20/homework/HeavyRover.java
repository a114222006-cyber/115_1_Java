// 類別：HeavyRover（重型探測車，第 7 週版本）
// 意義：一種探測車（is-a Rover），所以用 extends Rover 繼承探索、掃描、充電與全部狀態。
// 作用：在 Rover 的基礎上新增「鑽探」能力；本週只新增方法，不覆寫任何既有方法。
public class HeavyRover extends Rover {
    // 已提供的具名常數，避免把成本和產量兩個數字搞混；不是本週新增語法驗收。
    // DRILL_COST：鑽探一次消耗的能源格數。
    public static final int DRILL_COST = 8;
    // DRILL_YIELD：鑽探一次取得的樣本份數。
    public static final int DRILL_YIELD = 3;

    // TODO 2：只新增鑽探次數
    private int drillCount;

    public HeavyRover(int energy, int samples) {
        super(energy, samples);
        drillCount = 0;
    }

    public int getDrillCount() { return drillCount; }

    // TODO 3：先檢查停止，再扣能源，成功後才更新整組狀態
    public String drill() {
        if (isStopped()) { return "任務已停止"; }
        if (!spendEnergy(DRILL_COST)) { return "能源不足"; }
        addSamples(DRILL_YIELD);
        countAction();
        drillCount++;
        return "成功";
    }
}