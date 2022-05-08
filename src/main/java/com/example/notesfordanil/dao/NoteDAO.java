package com.example.notesfordanil.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.security.Key;
import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/Notes";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Vlad211003";
    private static Connection connection;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Note> getAll() {

        List<Note> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM notes";
            statement.execute(SQL);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt("id"));
                note.setNotetopic(resultSet.getString("notetopic"));
                note.setContent(resultSet.getString("content"));
                list.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void save(String tema, String content) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE notes set content = ? where notetopic = ?");
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, tema);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getText(String str) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT content FROM notes WHERE notetopic=? ");
            preparedStatement.setString(1, str);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getString("content");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void newNote(String str) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO notes (notetopic) values (?)");
            preparedStatement.setString(1, str);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteNote(String str) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from notes where notetopic = ?");
            preparedStatement.setString(1, str);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

