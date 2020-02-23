
package MyThr;

public class ABC {
    // Создать три потока, каждый из которых выводит определенную букву (A, B и C)
    // 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
    volatile int index = 0;
    public static int threadsNumber = 3;
    public static void main(String[] args) {
        ABC lock = new ABC();
            Thread t1 = new Thread(new Threads(lock));
            Thread t2 = new Thread(new Threads(lock));
            Thread t3 = new Thread(new Threads(lock));
            t1.start();
            t2.start();
            t3.start();

    }

    public static class Threads implements Runnable{
        private final ABC lock;
        static int count = 0;
        char name;
        char [] letters = {'A', 'B', 'C'};
        char [] lettersPrinted = new char[iterations* threadsNumber];
        boolean a = true;
        boolean b = false;
        boolean c = false;
        static int iterations = 5;
        public Threads(ABC obj) {
            this.name =letters[count];
            count++;
            this.lock = obj;
            System.out.print(name);
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (lock.index <= 13) {

                while ((name == 'A') && (!a)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while ((name == 'B') && (!b)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while ((name == 'C') && (!c)) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
           if ((a) && (name == 'A')) {
                        System.out.print(name);
                        lettersPrinted[lock.index] = name;
                        a = false;
                        b = true;
               lock.index++;
               lock.notifyAll();
                    }
            if ((b) && (name == 'B')) {
                System.out.print(name);
                lettersPrinted[lock.index] = name;
                b = false;
                c = true;
                lock.index++;
                lock.notifyAll();
            }
            if ((c) && (name == 'C')) {
                System.out.print(name);
                lettersPrinted[lock.index] = name;
                c = false;
                a = true;
                lock.index++;
                lock.notifyAll();
            }
        }

                        }

                    }
    }
}

