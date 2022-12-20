package pl.coderslab.jeeusercrud.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.jeeusercrud.model.User;
import pl.coderslab.jeeusercrud.utils.DbUtil;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY_BY_ID = "SELECT * from users where id  = ?";
    private static final String READ_USER_QUERY_BY_EMAIL = "SELECT * from users where email = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users set email = ?, username = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY = "delete from users where id = ?";

    private static final String READ_ALL_USERS = "select * from users";
    private static final String CHECK_USERNAME = "SELECT username from users where username = ?";
    private static final String CHECK_EMAIL = "SELECT email from users where email = ?";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User read (int userId) {
        User user = new User();
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User read (String email) {
        User user = new User();
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setUserName(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(2, user.getUserName());
            statement.setString(1, user.getEmail());
            //zakładamy ze nie zmianiemay hasła więce bez hash metoda zmieniajaca hasło jest inna
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }


    public static boolean checkUserName(String userName) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(CHECK_USERNAME);
            statement.setString(1, userName);
            String username = "";
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
            }
            return username.equals(userName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEmail(String email) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(CHECK_EMAIL);
            statement.setString(1, email);
            String em = "";
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                em = resultSet.getString("email");
            }
            return em.equals(email);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifyEmail(String email){
        return email.matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}");
    }

    public static boolean verifyPasswordStrength (String password) {
        String passwordReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern compilePattern = Pattern.compile(passwordReg);
        Matcher matcher = compilePattern.matcher(password);
        return matcher.matches();
    }

    public static boolean verifyUserName(String userName) {
        return userName.matches("[a-z0-9_-]{3,16}");
    }
}
