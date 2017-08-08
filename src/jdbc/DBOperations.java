package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperations {
	private DBSource dbsource;
	
	private Connection conn = null; // Database objects
	private Statement stmt = null; // ����,�ǤJ��sql������r��
	private ResultSet rs = null; // ���G��
	// ����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m �C���Q��?�Ӱ��Х�
	private PreparedStatement preStmt = null;
	
	public DBOperations() {
		try {
        	//dbsource = new DBSource("jdbc2.properties");
            dbsource = new DBSource();
            conn = dbsource.getConnection();
            System.out.println("MySQL Connection");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	System.out.println("DriverClassNotFound :" + e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void createTable(String createdbSQL) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(createdbSQL);
		} catch (SQLException e) {
			System.out.println("CreateDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public void insertTable(String insertdbSQL, String name, int Score) {
		try {
			preStmt = conn.prepareStatement(insertdbSQL);

			preStmt.setString(1, name);
			preStmt.setInt(2, Score);
			preStmt.executeUpdate();
			preStmt.clearParameters();
		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public void dropTable(String dropdbSQL) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(dropdbSQL);
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}

	public void SelectTable(String selectSQL, Mysql_Select mysql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSQL);
			
			mysql.select(rs);
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		} finally {
			Close();
		}
	}
	
	// ����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object
	// �_�h�b����Timeout��,�i��|��Connection poor�����p
	private void Close() {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				System.out.println("Close ResultSet Exception :" + e.toString());
			}

		}
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				System.out.println("Close Statement Exception :" + e.toString());
			}

		}
		if (preStmt != null) {
			try {
				preStmt.close();
				preStmt = null;
			} catch (SQLException e) {
				System.out.println("Close PreparedStatement Exception :" + e.toString());
			}
		}
		if (conn != null) {
			try {
				dbsource.closeConnection(conn);;
				conn = null;
			} catch (SQLException e) {
				System.out.println("Close Connection Exception :" + e.toString());
			}
		}
	}
}
