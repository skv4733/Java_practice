import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class OracelJdbcTestAndFileCopies
	{
		/* Using java jdbc to connect to oracle*/
		
		String driverClass="oracle.jdbc.driver.OracleDriver";
		Connection con;
		public void init(FileInputStream fs) throws ClassNotFoundException,FileNotFoundException, IOException, SQLException
		{
				Properties props=new Properties();
				props.load(fs);
				String url=props.getProperty("db.url");
				String userName=props.getProperty("db.user");
				String password=props.getProperty("db.password");
				
				Class.forName(driverClass);
				con=DriverManager.getConnection(url, userName, password);
		}
		public void fetch() throws SQLException, IOException
		
			{
				PreparedStatement ps =con.prepareStatement("select SYSDATE from dual");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) 
				{
					//do the thing you do
				}
				rs.close();
				ps.close();
			}
		public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
			{
			/*	OracelJdbcTest t=new OracelJdbcTest();
				t.init(null);
				t.fetch();*/
				fileCopy(new File("1.jpg"), new File("2.png"));
				
			}
		
		//Using NIO to copy java file fast
		public static void fileCopy(File in, File out) throws IOException
			{ 
				FileChannel inChannel=new FileInputStream(in).getChannel();
				FileChannel outChannel=new FileOutputStream(out).getChannel();
				try {
					//magic number for windows 
					int maxCount=(64*1024*1024)-(32*1024);
					long size=inChannel.size();
					System.out.println("In channel default system size"+size);
					long position=0;
					while(position<size)
					{
						position+=inChannel.transferTo(position, maxCount, outChannel);
					}
					
					
				} finally{
					if (inChannel!=null) {
						inChannel.close();
					}
					if (outChannel!=null) {
						outChannel.close();
					}
					
				}
				
			}
	}



