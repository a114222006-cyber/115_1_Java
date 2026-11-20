// #!/usr/bin/env java
/*
 * @date 2026-11-20
 * @author 黃于甄 (Beverly)
 * @project 115_11_20 StandAloneExample
 *
 * 題目：用繼承寫 VIP 會員，VIP 是一種 Member，只多了加贈功能。
 *
 * 介面：
 *   VipMember(String name)   建構子，先呼叫 super(name)，再印出「VipMember 建構子: 名字」
 *   int getBonusCount()      取得已領加贈次數
 *   void claimBonus()        領取加贈：加 20 點，bonusCount 加 1
 *
 * 策略：extends Member，沿用名字、點數、buy、getPoints；
 *       只寫 VIP 才有的 bonusCount 與 claimBonus；
 *       加點數透過 Member 的 protected addPoints，不直接碰 private 的 points。
 *
 * By claude
 */
public class VipMember extends Member {
    private int bonusCount;

    public VipMember(String name) {
        super(name);    // 名字、點數交給 Member
        bonusCount = 0;
        System.out.println("VipMember 建構子: " + name);
    }

    public int getBonusCount() {
        return bonusCount;
    }

    public void claimBonus() {
        addPoints(20);  // 用 Member 的方法
        bonusCount++;
    }
}