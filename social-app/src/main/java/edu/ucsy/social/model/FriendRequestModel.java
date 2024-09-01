package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.utils.StringTool;

public class FriendRequestModel extends AbstractModel<FriendRequest> {

	@Override
	public FriendRequest save(FriendRequest fr) {
		var sql = """
				insert into friend_requests(request_to, request_by) values(?, ?)
				""";

		try (var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, fr.requestTo());
			stmt.setLong(2, fr.requestBy());

			var row = stmt.executeUpdate();
			if (row == 0) {
				return null;
			}

			var keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				var friendRequest = fr.perfectClone(keys.getLong(1));
				return friendRequest;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FriendRequest findOne(long id) {
		var sql = "select fr.request_by as id , u.name as username , pi.name as profile from friend_requests as fr join users as u on fr.request_by = u.id join profile_images as pi on u.id = pi.user_id where id = ?";
		try (var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {

				return friendrequestFrom(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from friend_requests where id = ?";
		var columns = StringTool.joinWithComma(cols);

		sql = sql.formatted(columns);
		try (var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FriendRequest> getAll() {
		var sql = "select fr.request_by as id , u.name as username , pi.name as profile from friend_requests as fr join users as u on fr.request_by = u.id join profile_images as pi on u.id = pi.user_id";
		try (var stmt = connection.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var friendrequests = new ArrayList<FriendRequest>();

			while (rs.next()) {
				var friendrequest = friendrequestFrom(rs);
				friendrequests.add(friendrequest);
			}
			return friendrequests;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FriendRequest> get(long limit) {
		var sql = "select fr.request_by as id , u.name as username , pi.name as profile from friend_requests as fr join users as u on fr.request_by = u.id join profile_images as pi on u.id = pi.user_id limit ?";
		try (var stmt = connection.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var friendrequests = new ArrayList<FriendRequest>();

			while (rs.next()) {
				var friendrequest = friendrequestFrom(rs);
				friendrequests.add(friendrequest);
			}
			return friendrequests;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FriendRequest update(FriendRequest fr) {
		return null;
	}

	@Override
	public FriendRequest fullUpdate(FriendRequest fr) {
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from friend_requests where id = ?";
		try (var stmt = connection.prepareStatement(sql)) {
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

	public FriendRequest friendrequestFrom(ResultSet rs) throws SQLException {
		var fr = new FriendRequest(rs.getLong("id"), 
				rs.getLong("request_to"),
				rs.getLong("request_by"));
		return fr;

	}

}
