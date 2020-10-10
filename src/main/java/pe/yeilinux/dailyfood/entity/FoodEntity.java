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
@Table(name="foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Relation (collectionRelation = "Foods")
public class FoodEntity extends ResourceSupport implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="food_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long foodId;
	
	@Column(nullable=false)
	public String food;

	@JoinColumn(name= "user_id", referencedColumnName = "user_id")
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private UserEntity user;

	@OneToMany(mappedBy="food",fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"food"})
	private List<FoodIngredientDetailEntity> ingredientsDetail;

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
