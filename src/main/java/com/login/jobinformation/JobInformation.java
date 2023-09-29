package com.login.jobinformation;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.login.applicationdetails.ApplicationDetails;
import com.login.company.Company;
import com.login.jobcategory.JobCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "jobinformation")
public class JobInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_id")
	private int jobId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "no_of_vacancy")
	private int noOfVacancy;

	@Column(name = "type")
	private String type;

	@Column(name = "salary")
	private int salary;

//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
 //   @JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "position_date")
	private Long positionDate;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(name = "application_date")
	private Long applicationDate;

	@Column(name = "status")
	private String status;

	@Column (name = "job_location")
	private String jobLocation;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobInformation")
	private List<ApplicationDetails> applicationDetails;

	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private JobCategory jobCategory;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private Company company;

}
