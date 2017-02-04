package sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.entity.AuthUser;

/**
 * Created by liminghang on 1/18/17.
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{

    AuthUser findByUsername(String username);
}
