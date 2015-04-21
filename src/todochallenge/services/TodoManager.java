package todochallenge.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todochallenge.db.DBPool;
import todochallenge.dto.Todo;

public class TodoManager {

	public static Todo getTodo(int id){
		String sql = "SELECT * FROM todo WHERE idtodo=?;";
		Todo t = new Todo();
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				t.setIdtodo(rs.getInt("idtodo"));
				t.setNote(rs.getString("note"));
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static Todo getFirstTodo(){
		String sql = "SELECT * FROM todo WHERE status='PENDING' LIMIT 1;";
		Todo t = new Todo();
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				t.setIdtodo(rs.getInt("idtodo"));
				t.setNote(rs.getString("note"));
			}else{
				t.setNote("No notes");
				t.setIdtodo(-1);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static Todo getNextTodo(int id){
		String sql = "SELECT * FROM todo WHERE status='PENDING' and idtodo>? LIMIT 1;";
		Todo t = new Todo();
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				t.setIdtodo(rs.getInt("idtodo"));
				t.setNote(rs.getString("note"));
			}else{
				t = getTodo(id);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static Todo getPreviousTodo(int id){
		String sql = "SELECT * FROM todo WHERE status='PENDING' and idtodo<? ORDER BY idtodo DESC LIMIT 1;";
		Todo t = new Todo();
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				t.setIdtodo(rs.getInt("idtodo"));
				t.setNote(rs.getString("note"));
			}else{
				t = getTodo(id);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static void addTodo(String note){
		String sql = "INSERT INTO todo (note, status) VALUES (?,'PENDING')";
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, note);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void doneTodo(int id){
		String sql = "UPDATE todo SET STATUS='DONE' WHERE idtodo=?;";
		Connection conn = DBPool.getInstance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
