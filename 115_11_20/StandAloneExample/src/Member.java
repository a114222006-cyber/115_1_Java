// #!/usr/bin/env java
/*
 * @date 2026-11-20
 * @author 黃于甄 (Beverly)
 * @project 115_11_20 StandAloneExample
 *
 * 題目：建立一般會員 Member，有名字和點數，購物時依金額累積點數，
 *       並當作之後 VipMember 繼承的父類別。
 *
 * 介面：
 *   Member(String name)       建構子，印出「Member 建構子: 名字」
 *   String getName()          回傳名字
 *   int getPoints()           回傳點數
 *   void buy(int price)       購物，每 10 元得 1 點（整數除法）
 *   protected addPoints(int)  增加點數，只接受正數
 *
 * 策略：name、points 設為 private，只能透過方法依規則修改；
 *       addPoints 設為 protected，讓子類別（VipMember）也能加點數。
 *
 * By claude
 */
// 類別：Member（第 7 週範例：一般會員）
// 意義：商店的一般會員，有名字和點數。
// 作用：當作繼承的父類別；購物時依金額累積點數。和探測車主任務沒有程式相依。
public class Member {
    // name：會員名字。
    private String name;
    // points：目前累積的點數；private，只能透過方法依規則增加。
    private int points;

    // 建構子：設定名字，點數從 0 開始；並印出一行訊息，方便觀察建構順序。
    public Member(String name) {
        this.name = name;
        points = 0;
        // 只為課堂觀察建構順序，一般產品不必在建構子印訊息。
        System.out.println("Member 建構子: " + name);
    }

    // getName：回傳會員名字。
    public String getName() { return name; }
    // getPoints：回傳目前點數。
    public int getPoints() { return points; }

    // buy：購物 price 元，每 10 元得 1 點（整數除法，不足 10 元的部分不計）。
    public void buy(int price) {
        if (price > 0) { addPoints(price / 10); }
    }

    // addPoints：增加 amount 點；只接受正數。宣告為 protected，讓子類別也能使用。
    // 本例在同一套件，其他類別也能呼叫；保留它給子類別擴充的意圖。
    protected void addPoints(int amount) {
        if (amount > 0) { points += amount; }
    }
}