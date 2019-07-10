package domain.db;

import domain.DomainUtilities;
import domain.model.AuthorizationRole;
import domain.model.Person;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.List;

public class PersonDbSql implements PersonDb
{
    private Properties properties;

    public PersonDbSql()
    {
        this(new Properties());
        this.properties.setProperty("url", "jdbc:postgresql://databanken.ucll.be:51819/2TX36?currentSchema=r0599128");
        this.properties.setProperty("user", "local_r0599128");
        this.properties.setProperty("password", "ow8CprFQfujzCnD");
        // Secret.setPass(this.properties);	// implements line 17 and 18
        this.properties.setProperty("ssl", "true");
        this.properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
//         this.properties.setProperty("sslmode","prefer");
    }

    public PersonDbSql(Properties properties)
    {
        this.properties = properties;
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e)
        {
            throw new DbException("Driver not found");
        }
    }

    public Person get(String personId)
    {
        Person person = new Person();
        String query = "SELECT * FROM person WHERE userid = ?";
        try (
                Connection connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties);
                PreparedStatement statement = connection.prepareStatement(query)
        )
        {
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            result.next();
            try
            {
                person.setUserid(result.getString("userid"));
                person.setEmail(result.getString("email"));
                person.setPassword(result.getString("password"));
                person.setFirstName(result.getString("firstname"));
                person.setLastName(result.getString("lastname"));
                person.setRole(queryRole(result.getInt("role")));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                throw new DbException("Person with id " + personId + " does not exist");
            }

        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        return person;
    }

    private AuthorizationRole queryRole(int roleId)
    {
        AuthorizationRole role;
        String query = "SELECT name FROM role WHERE id = ?";
        try (
                Connection connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties);
                PreparedStatement statement = connection.prepareStatement(query)
        )
        {
            statement.setInt(1, roleId);
            ResultSet result = statement.executeQuery();
            result.next();
            try
            {
                role = AuthorizationRole.valueOf(result.getString("name"));
            }
            catch (Exception e)
            {
                throw new DbException("Invalid role.");
            }

        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        return role;
    }

    public List<Person> getAll()
    {
        ArrayList<Person> persons = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties);
                Statement statement = connection.createStatement()
        )
        {
            ResultSet result = statement.executeQuery("SELECT * FROM person");
            while (result.next())
            {
                Person newPerson = new Person();
                try
                {
                    newPerson.setUserid(result.getString("userid"));
                    newPerson.setEmail(result.getString("email"));
                    newPerson.setPassword(result.getString("password"));
                    newPerson.setFirstName(result.getString("firstname"));
                    newPerson.setLastName(result.getString("lastname"));
                }
                catch (Exception e)
                {
                    throw new DbException("db conatins corrupt data: " + newPerson);
                }
                persons.add(newPerson);
            }
        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        return persons;
    }

    public void add(Person person)
    {
        if (person == null)
        {
            throw new DbException("No person given");
        }
        if (personExists(person.getUserid()))
        {
            throw new DbException("User already exists");
        }

        String query = "INSERT INTO person (userid, email, password, firstname, lastname, role) VALUES (?,?,?,?,?, 1)";
        try (
                Connection connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties);
                PreparedStatement statement = connection.prepareStatement(query)
        )
        {
            statement.setString(1, person.getUserid());
            statement.setString(2, person.getEmail());
            statement.setString(3, DomainUtilities.sha512(person.getPassword()));
            statement.setString(4, person.getFirstName());
            statement.setString(5, person.getLastName());

            statement.execute();
//            if (!statement.execute())
//            {
//                throw new DbException("Could not execute query\n"
//                                      + statement + "\n");
//            }
        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
    }

    private boolean personExists(String userid)
    {
        String query = "SELECT * FROM person WHERE person.userid = ?";
        try (
                Connection connection = DriverManager.getConnection(this.properties.getProperty("url"), this.properties);
                PreparedStatement statement = connection.prepareStatement(query)
        )
        {
            statement.setString(1, userid);
            return statement.executeQuery().next();
        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
    }

    public void update(Person person)
    {
//        if (person == null)
//        {
//            throw new DbException("No person given");
//        }
//        if (!persons.containsKey(person.getUserid()))
//        {
//            throw new DbException("No person found");
//        }
//        persons.put(person.getUserid(), person);
    }

    public void delete(String personId)
    {
//        if (personId == null)
//        {
//            throw new DbException("No id given");
//        }
//        persons.remove(personId);
    }

    public int getNumberOfPersons()
    {
        return 0;
    }
}
