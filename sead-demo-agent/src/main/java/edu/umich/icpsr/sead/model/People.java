package edu.umich.icpsr.sead.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class People {
	@JsonProperty("@id")
	String id;
	@JsonProperty("http://schema.org/Person/givenName")
	String givenName;

	@JsonProperty("http://schema.org/Person/familyName")
	String familyName;

	@JsonProperty("http://schema.org/Person/email")
	String email;

	@JsonProperty("http://schema.org/Person/affiliation")
	@JsonDeserialize(using = StringOrListValueDes.class)
	List<String> affiliation;

	@JsonProperty("http://schema.org/Thing/mainEntityOfPage")
	String personalProfileDocument;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(List<String> affiliation) {
		this.affiliation = affiliation;
	}

	public String getPersonalProfileDocument() {
		return personalProfileDocument;
	}

	public void setPersonalProfileDocument(String personalProfileDocument) {
		this.personalProfileDocument = personalProfileDocument;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", givenName=" + givenName + ", familyName=" + familyName + ", email=" + email + ", affiliation=" + affiliation + ", personalProfileDocument="
				+ personalProfileDocument + "]";
	}
}
