public class CreateStepDemo {
    public static void main(String[] args) {
        StepCard first = new StepCard(1, 14); //建立ㄧ個物件
        StepCard second = new StepCard(2, 12); //建立ㄧ個物件
        System.out.println("first=" + first.getCommand() + "/" + first.getEnergy());
        System.out.println("second=" + second.getCommand() + "/" + second.getEnergy());
        System.out.println("same object=" + (first == second));
        //比較的是兩個變數是否指向同一個物件。它們是各自 new 出來的，所以結果是 false
        
        // first.energy = 99; // 私有存取錯誤練習只在副本取消註解。
        // StepCard empty = new StepCard(); // 已宣告有參數建構子，不會自動補無參數版。
    }
}
