package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")	// 현재 엔티티를 ORDERS 테이블과 매핑. 기본값은 클래스명.
public class Order {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")	// id필드를 테이블의 ORDER_ID컬럼과 매핑.
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")	// 현재 엔티티와 매핑된 테이블이 가진 외래키인 MEMBER_ID를 바탕으로 조인. 외래키는 다(many) 쪽의 엔티티가 항상 가지게 된다.
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)	// 현재 엔티티의 기본키를 외래키로 사용하는(외래키의 주인인) OrderItem 객체의 order필드와 매핑.
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DELIVERY_ID")	// 현재 엔티티가 가진 외래키인 DELIVERY_ID를 바탕으로 조인.
	private Delivery delivery;
	
	private Date orderDate;
	
	@Enumerated(EnumType.STRING)// Enum의 이름을 데이터베이스에 저장하고 사용한다.
	private OderStatus status;
	
	
	//==  연관관계 메서드  ==//
	public void setMember(Member member) {	// 연관관계를 각각 setter로 주입시 무한 반복의 우려가 생기므로 한 곳에서 연관관계를 설정해준다.
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOder(this);
	}
	
	public void setDelivery(Delivery delivery){
		this.delivery = delivery;
		delivery.setOrder(this);		
	}
	
}
