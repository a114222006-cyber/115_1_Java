// #!/usr/bin/env java
/*
 * @date 2026-11-20
 * @author 黃于甄 (Beverly)
 * @project 115_11_20 StandAloneExample
 *
 * 題目：並排執行「繼承版」與「不用繼承版」的 VIP 會員，比較結果。
 *
 * 介面：main(String[] args)，執行入口。
 *
 * 策略：兩位 VIP 都購物 85 元、領一次加贈，印出點數與獎勵次數；
 *       結果相同（85 / 10 = 8，再加 20 = 28），差別在程式碼是否重複。
 *
 * 預期輸出：
 *   --- 繼承版 VipMember ---
 *   Member 建構子: Bob
 *   VipMember 建構子: Bob
 *   點數=28, 獎勵次數=1
 *   --- 不用繼承版 StandaloneVipMember ---
 *   StandaloneVipMember 建構子: Bob
 *   點數=28, 獎勵次數=1
 *
 * By claude
 */
public class CompareMemberDemo {
    public static void main(String[] args) {
        System.out.println("--- 繼承版 VipMember ---");
        VipMember inherited = new VipMember("Bob");
        inherited.buy(85);
        inherited.claimBonus();
        System.out.println("點數=" + inherited.getPoints()
                + ", 獎勵次數=" + inherited.getBonusCount());

        System.out.println("--- 不用繼承版 StandaloneVipMember ---");
        StandaloneVipMember standalone = new StandaloneVipMember("Bob");
        standalone.buy(85);
        standalone.claimBonus();
        System.out.println("點數=" + standalone.getPoints()
                + ", 獎勵次數=" + standalone.getBonusCount());

        // 實驗 2：是不是一種 Member？（在副本操作，一次只取消一行註解）
        // Member asMember = inherited;     // 可以編譯：VipMember 是一種 Member
        // Member notMember = standalone;   // 編譯錯誤：StandaloneVipMember 沒有 extends Member，不是一種 Member
    }
}