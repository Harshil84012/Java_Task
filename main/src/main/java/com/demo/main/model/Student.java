package com.demo.main.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "global_table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 3, message = "enter atleast more characters!")
	@Column(name = "first_name")
	private String fn;

	@NotNull
	@Size(min = 3, message = "enter atleast more characters!")
	@Column(name = "last_name")
	private String ln;

	@NotNull(message = "enter email_id")
	@Email(message = "email_id not valid")
	@Column(name = "email_id")
	private String email;

	@NotNull
	@Size(min = 10, max = 10, message = "phone number should be 10 digits ! ")
	@Column(name = "phone_number")
	private String phno;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date doj;

	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date lastModifiedDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String lastModifiedBy;
}
