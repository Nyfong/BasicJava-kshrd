package synchronizedtest;

class Counter2 {
    private int count = 0;

    public void increment() {
        synchronized (this) { // Synchronized block
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

class MyThread extends Thread {
    Counter2 Counter2;

    MyThread(Counter2 Counter2) {
        this.Counter2 = Counter2;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            Counter2.increment();
        }
    }
}

public class Block {
    public static void main(String[] args) throws InterruptedException {
        Counter2 Counter2 = new Counter2();
        MyThread t1 = new MyThread(Counter2);
        MyThread t2 = new MyThread(Counter2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Count: " + Counter2.getCount());
    }
}