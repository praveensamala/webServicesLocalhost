package com.control2me.javaapi.services;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class UsersHolder implements Serializable
{
	private static final long serialVersionId = 1L;
	
	@XmlElement(name="id")
	private List<User> userslist;

	public List<User> getUserslist() {
		return userslist;
	}

	public void setUserslist(List<User> userslist) {
		this.userslist = userslist;
	}
}
