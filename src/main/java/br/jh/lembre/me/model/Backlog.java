package br.jh.lembre.me.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Backlog implements Serializable{

	private static final long serialVersionUID = 289723031688971102L;
	
	@Id
	private String id;
	
	private String origin;

	private BacklogType type;

	private LocalDateTime createdDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public BacklogType getType() {
		return type;
	}

	public void setType(BacklogType type) {
		this.type = type;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	
}