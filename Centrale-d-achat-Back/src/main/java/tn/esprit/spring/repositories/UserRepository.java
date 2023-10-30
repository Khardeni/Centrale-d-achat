package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByUserEmail(String UserEmail);

    public User findByUserPhone(String phone);

    public boolean existsByUserEmail(String UserEmail);

    @Query("select u.userPassword from User u where u.userEmail=?1")
    public String getPasswordByUserEmail(String UserEmail);

    User  findByVerificationToken(String Token);

    User findByUserName(String username);
}
