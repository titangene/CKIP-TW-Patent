package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Mysql_Insert {
	void insert(PreparedStatement preStmt) throws SQLException;
}
