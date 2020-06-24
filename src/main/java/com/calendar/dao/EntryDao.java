//package com.calendar.dao;
//
//import com.calendar.domain.Entry;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCallback;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//public class EntryDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Entry> getParent(final Entry e){
//
//        String query = "WITH RECURSIVE Entry_path(id, entry_id) AS(\n" +
//                "    SELECT id,entry_id\n" +
//                "    FROM entry\n" +
//                "    WHERE id = (9)\n" +
//                "    UNION ALL\n" +
//                "    SELECT e.id, e.entry_id\n" +
//                "    FROM Entry_path AS cp JOIN entry AS e\n" +
//                "                                  ON cp.entry_id = e.id\n" +
//                ")\n" +
//                "SELECT * FROM Entry_path ORDER by entry_id ASC;";
//
//        return jdbcTemplate.execute(query,new PreparedStatementCallback<List<Entry>>(){
//
//            public List<Entry> doInPreparedStatement(PreparedStatement ps)
//                    throws SQLException, DataAccessException {
//
//                ps.setInt(1,e.getId());
//
//                return ps.execute();
//
//            }
//        });
//    }
//}
//
//
///*
//
//WITH RECURSIVE Entry_path(id, entry_id) AS(
//    SELECT id,entry_id
//    FROM entry
//    WHERE id = 5
//    UNION ALL
//    SELECT e.id, e.entry_id
//    FROM Entry_path AS cp JOIN entry AS e
//                                  ON cp.entry_id = e.id
//)
//SELECT * FROM Entry_path ORDER by entry_id ASC;
//
//* */