package com.wolfhack.service.catalog.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "products")
public class ProductEntity {

    @Id
	@Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
    private String name;

	@Column(name = "description")
    private String description;

	@Column(name = "price")
    private BigDecimal price;

	@Column(name = "sku")
	private String sku;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<InventoryEntity> inventories;

	@ManyToMany
	@JoinTable(
		name = "product_category",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	@ToString.Exclude
	private Set<CategoryEntity> categories;

	@ManyToMany
	@JoinTable(
		name = "product_tag",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	@ToString.Exclude
	private Set<TagEntity> tags;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<ImageEntity> images;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	private StockEntity stock;

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) {
			return false;
		}
		ProductEntity that = (ProductEntity) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}

}
