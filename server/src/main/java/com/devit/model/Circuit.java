package com.devit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "circuit")
public class Circuit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "circuit_id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide circuit name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "monument_circuit", joinColumns = @JoinColumn(name = "monument_id"), inverseJoinColumns = @JoinColumn(name = "circuit_id"))
	private List<Monument> monuments;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "region_id")
	private Region region;

	

	public Circuit(int id, String name, String description, List<Monument> monuments, User user, Region region) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.monuments = monuments;
		this.user = user;
		this.region = region;
	}

	public Circuit() {
		super();
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

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void getCircuitOptimal(float latitude, float longitude) {
		
	}

	public void addMonument(Monument monument){
		this.monuments.add(monument);
	}

}
