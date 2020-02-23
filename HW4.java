public class HW4 {
    volatile int status = 0;

    public static void main(String[] args) {
        HW4 lock = new HW4();
        Thread t1 = new Thread(new MyThreads(lock,1));
        Thread t2 = new Thread(new MyThreads(lock,2));
        Thread t3 = new Thread(new MyThreads(lock,3));
        t1.start();
        t2.start();
        t3.start();
    }
}

