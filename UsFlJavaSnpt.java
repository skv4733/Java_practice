import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class UsFlJavaSnpt
	{
		public static void main(String[] args) throws Exception
			{
				/*
				 * String currentMethodName =
				 * Thread.currentThread().getStackTrace()[1].getMethodName();
				 * System.out.println(currentMethodName);
				 */

				/* Convert A string to date */
				/*
				 * String sDate1="31/12/1998"; Date date1=new
				 * SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
				 * System.out.println(sDate1+"\t"+date1); // String sDate1="31/12/1998"; String
				 * sDate2 = "31-Dec-1998"; String sDate3 = "12 31, 1998"; String sDate4 =
				 * "Thu, Dec 31 1998"; String sDate5 = "Thu, Dec 31 1998 23:37:50"; String
				 * sDate6 = "31-Dec-1998 23:37:50"; SimpleDateFormat formatter1=new
				 * SimpleDateFormat("dd/MM/yyyy"); SimpleDateFormat formatter2=new
				 * SimpleDateFormat("dd-MMM-yyyy"); SimpleDateFormat formatter3=new
				 * SimpleDateFormat("MM dd, yyyy"); SimpleDateFormat formatter4=new
				 * SimpleDateFormat("E, MMM dd yyyy"); SimpleDateFormat formatter5=new
				 * SimpleDateFormat("E, MMM dd yyyy HH:mm:ss"); SimpleDateFormat formatter6=new
				 * SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); // Date
				 * date1=formatter1.parse(sDate1); Date date2=formatter2.parse(sDate2); Date
				 * date3=formatter3.parse(sDate3); Date date4=formatter4.parse(sDate4); Date
				 * date5=formatter5.parse(sDate5); Date date6=formatter6.parse(sDate6);
				 * System.out.println(sDate1+"\t"+date1); System.out.println(sDate2+"\t"+date2);
				 * System.out.println(sDate3+"\t"+date3); System.out.println(sDate4+"\t"+date4);
				 * System.out.println(sDate5+"\t"+date5); System.out.println(sDate6+"\t"+date6);
				 */
				/*
				 * File directory=new File("C:/Program Files"); String[]
				 * substr=directory.list(); Arrays.stream(substr).forEach(System.out::println);
				 */

			}

		/*
		 * Generic static initlizalier public static String[] strings; public static int
		 * index = 60; static {
		 * 
		 * strings = new String[60]; Arrays.stream(strings).forEach(new1->new1=null);
		 * Arrays.stream(strings).forEach(System.out::println);
		 * 
		 * }
		 */
		public void takeScreenshot(String file) throws Exception
			{
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle rect = new Rectangle(size);
				Robot robot = new Robot();
				BufferedImage image = robot.createScreenCapture(rect);
				ImageIO.write(image, "png", new File(file));
			}
	}
