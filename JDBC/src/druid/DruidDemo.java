package druid;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/*
 * Druid数据库连接池演示
 * */
public class DruidDemo {

	public static void main(String[] args) throws Exception {
		//1.导入jar包
		
		//2.定义配置文件
		
		//3.加载配置文件
		Properties prop = new Properties();
		prop.load(new FileInputStream("src\\druid.properties"));
		
		//4.获取连接池对象
		DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
		
		//5.获取数据库连接Connection
		Connection connection = dataSource.getConnection();
		
		System.out.println(connection);

	}

}
