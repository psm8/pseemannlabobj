public class Philosopher implements Runnable{
    private Object leftFork;
    private Object rightFork;
    private Philosopher leftPhilosopher;
    private Philosopher rightPhilosopher;
    private String state;

    Philosopher(Object _leftFork, Object _rightFork){
        this.leftFork = _leftFork;
        this.rightFork = _rightFork;
        this.state = "thinking";
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if(leftPhilosopher.getState().equals("eating") || rightPhilosopher.getState().equals("eating")) { continue;}
                synchronized (leftFork) {
                    takeFork("left fork");
                    synchronized (rightFork) {
                        takeFork("right fork");
                        eat();
                        putDownFork("right fork");
                    }
                    putDownFork("left fork");
                }
            }
        } catch(InterruptedException e){
                Thread.currentThread().interrupt();
                return;
        }
    }

    private void think() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is thinking");
        Thread.sleep(((int) (Math.random() * 10000)));
    }

    private void eat() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is eating");
        Thread.sleep(((int) (Math.random() * 10000)));
    }

    private void takeFork(String fork) throws InterruptedException {
        if(fork.equals("left fork")){
            this.state = "eating";
        }
        System.out.println(Thread.currentThread().getName() + " took " + fork);
        Thread.sleep(((int) (Math.random() * 10000)));
    }

    private void putDownFork(String fork) throws InterruptedException {
        if(fork.equals("left fork")){
            this.state = "thinking";
        }
        System.out.println(Thread.currentThread().getName() + " put down " + fork);
        Thread.sleep(((int) (Math.random() * 10000)));
    }

    public String getState() {
        return state;
    }

    public void setNeighbours(Philosopher _leftphilosopher, Philosopher _rightphilosopher) {
        this.leftPhilosopher = _leftphilosopher;
        this.rightPhilosopher = _rightphilosopher;
    }
}
