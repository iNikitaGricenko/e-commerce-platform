package com.wolfhack.service.payment.model.entity;

import com.wolfhack.service.payment.model.PaymentMethod;
import com.wolfhack.service.payment.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class PaymentEntity {

	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod method;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "status")
	private PaymentStatus status;

	@Column(name = "success_date_time")
	private LocalDateTime successDate;

	@Column(name = "cancel_date_time")
	private LocalDateTime cancelDate;

    // Separated fields for PayPal payment method
    private String paypalOrderId;
    private String paypalLink;

    // Separated fields for Stripe payment method details
    private String paymentIntentId;

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
		PaymentEntity that = (PaymentEntity) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}

}
