package com.dsext.service;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONObject;

import com.dsext.model.Meeting;
import com.dsext.model.User;
import com.dsext.util.Constant;
import com.dsext.util.Util;

@Path("/meeting")
public class MeetingResource {
	Connection conn;

	@SuppressWarnings("unchecked")
	@GET
	@Path("/get_meetings")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object getMeetings(@QueryParam("userId") String userId) {

		System.out.println("check  :  " + userId.length());

		String url = "jdbc:mysql://localhost/";

		String dbName = "andrapp";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";

		JSONArray jsonArray = new JSONArray();
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName+"?zeroDateTimeBehavior=convertToNull", userName, password);

			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM meetings m LEFT OUTER JOIN meetingUserMap mup ON m.meeting_id = mup.meeting_id WHERE m.owner_user_id = ? OR mup.user_id = ? ORDER BY m.created_on");
			statement.setString(1, userId);
			statement.setString(2, userId);

			ResultSet res = statement.executeQuery();
			JSONObject jsonObj = null;
			// jsonObj.
			while (res.next()) {
				int flag = 0;

				// for (int i = jsonArray.length() - 1; i >= 0; i--) {
				// if (jsonArray.getJSONObject(i).getString("meeting_id")
				// .equalsIgnoreCase(res.getString("meeting_id"))) {
				// jsonArray.getJSONObject(i).getJSONArray("userList")
				// .put(res.getString("user_id"));
				// break;
				// }
				// }

				for (int i = jsonArray.size() - 1; i >= 0; i--) {
					if (((String) ((JSONObject) jsonArray.get(i))
							.get("meeting_id")).equalsIgnoreCase(res
							.getString("meeting_id"))) {
						((List<String>) ((JSONObject) jsonArray.get(i))
								.get("userList")).add(res.getString("user_id"));
						System.out.println("break");
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					continue;
				}

				System.out.println("while continued" + res.getRow());
				jsonObj = new JSONObject();
				// System.out.println("while  : " + res.getRow());
				jsonObj.put("meeting_id", res.getString("meeting_id"));
				jsonObj.put("owner_user_id", res.getString("owner_user_id"));
				jsonObj.put("subject", res.getString("subject"));
				jsonObj.put("description", res.getString("description"));
				jsonObj.put("location", res.getString("location"));
				jsonObj.put("created_on", res.getTimestamp("created_on"));
				jsonObj.put("start_date", res.getTimestamp("start_date"));
				jsonObj.put("end_date", res.getTimestamp("end_date"));
				jsonObj.put("duration", res.getString("duration"));
				jsonObj.put("parent_meeting_id",
						res.getString("parent_meeting_id"));
				JSONArray aa = new JSONArray();
				aa.add(res.getString("user_id"));
				jsonObj.put("userList", aa);

				jsonArray.add(jsonObj);
				System.out.println("while last" + res.getRow());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(jsonArray);
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/create_meeting")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object createMeeting(Meeting meeting) {

		JSONArray jsonArray = new JSONArray();
		try {
			Class.forName(Constant.driver).newInstance();
			conn = DriverManager.getConnection(Constant.url + Constant.dbName,
					Constant.userName, Constant.password);

			// System.out.println("asdasd" + meeting.getSubject());
			// // TODO : make duration nullable in DB
			// Statement stmt = conn.createStatement();
			String uuid = UUID.randomUUID().toString();

			/*PreparedStatement statement = conn
					.prepareStatement("INSERT into meetings(meeting_id, subject, description, location, owner_user_id, duration) values (UUID(),?,?,?,?,?)");*/
			PreparedStatement statement = conn
					.prepareStatement("INSERT into meetings(meeting_id, subject, description, location, owner_user_id, duration) values (?,?,?,?,?,?)");
			statement.setString(1, uuid);
			statement.setString(2, meeting.getSubject());
			statement.setString(3, meeting.getDescription());
			statement.setString(4, meeting.getLocation());
			statement.setString(5, meeting.getOwner_user_id());
			statement.setString(6, meeting.getDuration());

			int x = statement.executeUpdate();
			String meet = meeting.getMeeting_id();
			
			

			System.out.println("MeetingID::::" + uuid);

			System.out.println("x  :  " + x);

			// if (x == 0) {
			// throw new
			// SQLException("Creating user failed, no rows affected.");
			// }

			/*
			 * try (ResultSet generatedKeys = statement.getGeneratedKeys()) { if
			 * (generatedKeys.next()) {
			 * System.out.println(generatedKeys.getInt(1)); //
			 * System.out.println(generatedKeys.getLong(1)); //
			 * user.setId(generatedKeys.getLong(1)); } else { throw new
			 * SQLException( "Creating user failed, no ID obtained."); } }
			 */

			if(x==1){
			for (int i = 0; i < meeting.getUserList().size(); i++) {
				System.out.println("lopol" + meeting.getUserList().get(i));
				String userID = meeting.getUserList().get(i);
				PreparedStatement statementMeet = conn
						.prepareStatement("INSERT into meetingUserMap(id, meeting_id, user_id) values (UUID(),?,?)");
				statementMeet.setString(1, uuid);
				statementMeet.setString(2, userID);
				int y = statementMeet.executeUpdate();
				
				System.out.println("y  :  " + y);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonArray;
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/meeting_response")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response meetingResponse(User user) {
		JSONObject jobj = new JSONObject();

		try {
			Response res = Util.authenticate(user.username, user.password);
			if (res.getStatus() == 200) {
				jobj.put("status", "success");
				jobj.put("message",
						"Your response for the meeting was successfully sent");

				return Response.ok(jobj, MediaType.APPLICATION_JSON).build();
			} else {
				jobj.put("status", "error");
				jobj.put("message", "Please Enter Username and Password");

				return Response.status(401).entity(jobj).build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			return Response.status(500).entity(jobj).build();
		}
	}

}
