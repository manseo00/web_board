package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	
	private Connection conn;   
	private PreparedStatement pstmt;              
	private ResultSet rs; 
	

	public BbsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/ROOM";
			String dbID = "root";
			String dbPassword = "0000";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		public String getDate() {
			String sql = "select now()";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return ""; 
		}
		
	
		public int getNext() {
		
			String sql = "select bbsID from bbs order by bbsID desc";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1) + 1;
				}
				return 1; 
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		
	
		public int write(String bbsTitle, String userID, String bbsContent) {
			String sql = "insert into bbs values(?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext());
				pstmt.setString(2, bbsTitle);
				pstmt.setString(3, userID);
				pstmt.setString(4, getDate());
				pstmt.setString(5, bbsContent);
				pstmt.setInt(6, 1); 
				pstmt.setInt(7, 1); 
				pstmt.setInt(8, 1); 
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1;   
		}
	
		public ArrayList<Bbs> getList(int pageNumber){
			String sql = "select * from bbs where bbsID < ? and bbsAvailable = 1 order by bbsID desc limit 10";
			ArrayList<Bbs> list = new ArrayList<Bbs>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Bbs bbs = new Bbs();
					bbs.setBbsID(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsDate(rs.getString(4));
					bbs.setBbsContent(rs.getString(5));
					bbs.setBbsAvailable(rs.getInt(6));
					bbs.setUserIdentity(rs.getInt(7));
					bbs.setBoardID(rs.getInt(8));
					list.add(bbs);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		

		public boolean nextPage(int pageNumber) {
			String sql = "select * from bbs where bbsID < ? and bbsAvailable = 1";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		

		public Bbs getBbs(int bbsID) {
			String sql = "select * from bbs where bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bbsID);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					Bbs bbs = new Bbs();
					bbs.setBbsID(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsDate(rs.getString(4));
					bbs.setBbsContent(rs.getString(5));
					bbs.setBbsAvailable(rs.getInt(6));
					bbs.setUserIdentity(rs.getInt(7));
					bbs.setBoardID(rs.getInt(8));
					return bbs;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		

		public int update(int bbsID, String bbsTitle, String bbsContent) {
			String sql = "update bbs set bbsTitle = ?, bbsContent = ? where bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bbsTitle);
				pstmt.setString(2, bbsContent);
				pstmt.setInt(3, bbsID);
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1; 
		}
		

		public int delete(int bbsID) {

			String sql = "update bbs set bbsAvailable = 0 where bbsID = ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bbsID);
				return pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return -1; 
		}

		public ArrayList<Bbs> getSearch(String searchField, String searchText){
		      ArrayList<Bbs> list = new ArrayList<Bbs>();
		      String SQL ="select * from bbs WHERE "+searchField.trim();
		      try {
		            if(searchText != null && !searchText.equals("") ){
		                SQL +=" LIKE '%"+searchText.trim()+"%' order by bbsID desc limit 10";
		            }
		            PreparedStatement pstmt=conn.prepareStatement(SQL);
					rs=pstmt.executeQuery();//select
				while(rs.next()) {
					Bbs bbs = new Bbs();
					bbs.setBbsID(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsDate(rs.getString(4));						
					bbs.setBbsContent(rs.getString(5));
					bbs.setBbsAvailable(rs.getInt(6));
					bbs.setUserIdentity(rs.getInt(7));
					bbs.setBoardID(rs.getInt(8));
					list.add(bbs);
		         }         
		      } catch(Exception e) {
		         e.printStackTrace();
		      }
		      return list;
		   }
}
