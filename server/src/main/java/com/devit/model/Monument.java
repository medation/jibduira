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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "monument")
public class Monument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "monument_id")
	private int id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide monument name")
	private String name;
	
	@Lob
	@Column(name = "description", length = 5000)
	private String description;
	
	@Column(name = "latitude")
	@DecimalMax("100.0")
	@DecimalMin("-100.0")
	@NotNull(message ="*Please provide monument latitude")
	private double latitude;
	
	@Column(name = "longitude")
	@DecimalMax("100.0")
	@DecimalMin("-100.0")
	@NotNull(message ="*Please provide monument longitude")
	private double longitude;
	
	@Column(name = "url_to_image")
	@NotNull(message ="*Please provide monument image")
	private String urlToImage;
	
	@Column(name = "active")
	private boolean active = false;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name = "monument_circuit", joinColumns = @JoinColumn(name = "monument_id"), inverseJoinColumns = @JoinColumn(name = "circuit_id"))
	private List<Circuit> circuits;
	
	@ManyToOne
    @JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne
    @JoinColumn(name = "region_id")
	private Region region;


//	@Column(name = "title")
//	private String title;
//
//	@Column(name = "subtitle")
//	private String subtitle;
//
//	@Column(name = "paragraphes")
//	private List<String> paragraphes;




	public Monument(String name, String description, double latitude, double longitude, String urlToImage) {
		super();
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.urlToImage = urlToImage;
	}


	public Monument(int id, String name, String description, double latitude, double longitude, String urlToImage,
			boolean active, List<Circuit> circuits, City city, Region region) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.urlToImage = urlToImage;
		this.active = active;
		this.circuits = circuits;
		this.city = city;
		this.region = region;
	}


	public Monument() {
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuit> circuits) {
		this.circuits = circuits;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void addToCircuit(Circuit circuit) {
		if(!circuits.contains(circuit))
			circuits.add(circuit);
	}

}
