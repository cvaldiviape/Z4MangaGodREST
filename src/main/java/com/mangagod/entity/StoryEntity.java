package com.mangagod.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.mangagod.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//@NamedEntityGraphs({
//		@NamedEntityGraph(
//				name = "StoryEntity.country",
//				attributeNodes = {
//						@NamedAttributeNode("country")
//				}
//		),
//		@NamedEntityGraph(
//				name = "StoryEntity.storiesMangakas",
//				attributeNodes = @NamedAttributeNode("storiesMangakas")
//		),
//		@NamedEntityGraph(
//				name = "StoryEntity.multiples-relationships",
//				attributeNodes = {
//						@NamedAttributeNode("title"),
//						@NamedAttributeNode("demography"),
//						@NamedAttributeNode("category"),
//				}
//		)
//})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "stories") 
public class StoryEntity extends BaseEntity {
	
	@Column(name = "title", nullable = false, unique = true)
	private String title;
	@Column(name = "year", nullable = false)
	private Short year;
	@Column(name = "synopsis", nullable = false)
	private String synopsis;
	@Column(name = "state", nullable = false)
	private Boolean state;
	@Column(name = "url_image")
	private String urlImage;
	@Column(name = "adaptation_anime", nullable = false)
	private Boolean adaptationAnime;
	@Column(name = "price")
	private Double price;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;
	@ManyToOne
	@JoinColumn(name = "demography_id")
	private DemographyEntity demography;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	@Builder.Default
	@OneToMany(mappedBy = "story")
	private Set<CharacterEntity> characters = new HashSet<>();
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "stories_genres", joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id"), 
									    inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
	private Set<GenreEntity> genres = new HashSet<>();
	@Builder.Default
	@OneToMany(mappedBy = "story", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<StoryMangakaEntity> storiesMangakas = new HashSet<>();

	@Override
	public String toString() {
		return "StoryEntity{" +
				"title='" + title + '\'' +
				", year=" + year +
				", synopsis='" + synopsis + '\'' +
				", state=" + state +
				", urlImage='" + urlImage + '\'' +
				", adaptationAnime=" + adaptationAnime +
				", price=" + price +
				", storiesMangakas=" + storiesMangakas +
				'}';
	}
}