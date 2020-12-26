package ru.sorochinsky.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sorochinsky.model.User;

/**
 * Repository class for {@link ru.sorochinsky.model.User}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

public interface UserRepositiry extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
