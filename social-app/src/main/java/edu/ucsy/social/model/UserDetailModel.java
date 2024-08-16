package edu.ucsy.social.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;
import edu.ucsy.social.utils.StringTool;

public class UserDetailModel extends AbstractModel<UserDetail>{

	public UserDetailModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public UserDetail save(UserDetail ud) {
		var sql = """
				insert into user_details (userid, birth_date, address, bio, phone_number, gender, relationship, occupation)
				 values(?, ?, ?, ?, ?, ?, ?, ?)
				""";
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)
				){
			var birthDate = Date.valueOf(LocalDate.now());
			
				stmt.setDate(1, birthDate);
				stmt.setString(2, ud.address());
				stmt.setString(3, ud.bio());
				stmt.setString(4, ud.phoneNumber());
				stmt.setInt(5, ud.gender().ordinal() + 1);
				stmt.setInt(6, ud.relationship().ordinal() + 1);
				stmt.setInt(7, ud.occupation().ordinal() + 1);
			
			var row = stmt.executeUpdate();
			if(row == 0 ) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public UserDetail findOne(long id) {
		var sql = "select * from userdetails where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return userdetailFrom(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from user_details where id = ?";
		var columns = StringTool.joinWithComma(cols);
		
		sql = sql.formatted(columns);
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDetail> getAll() {
		var sql ="select * from user_details";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)){
			
			var rs = stmt.executeQuery();
			var userdetails = new ArrayList<UserDetail>();
			while(rs.next()) {
				var userdetail = userdetailFrom(rs);
				userdetails.add(userdetail);
			}
			return userdetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDetail> get(long limit) {
		return null;
	}

	@Override
	public UserDetail update(UserDetail ud) {
		var sql = "select * from user_details where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, ud.userid());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				if(!ud.birthDate().equals(rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate())) {
					var updatedAt = Timestamp.valueOf(LocalDateTime.now());
					rs.updateTimestamp("birth_date", updatedAt);	
				}
				
				if(!ud.address().equals(rs.getString("address"))) {
					rs.updateString("address", ud.address());
				}
				
				if(!ud.bio().equals(rs.getString("bio"))) {
					rs.updateString("address", ud.address());
				}
				
				if(!ud.phoneNumber().equals(rs.getString("phone_number"))) {
					rs.updateString("phone_number", ud.phoneNumber());
				}
				
				if(!ud.gender().equals(Gender.valueOf(rs.getString("gender")))) {
					rs.updateString("gender", ud.gender().name());
				}
				
				if(!ud.relationship().equals(Relationship.valueOf(rs.getString("relationship")))) {
					rs.updateString("relationship", ud.relationship().name());
				}
				
				if(!ud.occupation().equals(Gender.valueOf(rs.getString("occupation")))) {
					rs.updateString("occupation", ud.occupation().name());
				}
				
				rs.updateRow();
		}
			return ud;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDetail fullUpdate(UserDetail ud) {
		var update1 = update(ud);
		return update1;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from user_details where id = ?";
		try (var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if (row == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteOne(long id, String... cols) {
		var sql = "select %s from user_details where id = ?";
		var columns = StringTool.joinWithComma(cols);
		
		sql = sql.formatted(columns);
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row  = stmt.executeUpdate();
			if(row == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private UserDetail userdetailFrom(ResultSet rs) throws SQLException {
		var userdetail = new UserDetail(
				rs.getLong("user_id"),
				rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate(),
				rs.getString("address"),
				rs.getString("bio"),
				rs.getString("phone_number"),
				Gender.valueOf(rs.getString("gender")),
				Relationship.valueOf(rs.getString("relationship")),
				Occupation.valueOf(rs.getString("occupation"))
				);
		return userdetail;
	}
	

}
