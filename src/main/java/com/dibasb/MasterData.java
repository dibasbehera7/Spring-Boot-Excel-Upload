package com.dibasb;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "MASTER_DATA")
@Data
public class MasterData {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private Long id;
	
	private String title;
	
	private String description;
	
	private String activeYn;
	
	private String fileName;
	
	private byte[] fileContent;
	
	@Column(insertable = false, updatable = false)
	private String contentType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-mm-yyyy hh24:mm:ss")
	private Date actionOn; 
}
