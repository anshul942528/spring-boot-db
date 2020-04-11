package com.ansh.repo;

import com.ansh.model.User;
import com.ansh.model.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepository {

    @Autowired
    private AbstractRepository abstractRepository;

    private static final String INSERT_USER = "INSERT INTO test.user (`id`, `name`, `email`) VALUES (?, ?, ?)";

    private static final String GET_USERS = "SELECT id, name, email FROM test.user";

    private static final String GET_USER = "SELECT id, name, email FROM test.user WHERE id = ?";

    private static final String UPDATE_USER = "UPDATE user set name = ?, email = ? where id = ?";

    private static final String DELETE_USER = "DELETE from user where id = ?";

    public void save(List<Object[]> list) {
        abstractRepository.batchUpdate(INSERT_USER, list);
    }

    public List<User> getUserList() {
        return abstractRepository.find(GET_USERS, new UserRowMapper());
    }

    public User getUser(Integer id) {
        return abstractRepository.findOne(GET_USER, new UserRowMapper(), id);
    }

    public void updateUser(User user){
        abstractRepository.update(UPDATE_USER, user.getName(), user.getEmail(), user.getId());
    }

    public void deleteUser(Integer id){
        abstractRepository.update(DELETE_USER, id);
    }
}
