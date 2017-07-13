package com.yuyoubang.activity.sort;

public class SortModel {

	private String name;
	private String sortLetters;
	private long id;

	private String avatarUrl;
	private long age;
	private String location;
	private long birthday;
	private String gender;

	private String teamUrl;
	private String teamName;
	private String teamMems;
	private String group_id;

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getTeamUrl() {
		return teamUrl;
	}

	public void setTeamUrl(String teamUrl) {
		this.teamUrl = teamUrl;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamMems() {
		return teamMems;
	}

	public void setTeamMems(String teamMems) {
		this.teamMems = teamMems;
	}

	public int getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(int isChoose) {
		this.isChoose = isChoose;
	}

	private int isChoose;
	private int isSelector;

	public int getIsSelector() {
		return isSelector;
	}

	public void setIsSelector(int isSelector) {
		this.isSelector = isSelector;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Long pid;

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
