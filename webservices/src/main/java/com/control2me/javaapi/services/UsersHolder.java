package com.control2me.javaapi.services;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class UsersHolder implements Serializable
{
	private static final long serialVersionId = 1L;
	
	@XmlElement(name="id")
	private List<Users> userslist;

	public List<Users> getUserslist() {
		return userslist;
	}

	public void setUserslist(List<Users> userslist) {
		this.userslist = userslist;
	}
}
