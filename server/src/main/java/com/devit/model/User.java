package com.devit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devit.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "username", unique = true, length = 50)
	private String username;

	@Column(name = "password", length = 50)
	@Lob
	private String password;

	@Column(name = "email", unique = true, length = 100)
	private String email;
	
	@Column(name = "active")
	private int active = 1;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_monument", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "monument_id"))
	private List<Monument> monuments;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Circuit> circuits;

	public User(String name, String lastName, String username, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(int id, String name, String lastName, String username, String password, String email, List<Role> roles,
			List<Monument> monuments, List<Circuit> circuits) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.monuments = monuments;
		this.circuits = circuits;
	}

	public User() {
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public List<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuit> circuits) {
		this.circuits = circuits;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void addMonument(Monument monument){
		this.monuments.add(monument);
	}

	public void addCircuit(Circuit circuit){
		this.circuits.add(circuit);
	}

	public boolean containsMonument(Monument monument){
		return monuments.contains(monument);
	}

	public boolean containsCircuit(Circuit circuit){
		return circuits.contains(circuit);
	}

	public Circuit findCircuit(int id){
		for(Circuit circuit : circuits){
			if(circuit.getId() == id)
				return circuit;
		}
		return null;
	}

	public boolean deleteCircuit(Circuit circuit){
		return circuits.remove(circuit);
	}

	public boolean deleteMonument(Monument monument){
		return monuments.remove(monument);
	}

	public boolean hasRole(String roleName) {
		
		for(Role role : this.roles) {
			if(role.getRole().equals(roleName))
				return true;
		}
		return false;
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
	    this.password = passwordEncoder.encode(this.password);
	}
}
