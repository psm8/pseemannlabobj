public class Main {
    static final Object fork1 = new Object();
    static final Object fork2 = new Object();
    static final Object fork3 = new Object();
    static final Object fork4 = new Object();
    static final Object fork5 = new Object();

    public static void main(String[] args) {
        Philosopher philosopher1 = new Philosopher(fork5, fork1);
        Philosopher philosopher2 = new Philosopher(fork1, fork2);
        Philosopher philosopher3 = new Philosopher(fork2, fork3);
        Philosopher philosopher4 = new Philosopher(fork3, fork4);
        Philosopher philosopher5 = new Philosopher(fork4, fork5);

        philosopher1.setNeighbours(philosopher5, philosopher2);
        philosopher2.setNeighbours(philosopher1, philosopher3);
        philosopher3.setNeighbours(philosopher2, philosopher4);
        philosopher4.setNeighbours(philosopher3, philosopher5);
        philosopher5.setNeighbours(philosopher4, philosopher1);

        new Thread(philosopher1, "philosopher1").start();
        new Thread(philosopher2, "philosopher2").start();
        new Thread(philosopher3, "philosopher3").start();
        new Thread(philosopher4, "philosopher4").start();
        new Thread(philosopher5, "philosopher5").start();
    }
}
