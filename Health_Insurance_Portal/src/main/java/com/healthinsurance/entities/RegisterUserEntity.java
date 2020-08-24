package com.healthinsurance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "Create_user")
public class RegisterUserEntity {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Column(name = "role")
	private String role;
	@Column(name = "pazzword")
	private String pazzword;
	@Column(name = "lock_status")
	private String lockStatus;
	@Column(name = "account_action")
	private String accountAction;
	@CreationTimestamp
	@Temporal(value = TemporalType.DATE)
	@Column(updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Temporal(value = TemporalType.DATE)
	@Column(insertable = false)
	private Date updatedDate;
}
