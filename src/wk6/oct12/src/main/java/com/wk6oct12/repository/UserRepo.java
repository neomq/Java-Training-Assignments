package wk6.oct12.src.main.java.com.wk6oct12.repository;

import com.wk6oct12.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {

    public UserModel findByEmail(String email);

}
