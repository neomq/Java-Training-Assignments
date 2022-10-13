package wk6.oct12.src.main.java.com.wk6oct12.repository;

import com.wk6oct12.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {

    public UserModel findByEmail(String email);

    @Query("select user from UserModel user where email = ?1 and password=?2")
    Optional<UserModel> getUserByEmailAndPassword(String email, String password);

}
