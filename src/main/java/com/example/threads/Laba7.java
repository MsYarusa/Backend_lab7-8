package com.example.threads;

//Многопоточность
public class Laba7 {
    //Создаем переменную которая хранит сумму на счету
    private static int money = 200;
    //Создаем метод, который отвечает за списание со счета
    //Ключевое слово synchronized, грубо говоря, выстраивает потоки вызывающие данный метод в очередь
    //Таким образом не происходит одновременного чтения и записи данных
    public static synchronized void writeOffMoney(int amount, String user){
        if (amount <= money){
            try{
                Thread.sleep(1000);
                money -= amount;
                System.out.println(user + " снял " + amount + " р. со счета. На счету " + money + " р.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println(user + " не может снять " + amount + " р. со счета, так как на счету осталось " + money + " р.");
        }
    }

    public static void main(String[] args){
        //Инициализируем потоки
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Том пытается списать деньги со счета...");
                writeOffMoney( 100, "Том");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Мартин пытается списать деньги со счета...");
                writeOffMoney( 100, "Мартин");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Джозеф пытается списать деньги со счета...");
                writeOffMoney( 100, "Джозеф");
            }
        });
        //Запускаем потоки
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
