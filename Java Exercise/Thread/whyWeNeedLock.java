public class RyanAndMonicaJob implements Runnable {

    private BankAccount account = new BankAccount();

    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            makeWithdrawal(10);
            if (account.getBalance() < 0) {
                System.out.println("Overdrawn");
            }
        }
    }

    private void makeWithdrawal(int amount) {
        if (account.getBalance() >= amount) {
            try {
                System.out.println(Thread.currentThread().getName() + " is about to withdraw");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " woke up.");
            account.withDraw(amount);
            System.out.println(Thread.currentThread().getName() + " completes the withdrawl");
        } else {
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        RyanAndMonicaJob thejob = new RyanAndMonicaJob();
        Thread one = new Thread(thejob);
        Thread two = new Thread(thejob);

        one.setName("Ryan");
        two.setName("Monica");

        one.start();
        two.start();
    }


    class BankAccount {
        private int balance = 100;

        public int getBalance() {
            return balance;
        }

        public void withDraw(int amount) {
            balance -= amount;
        }
    }
