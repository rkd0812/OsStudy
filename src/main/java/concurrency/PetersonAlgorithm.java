package concurrency;

public class PetersonAlgorithm {

    static boolean[] flag = {false, false};
    static int turn = 0;
    static int N = 4;

    static Thread process(int i) {
        return new Thread(() -> {
            int j = 1 -i;
            for (int n=0; n < N; n++) {
                System.out.println(i+": want CS");
                flag[i] = true;
                turn = j;

                while (flag[j] && turn == j) Thread.yield();

                System.out.println(i+": in CS"+n);
                sleep((long) (1000 * Math.random()));

                System.out.println(i+": done CS");
                flag[i] = false;
            }
        });
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting 2 processes (threads) ...");
            Thread p0 = process(0);
            Thread p1 = process(1);
            p0.start();
            p1.start();
            p0.join();
            p1.join();
        }
        catch (InterruptedException e) {}
    }

    static void sleep(double t) {
        try { Thread.sleep((long)t); }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
