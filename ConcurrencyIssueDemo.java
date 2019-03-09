import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sandip
 *
 */
public class ConcurrencyIssueDemo implements Runnable
	{
		private BankAccount account;

		public Worker(BankAccount bankAccount)
			{
				this.account = bankAccount;
			}

		@Override
		public void run()
			{
				for (int i = 0; i < 1000; i++) {
					int startBalance = account.getBalance();
					account.deposit(10);
					int endingBalance = account.getBalance();
					System.out.println("Thread " + Thread.currentThread().getName() + " start balnce " + startBalance
							+ " endnig balance 	" + endingBalance);
				}
			}

		public static void main(String[] args)
			{
				ExecutorService es = Executors.newFixedThreadPool(50);
				BankAccount ba = new BankAccount(100);

				for (int i = 0; i < 50; i++) {
					Worker worker = new Worker(ba);
					es.submit(worker);
				}
				es.shutdown();
			}
	}

class BankAccount
	{
		private int balance;

		public BankAccount(int balance)
			{
				this.balance = balance;
			}

		public int getBalance()
			{
				return balance;
			}

		public void deposit(int amount)
			{

				balance += amount;
			}

	}
