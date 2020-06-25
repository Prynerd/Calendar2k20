package com.calendar.dao;

import com.calendar.Connection.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntryDao {

    private SQLQuery sqlQuery;
    private DBConnection dbConnection;

    public EntryDao() {
    }

    @Autowired
    public EntryDao(SQLQuery sqlQuery, DBConnection dbConnection) {
        this.sqlQuery = sqlQuery;
        this.dbConnection = dbConnection;
    }


    public List<Integer> getParents(int entryId) throws SQLException {

        List<Integer> resultList = new ArrayList<>();

        try (Connection connection = DriverManager
//                .getConnection("localhost/calendar2k20", "root", "The@peOfNaples01");
             .getConnection(dbConnection.getUrl(), dbConnection.getUser(), dbConnection.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.getQuery());) {

            preparedStatement.setInt(1, entryId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                resultList.add(id);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return resultList;
    }
}