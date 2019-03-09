import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author sandip
 *
 */
public class ExecutorThreadPoolDemo
	{
		public static void main(String[] args) throws IOException
			{
				String[] infiles = {};
				String[] outfiles = {};
				// Thread[] threads = new Thread[infiles.length];
				ExecutorService es = Executors.newFixedThreadPool(3);
				for (int i = 0; i < infiles.length; i++) {
					Adder adder = new Adder(infiles[i], outfiles[i]);
					es.submit(adder);

				}
				try {
					es.shutdown();
					es.awaitTermination(60, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

	}

class Adder implements Runnable
	{
		private String inFile, outFile;

		public Adder(String inFile, String outFile)
			{
				this.inFile = inFile;
				this.outFile = outFile;
			}

		public void doAdd() throws IOException
			{
				int total = 0;
				String line = null;
				try (BufferedReader br = Files.newBufferedReader(Paths.get(inFile))) {
					while (null != (line = br.readLine())) {
						total += Integer.parseInt(line);
					}
				}

				try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFile), null)) {
					bw.write("Total " + total);
				}
			}

		@Override
		public void run()
			{

				try {
					doAdd();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
