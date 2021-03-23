package com.dibasb;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MASTER_DATA")
@Data
public class MasterData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private String activeYn;
	
	private String fileName;
	
	private byte[] fileContent;
	
	private String contentType;
	
	private Date actionOn; 
}
