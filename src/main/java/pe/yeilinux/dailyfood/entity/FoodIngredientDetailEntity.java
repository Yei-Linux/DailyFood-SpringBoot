package pe.yeilinux.dailyfood.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="food_ingredients_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLayInitializer","handler"})
public class FoodIngredientDetailEntity extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long foodIngredientId;

	@JsonIgnoreProperties({"user","ingredientsDetail","createdAt","deletedAt"})
	@JoinColumn(name="food_id", referencedColumnName = "food_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private FoodEntity food;

	@JsonIgnoreProperties({"foods","createdAt","deletedAt"})
	@JoinColumn(name="ingredient_id", referencedColumnName = "ingredient_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private IngredientEntity ingredient;
	
	@Column(nullable=false)
	private String size;

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
