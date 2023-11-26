package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class ConnectionToDatabase {


    public boolean createTask(String title, String content) {
        Connection connection = null;
        int result = 0;
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db_lesson_1",
                    "jdbc_user", "1234");
            Statement statement = connection.createStatement();
            String sql = "insert into task (title,content) values('%s','%s')";
            sql = String.format(sql, title, content);
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result != 0;
    }


    public List<Task> getAllActivTask(String status) {
        Connection connection = null;
        List<Task> taskList = new LinkedList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db_lesson_1",
                    "jdbc_user", "1234");
            Statement statement = connection.createStatement();
            String sql = "select * from task where status='%s'";
            sql = String.format(sql, status);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                LocalDateTime cretadDate = resultSet.getTimestamp("created_date").toLocalDateTime();
                Task task = new Task();
                task.setId(id);
                task.setTitle(title);
                task.setContent(content);
                task.setCreatedDate(cretadDate);
                task.setStatus(resultSet.getString("status"));
                if (status.equals(TasKStatus.DONE.toString())) {
                    Timestamp finishedDate = resultSet.getTimestamp("finished_date");
                    if (finishedDate != null) {
                        LocalDateTime finishdate = finishedDate.toLocalDateTime();
                        task.setFinishedDate(finishdate);
                    }
                }
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }


    public boolean updateTask(int id, String updateTitle, String updateContent) {
        Connection connection = null;
        int result = 0;
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db_lesson_1",
                    "jdbc_user", "1234");
            Statement statement = connection.createStatement();
            String sql = "update task set title='%s',content='%s' where id=%d ";
            sql = String.format(sql, updateTitle,updateContent,id);
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result != 0;
    }

    public boolean delete(int id) {
        Connection connection = null;
        int result = 0;
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db_lesson_1",
                    "jdbc_user", "1234");
            Statement statement = connection.createStatement();
            String sql = " delete  from task where id="+id;
            sql = String.format(sql,id);
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result != 0;

    }

    public boolean markAsDone(int id) {
        Connection connection = null;
        int result = 0;
        try {
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db_lesson_1",
                    "jdbc_user", "1234");
            Statement statement = connection.createStatement();
            String sql = " update task set status='DONE',finished_date=now() where id="+id;
            sql = String.format(sql,id);
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result != 0;

    }

}
