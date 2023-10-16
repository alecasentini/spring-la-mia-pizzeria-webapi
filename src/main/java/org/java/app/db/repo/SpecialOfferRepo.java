package org.java.app.db.repo;

import java.util.List;

import org.java.app.db.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Integer> {
	List<SpecialOffer> findByPizzaId(int pizzaId);
}

