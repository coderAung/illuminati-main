package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;

public class UserDetailModel extends AbstractModel<UserDetail>{

	public UserDetailModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public UserDetail save(UserDetail ud) {
		var sql = """
				insert into user_details (birth_date, address, bio, phone_number, gender, relationship, occupation)
				 values(?, ?, ?, ?, ?, ?, ?)
				""";
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)
				){
			
			var birthDate = Timestamp.valueOf(LocalDateTime.now());
			
			if(!birthDate.equals(null)) {
				stmt.setTimestamp(1, birthDate);
			}else {
				return null;
			}
			
			if(!ud.address().equals(null)) {
				stmt.setString(2, ud.address());
			}else {
				return null;
			}
			
			if(!ud.bio().equals(null)) {
				stmt.setString(3, ud.bio());
			}else {
				return null;
			}
			
			if(!ud.phoneNumber().equals(null)) {
				stmt.setString(4, ud.phoneNumber());
			}else {
				return null;
			}
			
			if(!ud.gender().equals(null)) {
				stmt.setInt(5, ud.gender().ordinal() + 1);
			}else {
				return null;
			}
			
			if(!ud.relationship().equals(null)) {
				stmt.setInt(6, ud.relationship().ordinal() + 1);
			}else {
				return null;
			}
			
			if(!ud.occupation().equals(null)) {
				stmt.setInt(7, ud.occupation().ordinal() + 1);
			}else {
				return null;
			}
			
			var row = stmt.executeUpdate();
			if(row == 0 ) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
					
		return null;
	}

	@Override
	public UserDetail findOne(long id) {
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		return null;
	}

	@Override
	public List<UserDetail> getAll() {
		return null;
	}

	@Override
	public List<UserDetail> get(long limit) {
		return null;
	}

	@Override
	public UserDetail update(UserDetail ud) {
		return null;
	}

	@Override
	public UserDetail fullUpdate(UserDetail ud) {
		return null;
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
	
	

}
