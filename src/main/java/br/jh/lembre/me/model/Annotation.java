package br.jh.lembre.me.model;

import static br.jh.lembre.me.util.UrlUtils.removePrefix;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document
public class Annotation implements Serializable{

	private static final long serialVersionUID = -704133223718043689L;

	@Id
	private String id;
	
	private String description;

	private String link;

	private String documentation;
	
	private Subscribe subscribe;
	
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Subscribe getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getLinkCleared(){
		return removePrefix(link);
	}

	public String getDocumentCleared(){
		return removePrefix(documentation);
	}
	
	public String serialize(){
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
}