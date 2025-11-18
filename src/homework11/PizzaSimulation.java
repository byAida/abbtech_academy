package homework11;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class PizzaSimulation {

    private static final Object lock = new Object();

    public static void main(String[] args) {

        List<PizzaOrder> orders = new ArrayList<>();
        int totalOrders = 5;

        long simulationStart = System.currentTimeMillis();

        for (int i = 1; i <= totalOrders; i++) {
            PizzaOrder order = new PizzaOrder(i);
            orders.add(order);
            order.start();
        }

        for (PizzaOrder order : orders) {
            try {
                order.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long simulationEnd = System.currentTimeMillis();
        long totalSimulationTime = (simulationEnd - simulationStart) / 1000;

        PizzaOrder fastest = orders.get(0);
        for (PizzaOrder order : orders) {
            if (order.getTotalTime() < fastest.getTotalTime()) {
                fastest = order;
            }
        }

        System.out.println("Bütün sifarişlər tamamlandı!");
        System.out.println("Ümumi simulyasiya vaxtı: " + totalSimulationTime + " saniyə");
        System.out.println("Ən sürətli sifariş: Sifariş " + fastest.getOrderNumber() + " → " + fastest.getTotalTime() + " saniyə");
    }

    static class PizzaOrder extends Thread {

        private final int orderNumber;
        private int totalTime;
        private final Random rand = new Random();

        public PizzaOrder(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public int getTotalTime() {
            return totalTime;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            log("Başladı.");
            preparePizza();
            bakePizza();
            deliverPizza();

            long end = System.currentTimeMillis();
            totalTime = (int) ((end - start) / 1000);
            log("Pizza çatdırıldı!");
            log("Ümumi vaxt: " + totalTime + " saniyə");
        }

        private void preparePizza() {
            log("Pizzanın hazırlanmasına başlanıldı...");
            sleepRandom(1, 3);
            log("Hazırlanma tamamlandı.");
        }

        private void bakePizza() {
            log("Pizzanın bişirilməsinə başlanıldı...");
            sleepRandom(3, 6);
            log("Bişirmə tamamlandı.");
        }

        private void deliverPizza() {
            log("Pizza müştəriyə çatdırılır...");
            sleepRandom(2, 5);
        }

        private void sleepRandom(int minSec, int maxSec) {
            int sleepTime = rand.nextInt(maxSec - minSec + 1) + minSec;
            try {
                Thread.sleep(sleepTime * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void log(String message) {
            synchronized (lock) {
                System.out.println("Sifariş " + orderNumber + " " + message);
            }
        }
    }
}
