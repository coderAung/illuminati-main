package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetail save(UserDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetail findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDetail> get(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetail update(UserDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetail fullUpdate(UserDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	long userid,
//	LocalDate birthDate,
//	String address,
//	String bio,
//	String phoneNumber,
//	Gender gender,
//	Relationship relationship,
//	Occupation occupation

	
	private UserDetail userdetailFrom(ResultSet rs) throws SQLException{
		var user = new UserDetail(
				rs.getTimestamp("birth_date").toLocalDateTime(),
				rs.getString("address"),
				rs.getString("bio"),
				rs.getString("phone_number"),
				Gender.valueOf(rs.getString("gender")),
				Relationship.valueOf(rs.getString("relationship")),
				Occupation.valueOf(rs.getString("occupaton"))
				);
	}

}
