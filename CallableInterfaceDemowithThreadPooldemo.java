import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author sandip
 *
 */
public class CallableInterfaceDemowithThreadPooldemo
	{
		public static void main(String[] args) throws IOException
			{
				String[] infiles = {};
				String[] outfiles = {};
				// Thread[] threads = new Thread[infiles.length];
				ExecutorService es = Executors.newFixedThreadPool(3);
				Future<Integer>[] results = new Future[infiles.length];
				for (int i = 0; i < infiles.length; i++) {
					Adder adder = new Adder(infiles[i], outfiles[i]);
					results[i] = es.submit(adder);

				}
				try {
					es.shutdown();
					es.awaitTermination(60, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Future<Integer> result : results) {
					try {
						int value = result.get();
						System.out.println("Total :" + value);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						Throwable adderEx = e.getCause();
						// do something with adderex

						e.printStackTrace();
					} catch (Exception e) {
						// other exception not from execution exception
					}

				}

			}

	}

class Adder implements Callable<Integer>
	{
		private String inFile, outFile;

		public Adder(String inFile, String outFile)
			{
				this.inFile = inFile;
				this.outFile = outFile;
			}

		public int doAdd() throws IOException
			{
				int total = 0;
				String line = null;
				try (BufferedReader br = Files.newBufferedReader(Paths.get(inFile))) {
					while (null != (line = br.readLine())) {
						total += Integer.parseInt(line);
					}
				}
				return total;
			}

		@Override
		public Integer call() throws IOException
			{
				return doAdd();
			}
	}
