package miniBank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import miniBank.model.Balance;
import miniBank.model.User;

@Repository
public interface BalanceRepository  extends JpaRepository<Balance, Long> {
	 Optional<User> findByUsername(String username);
	
}
