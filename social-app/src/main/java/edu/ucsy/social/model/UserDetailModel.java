package edu.ucsy.social.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.social.model.entity.UserDetail.Gender;
import edu.ucsy.social.model.entity.UserDetail.Occupation;
import edu.ucsy.social.model.entity.UserDetail.Relationship;
import edu.ucsy.social.utils.StringTool;

public class UserDetailModel extends AbstractModel<UserDetail>{
	
	@Override
	public UserDetail save(UserDetail ud) {
		var sql = """
				insert into user_details (user_id, birth_date, address, bio, phone_number, gender, relationship, occupation)
				 values(?, ?, ?, ?, ?, ?, ?, ?)
				""";
		
		
		try(var stmt = connection.prepareStatement(sql)
				){
			
			stmt.setLong(1, ud.userId());
		
			if (null != ud.birthDate()) {
			    var birthDate = Date.valueOf(ud.birthDate());
			    stmt.setDate(2, birthDate);
			} else {
			    stmt.setDate(2, null);
			}
				stmt.setString(3, ud.address());
				stmt.setString(4, ud.bio());
				stmt.setString(5, ud.phoneNumber());
			
			if(null != ud.gender()) {
				stmt.setInt(6, ud.gender().ordinal() + 1);
			}else {
				stmt.setInt(6, Gender.Others.ordinal() + 1);
			}
				
			if(null != ud.relationship()) {
				stmt.setInt(7, ud.relationship().ordinal() + 1);
			}else {
				stmt.setObject(7, null);
			}
				
			if(null != ud.occupation()) {
				stmt.setInt(8, ud.occupation().ordinal() + 1);
			}else {
				stmt.setObject(8, null);
			}
			

			var row = stmt.executeUpdate();
			if(row == 0 ) {
				return null;
			}
			return ud;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public UserDetail findOne(long id) {
		var sql = "select * from user_details where user_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
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
		try(var stmt = connection.prepareStatement(sql)) {
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
		try(var stmt = connection.prepareStatement(sql)){
			
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
		var sql = "select * from user_details where user_id = ?";
		try(var stmt = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, ud.userId());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				var userDetail = userdetailFrom(rs);
				if(null != ud.birthDate() && !ud.birthDate().equals(userDetail.birthDate())) {
					rs.updateDate("birth_date", Date.valueOf(ud.birthDate()));
				}
				
				if(null != ud.address() && !ud.address().equals(userDetail.address())) {
					rs.updateString("address", ud.address());
				}
				
				if(null != ud.bio() && !ud.bio().equals(userDetail.bio())) {
					rs.updateString("bio", ud.bio());
				}
				
				if(null != ud.phoneNumber() && !ud.phoneNumber().equals(userDetail.phoneNumber())) {
					rs.updateString("phone_number", ud.phoneNumber());
				}
				
				if(null != ud.gender() && !ud.gender().equals(userDetail.gender())) {
					rs.updateString("gender", ud.gender().name());
				}
				
				if(null != ud.relationship() && !ud.relationship().equals(userDetail.relationship())) {
					rs.updateString("relationship", ud.relationship().name());
				}
				
				if(null != ud.occupation() && !ud.occupation().equals(userDetail.occupation())) {
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
		try (var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
	
//	public boolean deleteOne(long id, String... cols) {
//		var sql = "delete %s from user_details where id = ?";
//		var columns = StringTool.joinWithComma(cols);
//		
//		sql = sql.formatted(columns);
//		try(var conn = connector.getConnection();
//				var stmt = conn.prepareStatement(sql)) {
//			stmt.setLong(1, id);
//			var row  = stmt.executeUpdate();
//			if(row == 0) {
//				return false;
//			}
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	private UserDetail userdetailFrom(ResultSet rs) throws SQLException {
		LocalDate birthDate = null;
		Gender gender = null;
		Relationship relationship = null;
		Occupation occupation = null;
		
		if(null != rs.getDate("birth_date")) {
			birthDate = rs.getDate("birth_date").toLocalDate();
		}
		
		if(null != rs.getString("gender")) {
			gender = Gender.valueOf(rs.getString("gender"));
		}
		
		if(null != rs.getString("relationship")) {
			relationship = Relationship.valueOf(rs.getString("relationship"));
		}
		
		if(null != rs.getString("occupation")) {
			occupation = Occupation.valueOf(rs.getString("occupation"));
		}
		
		var userdetail = new UserDetail(
				rs.getLong("user_id"),
				birthDate,
				rs.getString("address"),
				rs.getString("bio"),
				rs.getString("phone_number"),
				gender,
				relationship,
				occupation
				);
		return userdetail;
	}
	

}
