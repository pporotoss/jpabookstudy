package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	@Embedded	// 현재 엔티티와 매핑된 테이블의 컬럼들을 모아서 객체로 관리.
	private Address address; 
	
	@OneToMany(mappedBy = "member")	// 연관관계의 주인인(외래키를 가지고 있는) Oder객체가 가진 필드중에서 member필드와 연관관계를 설정.
	private List<Order> orders = new ArrayList<Order>();

	// getter, setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
