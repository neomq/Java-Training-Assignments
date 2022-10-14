package wk6.oct13.src.main.java.com.wk6oct13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wk6oct13.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {

    @Query("select user from UserModel user where email = ?1 and password=?2")
    Optional<UserModel> getUserByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query("update  UserModel  set token = ?1 where id = ?2")
    Integer updateTokenForUserId(String token, Integer userId);

}
