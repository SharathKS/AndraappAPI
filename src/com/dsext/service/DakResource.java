package com.dsext.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dsext.model.User;
import com.dsext.util.Util;

@Path("/dak")
public class DakResource {

	@SuppressWarnings("unchecked")
	@POST
	@Path("/get_DAKS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDak(User user) {
System.out.println("11");
		JSONObject obj = new JSONObject();

		try {
			Response res = Util.authenticate(user.username, user.password);
			if (res.getStatus() == 200) {
				ArrayList<String> keyWords = new ArrayList<String>();
				keyWords.add("anjali");
				keyWords.add("value entered");
				keyWords.add("value entered");
				keyWords.add("value entered");
				JSONArray jarr = new JSONArray();

				obj.put("dak_id", "DAK-35-20-2013");
				obj.put("is_important", "Yes");
				obj.put("send_bu_user_id", "Yes");
				obj.put("file_name", "Certificate.pdf");
				obj.put("file_no", "R-111-20-2013");
				obj.put("subject", "Choice Center");
				obj.put("assignment_date", "10/10/2012 11:20:20 PM");
				obj.put("category", "DAK");
				obj.put("section", "bill");
				obj.put("keywords", keyWords);
				obj.put("status", "Inactive");
				obj.put("dak_link", "Certificate.pdf");

				jarr.add(obj);

				return Response.ok(jarr, MediaType.APPLICATION_JSON).build();
			} else {
				obj.put("status", "error");
				obj.put("message", "Please Enter Username and Password");

				return Response.status(401).entity(obj).build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			return Response.status(500).entity(obj).build();
		}
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/forward_DAK")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forwardDak(User user) {

		JSONObject jobj = new JSONObject();
		try {
			
			Response res = Util.authenticate(user.username, user.password);
			System.out.println("array"+user.jarr);
			List<Map<String, String>> list = user.jarr;
			int j = 0;
			while (list.size() > j) {
				System.out.println(list.get(j));
				for (Map<String, String> map : list){
					 for (Map.Entry<String, String> entry : map.entrySet()) {
					        String key = entry.getKey();
					        Object value = entry.getValue();
					        System.out.println(entry.getKey() + "/" + entry.getValue());
					    }
				j++;
			}
			}
			/*Map<String, String> jsmap= user.json;
			for (Map.Entry<String, String> entry : jsmap.entrySet())
			{
			    
			}
*/
			if (res.getStatus() == 200) {
				jobj.put("Message", "DAK was successfully forwarded");

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

	// /home/sharath/Documents

	// Please insert the path of pdf file here
	// private static final String FILE_PATH = "../WebContent/WEB-INF/BPMN.pdf";
	// private static final String FILE_PATH =
	// System.getProperty("user.dir")+"/BPMN.pdf";
	/*
	 * private static final String FILE_PATH =
	 * "https://www.dropbox.com/s/vmj57cizvapbprs/10.1.1.146.4390.pdf?dl=0";
	 */

	@SuppressWarnings("unchecked")
	@POST
	@Path("/get_file")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/pdf")
	public Response getFile(User user) {

		JSONObject jobj = new JSONObject();
		try {
			Response auth = Util.authenticate(user.username, user.password);

			if (auth.getStatus() == 200) {
				URL url = new URL(
						"http://www.englisch-hilfen.de/en/grammar/tenses_table.pdf");

				ResponseBuilder response = Response.ok((Object) url
						.openStream());
				Response res = response.build();
				return res;
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
