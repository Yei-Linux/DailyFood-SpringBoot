package pe.yeilinux.dailyfood.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Relation(collectionRelation="Ingredients")
public class IngredientEntity extends ResourceSupport implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="ingredient_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ingredientId;
	
	@Column(nullable=false)
	private String ingredient;
	
	@OneToMany(mappedBy="ingredient")
	@JsonIgnoreProperties({"ingredient"})
	private List<FoodIngredientDetailEntity> foodsDetail;

	@Column
	private Date createdAt;

	@Column
	private Date updatedAt;

	@Column
	private Date deletedAt;

	@PrePersist
	protected void onCreatedAt(){
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdatedAt() {
		this.updatedAt = new Date();
	}
}
