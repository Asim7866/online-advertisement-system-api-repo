package com.cg.onlineadvapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@NotBlank(message="name cannot be left empty")
	@Pattern(regexp = "^[a-zA-Z ]*$", message="User name should only contain alphabets")
	private String name;
	@NotBlank(message="Login name cannot be left blank")
	@Column(unique=true) //this should be updatable = false
	private String loginName;
	@Size(min = 8, max = 12, message = "Password should be greater than 7 and less than 13 characters")
	private String password;
	@Size(min = 8, max = 12, message = "Password should be greater than 7 and less than 13 characters")
	private String confirmPassword;
	@Size(min = 10, max = 10, message="Contact number should consist 10 digits")
	@Pattern(regexp= "[0-9]+", message="Contact number should only contain numeric value")
	private String contactNo;
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Email is Incorrect")
	private String email;
	@Column(updatable = false)
	private Integer role;
	@Embedded
	private Address address;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="user_id" ,referencedColumnName = "userId")
	private List<Advertise> advertise=new ArrayList<>();

	public User() {
		super();
	}
	
	
	

	public User( String loginName, String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}




	public User(String name, String loginName, String password, String confirmPassword, String contactNo, String email, Integer role) {
		super();
		this.name = name;
		this.loginName = loginName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contactNo = contactNo;
		this.email = email;
		this.role = role;
	}


	
	public User(
			@NotBlank(message = "name cannot be left empty") @Pattern(regexp = "^[a-zA-Z ]*$", message = "User name should only contain alphabets") String name,
			@Size(min = 10, max = 10, message = "Contact number should consist 10 digits") @Pattern(regexp = "[0-9]+", message = "Contact number should only contain numeric value") String contactNo,
			@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email is Incorrect") String email, Integer role) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.email = email;
		this.role = role;
	}

	public User(@NotBlank(message = "Login name cannot be left blank") String loginName,
			@Size(min = 8, max = 33, message = "Password should be greater than 7 and less than 13 characters") String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "User [name=" + name + ", loginName=" + loginName + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", contactNo=" + contactNo + ", email=" + email + ", role=" + role + "]";
	}
	
	
	
}