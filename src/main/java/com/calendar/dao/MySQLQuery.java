package com.calendar.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile({"mysql"})
public class MySQLQuery extends SQLQuery {

    public MySQLQuery() {
        super.setQuery("WITH RECURSIVE Entry_path(id, entry_id) AS(\n" +
                "    SELECT id,entry_id\n" +
                "    FROM entry\n" +
                "    WHERE id = (?)\n" +
                "    UNION ALL\n" +
                "    SELECT e.id, e.entry_id\n" +
                "    FROM Entry_path AS cp JOIN entry AS e\n" +
                "                                  ON cp.entry_id = e.id\n" +
                ")\n" +
                "SELECT id FROM Entry_path ORDER by entry_id ASC;");
    }
}
