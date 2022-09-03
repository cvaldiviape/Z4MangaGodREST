package com.mangagod.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.mangagod.entity.base.BaseEntity;
import com.mangagod.util.enums.Sex;

@Entity
@Table(name = "persons")
public class PersonEntity extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "sexo")
	private Sex sex;
	@Column(name = "birth_date")
	private LocalDate birthDate;
	@OneToOne(mappedBy="person", cascade = CascadeType.REMOVE, optional=true)
	private AuthorEntity author;
	@OneToOne(mappedBy="person", cascade = CascadeType.REMOVE, optional=true)
	private ArtistsEntity artists;
	
	public PersonEntity() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public ArtistsEntity getArtists() {
		return artists;
	}

	public void setArtists(ArtistsEntity artists) {
		this.artists = artists;
	}
	
}