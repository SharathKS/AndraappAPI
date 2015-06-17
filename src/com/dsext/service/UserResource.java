package com.dsext.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dsext.model.User;
import com.dsext.util.Constant;
import com.dsext.util.Util;

@Path("/user")
public class UserResource {
	Connection conn;

	@SuppressWarnings({ "unchecked" })
	@POST
	@Path("/get_users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUser(User user) {

		JSONArray jsonArray = new JSONArray();
		try {
			Class.forName(Constant.driver).newInstance();
			conn = DriverManager.getConnection(Constant.url + Constant.dbName,
					Constant.userName, Constant.password);

			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM user ORDER BY name");

			ResultSet res = statement.executeQuery();
			JSONObject jsonObj = null;
			while (res.next()) {
				jsonObj = new JSONObject();
				System.out.println("while : " + res.getRow());
				jsonObj.put("user_id", res.getString("id"));
				jsonObj.put("name", res.getString("name"));
				jsonObj.put("email", res.getString("email"));
				jsonObj.put("designation", res.getString("designation"));
				jsonObj.put("department", res.getString("department"));
				jsonObj.put("phone", res.getString("phone"));
System.out.println(jsonObj);
				jsonArray.add(jsonObj);
				System.out.println(jsonArray);
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
//		System.out.println(jsonArray);
		
		return jsonArray;
	}


//	@SuppressWarnings("unchecked")
//	@POST
//	@Path("/get_all_users")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllUsers(User user) {
//		JSONArray jarr = new JSONArray();
//		JSONArray userArr = new JSONArray();
//		JSONObject userObject = new JSONObject();
//		JSONObject userObject1 = new JSONObject();
//
//		try {
//			Response res = Util.authenticate(user.username, user.password);
//
//			JSONObject obj = new JSONObject();
//			JSONObject obj1 = new JSONObject();
//			if (res.getStatus() == 200) {
//				obj.put("dept_id", "1");
//				obj.put("department_name", "CHiPS");
//				obj.put("department_users", userArr);
//
//				obj1.put("dept_id", "2");
//				obj1.put("department_name", "Training Dept");
//				obj1.put("department_users", userArr);
//
//				userObject.put("user_id", "12345");
//				userObject.put("name", "Albert");
//				userObject.put("designation", "MD");
//				userObject.put("department_code", "300");
//
//				userObject1.put("user_id", "12345");
//				userObject1.put("name", "Tom");
//				userObject1.put("designation", "MD");
//				userObject1.put("department_code", "300");
//
//				userArr.add(userObject);
//				userArr.add(userObject1);
//
//				jarr.add(obj);
//				jarr.add(obj1);
//				return Response.ok(jarr, MediaType.APPLICATION_JSON).build();
//			} else {
//				obj.put("status", "error");
//				obj.put("message", "Please Enter Username and Password");
//
//				return Response.status(401).entity(obj).build();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			return Response.status(500).entity(jarr).build();
//		}
//
//	}
}
