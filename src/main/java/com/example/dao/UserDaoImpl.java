package com.example.dao;

import com.example.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Nonnull
    @Override
    public List<User> getAllUsers() {
        return UserRowMapper.getAllUser(jdbcOperations);
    }

    @Override
    public void addUser(@Nonnull User user) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    //TODO GG - Use https://github.com/nurkiewicz/spring-data-jdbc-repository instead of Rowmapper
    private static final class UserRowMapper implements RowMapper<User> {

        private static final UserRowMapper INSTANCE = new UserRowMapper();

        private static final String SELECT_SQL = "select id, name, title from \"users\"";

        private UserRowMapper() {
        }

        private static List<User> getAllUser(JdbcOperations jdbcOperations) {
            return jdbcOperations.query(SELECT_SQL, INSTANCE );
        }

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String title = rs.getString("title");
            return new User(id, name, title);
        }
    }
}
