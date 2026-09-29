package org.example.data;

import java.sql.*;
import org.example.util.ConnectionPool;
import org.example.util.DBUtil;

public class UserDB {

    // Phương thức kiểm tra xem email đã tồn tại trong bảng User chưa
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Lưu ý: với PostgreSQL nhớ bọc tên bảng User trong ngoặc kép " "
        String query = "SELECT Email FROM \"User\" WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            // Nếu rs.next() trả về true, nghĩa là có ít nhất 1 dòng trùng email này
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}