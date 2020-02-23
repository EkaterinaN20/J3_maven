public class MyThreads implements Runnable {

        char name;
        private final HW4 lock;
        private int p;
        public volatile static int count=0;
        char[] names = {'A', 'B', 'C'};

        public MyThreads(HW4 obj, int p) {
            name = names[count];
            this.lock = obj;
            this.p = p;
            count++;
        }

        @Override
        public void run() {
            while(lock.status < 13) {
                synchronized (lock) {

                    while(!((lock.status % 3) == 0) && p == 1){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    while(!((lock.status % 3) == 1) && p == 2){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    while(!((lock.status % 3) == 2) && p == 3){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(name);
                    lock.status++;
                    lock.notifyAll();
                }
            }
        }
    }

