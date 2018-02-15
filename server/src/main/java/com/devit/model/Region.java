package com.devit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "region")
public class e Region {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide region name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "url_to_image")
	@NotNull(message ="*Please provide monument image")
	private String urlToImage;
	
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Circuit> circuits;
	
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Monument> monuments;

	public Region(int id, String name, String description, List<Circuit> circuits, List<Monument> monuments) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.circuits = circuits;
		this.monuments = monuments;
	}

	
	public Region() {
		super();
	}

	
	public Region(String name, String urlToImage) {
		super();
		this.name = name;
		this.urlToImage = urlToImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuit> circuits) {
		this.circuits = circuits;
	}

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	
	
	
}
