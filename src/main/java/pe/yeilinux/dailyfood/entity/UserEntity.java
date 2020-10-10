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
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Relation (collectionRelation = "Users")
public class UserEntity extends ResourceSupport implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable=false)
	private String user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnoreProperties({"user"})
	private List<FoodEntity> food;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnoreProperties({"user"})
	private List<DayEntity> day;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String dadLastName;
	
	@Column
	private String momLastName;
	
	@Column
	private String fullName;

	@Column
	private String phone;

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
