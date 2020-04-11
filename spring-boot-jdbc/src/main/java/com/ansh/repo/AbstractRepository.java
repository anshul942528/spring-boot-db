package com.ansh.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class AbstractRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public <T> List<T> find(String query, RowMapper<T> rowMapper, Object... params) {
        StopWatch stopWatch = new StopWatch();
        int rowCount = 0;
        try{
            List<T> results = jdbcTemplate.query(query, params, rowMapper);
            rowCount = results.size();
            return results;
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return null;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }

    public <T> T findOne(String query, RowMapper<T> rowMapper, Object... params) {
        StopWatch stopWatch = new StopWatch();
        try{
            return jdbcTemplate.queryForObject(query, params, rowMapper);
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return null;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }

    public Integer findInteger(String query, Object... params) {
        StopWatch stopWatch = new StopWatch();
        try {
            Number num = jdbcTemplate.queryForObject(query, params, Integer.class);
            return num != null ? num.intValue() : 0;
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return null;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }

    public String findString(String query, Object... params) {
        StopWatch stopWatch = new StopWatch();
        try {
            return jdbcTemplate.queryForObject(query, params, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            });
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return null;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }

    public int update(String query, Object... params) {
        StopWatch stopWatch = new StopWatch();
        int rowCount = 0;
        try{
            rowCount = jdbcTemplate.update(query, params);
            return rowCount;
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return 0;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }

    public int[] batchUpdate(String query, List<Object[]> params) {
        StopWatch stopWatch = new StopWatch();
        int rowCount = 0;
        try{
            int[] results = jdbcTemplate.batchUpdate(query, params);
            for(int num : results){
                rowCount = rowCount + num;
            }
            return results;
        } catch(Exception ex) {
            log.error("Exception occurred", ex);
            return null;
        } finally {
            log.info("Total time taken : " + stopWatch.getTotalTimeMillis());
        }
    }
}
