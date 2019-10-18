package com.swap281.model.item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification implements Specification<Item>{

	private Item item;
	
	public ItemSpecification(Item item) {
		super();
		this.item = item;
	}

	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		
		Predicate p = cb.disjunction();

		if (item.title != null) {
			p.getExpressions().add(cb.equal(root.get("title"), item.title));
		}
		
		if (item.price != null) {
			p.getExpressions().add(cb.and(
						cb.equal(root.get("price"), item.price)
						//cb.equal(root.get("age"), item.)
					));
		}
		
		
		return p;
		
	}

	
}
