package com.mangagod.dto.data;

public class CharacterDataDTO {

	private Integer id;
	private String name;
	private String description;
	private String urlImage;
	private StoryDataDTO story;
	private TypeCharacterDataDTO type;
	
	public CharacterDataDTO() {
		
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

	public StoryDataDTO getStory() {
		return story;
	}

	public void setStory(StoryDataDTO story) {
		this.story = story;
	}

	public TypeCharacterDataDTO getType() {
		return type;
	}

	public void setType(TypeCharacterDataDTO type) {
		this.type = type;
	}
	
}