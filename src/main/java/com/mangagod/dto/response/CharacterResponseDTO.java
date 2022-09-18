package com.mangagod.dto.response;

public class CharacterResponseDTO {

	private Integer id;
	private String name;
	private String description;
	private String urlImage;
	private StoryResponseDTO story;
	private TypeCharacterResponseDTO type;
	
	public CharacterResponseDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public StoryResponseDTO getStory() {
		return story;
	}

	public void setStory(StoryResponseDTO story) {
		this.story = story;
	}

	public TypeCharacterResponseDTO getType() {
		return type;
	}

	public void setType(TypeCharacterResponseDTO type) {
		this.type = type;
	}
	
}