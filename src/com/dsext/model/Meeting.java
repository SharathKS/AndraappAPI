package com.dsext.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pranav on 11/5/15.
 */
public class Meeting implements Serializable {

	private static final long serialVersionUID = -4203426543596157065L;

	String meeting_id;

    String owner_user_id;

    String subject, description;

    String location;

    Date createdOn, startDate, endDate;
    Timestamp created_on, start_date, end_date;
    public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	String duration;
    
    Timestamp create;

//    private boolean isFollowUp;

    public Timestamp getCreate() {
		return create;
	}

	public void setCreate(Timestamp create) {
		this.create = create;
	}

	String parent_meeting_id;

    List<User> attendeeList;

    List<String> userList;

    boolean isChecked;

    public Meeting(String meeting_id, String owner_user_id, String subject, String description, String location, Date createdOn, Date startDate, Date endDate, String duration, String parent_meeting_id) {
        this.meeting_id = meeting_id;
        this.owner_user_id = owner_user_id;
        this.subject = subject;
        this.description = description;
        this.location = location;
        this.createdOn = createdOn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;

//        this.isFollowUp = isFollowUp;
        this.parent_meeting_id = parent_meeting_id;

        attendeeList = new ArrayList<User>();

        userList = new ArrayList<String>();
//        userList.add("user1");
//        userList.add("user2");
//        userList.add("user3");
//        userList.add("user4");
    }

    public void setDates() {
        this.createdOn = new Date();
        this.startDate = new Date();
        this.endDate = new Date();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<User> getAttendeeList() {
        return attendeeList;
    }

    public void setAttendeeList(List<User> attendeeList) {
        this.attendeeList = attendeeList;
    }

//    public boolean isFollowUp() {
//        return isFollowUp;
//    }
//
//    public void setFollowUp(boolean isFollowUp) {
//        this.isFollowUp = isFollowUp;
//    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

	public String getMeeting_id() {
		return meeting_id;
	}

	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public String getParent_meeting_id() {
		return parent_meeting_id;
	}

	public void setParent_meeting_id(String parent_meeting_id) {
		this.parent_meeting_id = parent_meeting_id;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
    
    
}