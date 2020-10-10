package pe.yeilinux.dailyfood.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.yeilinux.dailyfood.helper.JpaConverterJsonHelper;

@Entity
@Table(name="days")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation="Days")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DayEntity extends ResourceSupport implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="day_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dayId;

	@JoinColumn(name="user_id",referencedColumnName = "user_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity user;

	@Column(nullable = false)
	@Convert(converter= JpaConverterJsonHelper.class)
	private Object weekSchema;

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
