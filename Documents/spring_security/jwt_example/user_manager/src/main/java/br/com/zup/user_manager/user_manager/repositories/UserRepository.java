package br.com.zup.user_manager.user_manager.repositories;

import br.com.zup.user_manager.user_manager.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    public Optional<UserModel> findByUserName(String username);
}
