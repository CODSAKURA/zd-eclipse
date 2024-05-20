package example;

import pojo.Brand;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 品牌数据的增删改查操作
 * */
public class BrandTest {

	/**
	 * 查询所有数据
	 * */
	@Test
	public void testSelectAll() throws Exception {

		/*获取Connection(用Druid)*/
		//1.导入jar包
		//2.定义配置文件
		//3.加载配置文件
		Properties pr = new Properties();
		pr.load(new FileInputStream("src//druid.properties"));
		//4.获取数据库连接池对象
		DataSource ds = DruidDataSourceFactory.createDataSource(pr);
		//5.获取连接
		Connection conn = ds.getConnection();
		
		/*定义SQL*/
		String sql = "select * from tb_brand";
		
		/*获取PreperadStatement对象*/
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		/*设置参数值（无）*/
		
		/*使用事务执行SQL，获取并处理结果*/
		ResultSet rs = null;
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			//执行SQL，获取结果
			rs = pstmt.executeQuery();
			
			//处理结果
			ArrayList<Brand> bl = new ArrayList<Brand>();
			Brand b = null;
			
			while(rs.next()) {	
				//创建一个Brand实体
				b = new Brand();
				b.setId(rs.getInt(1));
				b.setBrandName(rs.getString(2));
				b.setCompanyName(rs.getString(3));
				b.setOrdered(rs.getInt(4));
				b.setDescription(rs.getString(5));
				b.setStatus(rs.getInt(6));
				
				//将实体加入到Arraylist中
				bl.add(b);
			}
			System.out.println(bl);
			
			//提交事务
			conn.commit();
		}catch(Exception ex) {
			conn.rollback();
			ex.printStackTrace();
		}
		/*释放资源*/
		rs.close();
		pstmt.close();
		conn.close();
	}

	/**
	 * 添加数据
	 * */
	@Test
	public void testAdd() throws Exception {

		/*获取Connection(用Druid)*/
		//1.导入jar包
		//2.定义配置文件
		//3.加载配置文件
		Properties pr = new Properties();
		pr.load(new FileInputStream("src//druid.properties"));
		//4.获取数据库连接池对象
		DataSource ds = DruidDataSourceFactory.createDataSource(pr);
		//5.获取连接
		Connection conn = ds.getConnection();
		
		/*定义SQL*/
		String brandName = "aaa";
		String companyName = "bbb";
		int ordered = 22;
		String description = "ccc";
		int status = 0;
		String sql = "Insert into tb_brand(brand_name,company_name,ordered,description,status) values(?,?,?,?,?)";
		
		/*获取PreperadStatement对象*/
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		/*设置参数值*/
		pstmt.setString(1, brandName);
		pstmt.setString(2, companyName);
		pstmt.setInt(3, ordered);
		pstmt.setString(4, description);
		pstmt.setInt(5, status);
		
		/*使用事务执行SQL，检查结果成功与否*/
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			//执行SQL，检查结果成功与否
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print("Success~");
			}else {
				System.out.print("Fail~");
			}
			
			//提交事务
			conn.commit();
		}catch(Exception ex) {
			conn.rollback();
			ex.printStackTrace();
		}
		/*释放资源*/
		pstmt.close();
		conn.close();
	}
		
	/**
	 * 修改数据
	 * */
	@Test
	public void testUpdate() throws Exception {

		//模拟：所需要修改的数据id和域
		int id = 2;
		String brandName = "测试";
		String companyName = "dsad";
		int ordered = 60;
		String description = "努力上进";
		int status = 0;
		
		
		/*获取Connection(用Druid)*/
		//1.导入jar包
		//2.定义配置文件
		//3.加载配置文件
		Properties pr = new Properties();
		pr.load(new FileInputStream("src//druid.properties"));
		//4.获取数据库连接池对象
		DataSource ds = DruidDataSourceFactory.createDataSource(pr);
		//5.获取连接
		Connection conn = ds.getConnection();
		
		/*定义SQL*/
		String sql = "update tb_brand set brand_name = ?, company_name= ?, ordered = ?, description = ?, status = ? where id = ?";
		
		/*获取PreperadStatement对象*/
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		/*设置参数值*/
		pstmt.setString(1, brandName);
		pstmt.setString(2, companyName);
		pstmt.setInt(3, ordered);
		pstmt.setString(4, description);
		pstmt.setInt(5, status);
		pstmt.setInt(6, id);
		
		/*使用事务执行SQL，检查结果成功与否*/
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			//执行SQL，检查结果成功与否
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print("Success~");
			}else {
				System.out.print("Fail~");
			}
			
			//提交事务
			conn.commit();
		}
		catch(Exception ex) 
		{
			conn.rollback();
			ex.printStackTrace();
		}
		/*释放资源*/
		pstmt.close();
		conn.close();
	}
	
	
	/**
	 * 删除数据
	 * */
	@Test
	public void testDelete() throws Exception {

		//模拟：所需要删除的数据id
		int id = 2;		
		
		/*获取Connection(用Druid)*/
		//1.导入jar包
		//2.定义配置文件
		//3.加载配置文件
		Properties pr = new Properties();
		pr.load(new FileInputStream("src//druid.properties"));
		//4.获取数据库连接池对象
		DataSource ds = DruidDataSourceFactory.createDataSource(pr);
		//5.获取连接
		Connection conn = ds.getConnection();
		
		/*定义SQL*/
		String sql = "delete from tb_brand where id = ?";
		
		/*获取PreperadStatement对象*/
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		/*设置参数值*/
		pstmt.setInt(1, id);
		
		/*使用事务执行SQL，检查结果成功与否*/
		try {
			//开启事务
			conn.setAutoCommit(false);
			
			//执行SQL，检查结果成功与否
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.print("Success~");
			}else {
				System.out.print("Fail~");
			}
			
			//提交事务
			conn.commit();
		}
		catch(Exception ex) 
		{
			conn.rollback();
			ex.printStackTrace();
		}
		/*释放资源*/
		pstmt.close();
		conn.close();
	}
}
