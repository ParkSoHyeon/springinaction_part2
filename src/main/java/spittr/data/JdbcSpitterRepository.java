package spittr.data;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import spittr.Spitter;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private static final String INSERT_SPITTER = "insert into Spitter (username, password, fullname, email, updateByEmail) values (?, ?, ?, ?, ?)";
    private static final String INSERT_SPITTER_NAMED = "insert into Spitter (username, password, fullname, email, updateByEmail) values (:username, :password, :fullname, :email, :updateByEmail)";
    private static final String SELECT_SPITTER = "select id, username, password, fullname, email, updateByEmail from Spitter";

    private JdbcOperations jdbcOperations;  //JdbcTemplate으로 구현된 인터페이스 정의 동작

    private JdbcTemplate jdbcTemplate;

    @Bean
    public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcSpitterRepository(jdbcTemplate);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Inject
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void addSpitter(Spitter spitter) {
        //JdbcTemplate
//        jdbcOperations.update(INSERT_SPITTER,
//                spitter.getUsername(),
//                spitter.getPassword(),
//                spitter.getFullName(),
//                spitter.getEmail(),
//                spitter.isUpdateByEmail());

        //NamedParameterJdbcTemplate
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", spitter.getUsername());
        paramMap.put("password" , spitter.getPassword());
        paramMap.put("fullname", spitter.getFullName());
        paramMap.put("email", spitter.getEmail());
        paramMap.put("updateByEmail", spitter.isUpdateByEmail());

        jdbcOperations.update(INSERT_SPITTER_NAMED, paramMap);
    }

    @Override
    public Spitter save(Spitter unsaved) {
        return null;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }

    @Override
    public Spitter findOne(long id) {
        //JDBCTemplate
//        return jdbcTemplate.queryForObject(
//                SELECT_SPITTER + " where id=?", new SpitterRowMapper(), id);

        //JDBCTemplate + Java 8 람다
//        return jdbcOperations.queryForObject(SELECT_SPITTER + "where id = ?",
//                (rs, rowNum) ->  {
//                    return new Spitter(
//                            rs.getLong("id"),
//                            rs.getString("username"),
//                            rs.getString("password"),
//                            rs.getString("fullName"),
//                            rs.getString("email"),
//                            rs.getBoolean("updateByEmail")
//                    );
//        }, id);

        //JDBCTemplate + Java 8 메소드 레퍼런스
        return jdbcOperations.queryForObject(SELECT_SPITTER + "where id=?",
                this::mapSpitter,
                id);
    }

    private Spitter mapSpitter(ResultSet rs, int row) throws SQLException {
        return new Spitter(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("email"),
                rs.getBoolean("updateByEmail")
            );
    }


    //JDBCTemplate
//    private static final class SpitterRowMapper implements RowMapper<Spitter> {
//        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new Spitter(
//                    rs.getLong("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("fullName"),
//                    rs.getString("email"),
//                    rs.getBoolean("updateByEmail")
//            );
//        }
//
//        @Override
//        public int[] getRowsForPaths(TreePath[] path) {
//            return new int[0];
//        }
//    }
}