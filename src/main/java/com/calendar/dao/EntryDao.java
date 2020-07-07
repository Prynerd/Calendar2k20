package com.calendar.dao;

import com.calendar.Connection.DBConnection;
import com.calendar.exceptions.SQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;

@Component
public class EntryDao {

    private SQLQuery sqlQuery;
    private DBConnection dbConnection;

    @Autowired
    public EntryDao(SQLQuery sqlQuery, DBConnection dbConnection) {
        this.sqlQuery = sqlQuery;
        this.dbConnection = dbConnection;
    }

    public int getProjectIdOfEntry(int entryId) {

        ArrayList<Integer> resultList = new ArrayList<>();

        try (Connection connection = DriverManager
                .getConnection(dbConnection.getUrl(), dbConnection.getUser(), dbConnection.getPassword());

             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery.getQuery());) {

            preparedStatement.setInt(1, entryId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                resultList.add(id);
            }
        } catch (SQLException e) {
           throw new SQLError("Ooops! Something went wrong with our Database!");
        }

        return resultList.size() == 1 ? resultList.get(0) : -1;
    }
}
