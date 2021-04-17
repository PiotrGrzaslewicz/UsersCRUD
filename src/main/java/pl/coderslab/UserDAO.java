package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DBUtil;
import pl.coderslab.utils.InvalidEmailException;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT username, email, password FROM users  WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username=?, email=?, password=? WHERE id=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String CHECK_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";





    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

//    public User create(User user) {
//        try (Connection conn = DBUtil.getConnection()) {
//            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(1, user.getUserName());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, hashPassword(user.getPassword()));
//            statement.executeUpdate();
//            ResultSet resultSet = statement.getGeneratedKeys();
//            if (resultSet.next()) {
//                user.setId((resultSet.getInt(1)));
//            }
//            return user;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static User read(int id) {
        User user = new User();
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setId(id);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(User user)  throws InvalidEmailException  {
        try (Connection conn = DBUtil.getConnection()) {

            if (!checkMail(conn,user)) {throw new InvalidEmailException("Email already exists. Please try another email");}
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, Integer.toString(user.getId()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User[] findAll() {
        User[] allUsers = new User[0];
        try (Connection conn = DBUtil.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                allUsers = addToArray(user, allUsers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    private static User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }


    ///CREATE with EMAIL CHECK

    public static User create(User user) throws InvalidEmailException {
        try (Connection conn = DBUtil.getConnection()) {

            if (!checkMail(conn,user)) {throw new InvalidEmailException("Email already exists. Please try another email");}

            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId((resultSet.getInt(1)));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
    }

    private static boolean checkMail(Connection conn, User user) throws SQLException {
        boolean check = true;

        PreparedStatement emailCheck = conn.prepareStatement(CHECK_EMAIL);
        emailCheck.setString(1, user.getEmail());
        ResultSet checkResult = emailCheck.executeQuery();
        while (checkResult.next()) {

            int i = Integer.parseInt(checkResult.getString("COUNT(*)"));
            if (i != 0) {
                return false;
            }
        }
        return check;
    }
}
