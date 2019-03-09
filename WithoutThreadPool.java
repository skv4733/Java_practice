import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author sandip
 *
 */
public class WithoutThreadPool
	{
		public static void main(String[] args) throws IOException
			{
				String[] infiles = {};
				String[] outfiles = {};
				Thread[] threads = new Thread[infiles.length];

				for (int i = 0; i < infiles.length; i++) {
					Adder adder = new Adder(infiles[i], outfiles[i]);
					threads[i] = new Thread(adder);
					threads[i].start();
				}
				for (Thread thread : threads) {
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
