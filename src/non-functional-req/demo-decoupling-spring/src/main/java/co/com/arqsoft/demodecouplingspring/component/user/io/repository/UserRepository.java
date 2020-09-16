package co.com.arqsoft.demodecouplingspring.component.user.io.repository;

import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
