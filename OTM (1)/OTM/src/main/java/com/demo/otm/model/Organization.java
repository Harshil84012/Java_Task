package com.demo.otm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OneToMany_DTO_Organization_Table")
@EqualsAndHashCode
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int organizationid;

	@Column(name = "organization_name")
	private String organizationname;

	@Column(name = "organization_Type")
	private String organizationType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fkey")
	private List<Student> student;

}
