package domain.model;

import domain.DomainUtilities;
import domain.db.*;

import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class ShopService
{
    private ProductDb productDb;
    private PersonDb personDb;
    private Comparator<Person> byFirstName;
    private Comparator<Person> byName;
    private Comparator<Person> byEmail;

    public ShopService(Properties properties)
    {
        this.personDb = new PersonDbSql(properties);
        this.productDb = new ProductDbInMemory();

        this.byFirstName = Comparator.comparing(Person::getFirstName);
        this.byName = Comparator.comparing(Person::getLastName);
        this.byEmail = Comparator.comparing(Person::getEmail);
    }

    public ShopService()
    {
        this.personDb = new domain.db.PersonDbSql();
        this.productDb = new ProductDbInMemory();
        this.byFirstName = Comparator.comparing(Person::getFirstName);
        this.byName = Comparator.comparing(Person::getLastName);
        this.byEmail = Comparator.comparing(Person::getEmail);
    }

    // -----[Person]-----


    public List<Person> getPersons()
    {
        return getPersonDb().getAll();
    }

    public List<Person> getPersons(String sortOrder)
    {
        List<Person> persons = this.getPersons();
        if (sortOrder.equals("firstName"))
        {
            persons.sort(byFirstName);
        }
        else if (sortOrder.equals("name"))
        {
            persons.sort(byName);
        }
        else
        {
            persons.sort(byEmail);
        }
        return persons;
    }

    public Person getUserIfAuthenticated(String userid, String password)
    {
        Person user = this.getPerson(userid);
        password = DomainUtilities.sha512(password);
        if (user != null && user.getPassword().equals(password))
        {
            return user;
        }
        throw new DbException();
    }

    public void addPerson(Person person) { getPersonDb().add(person); }

    public void updatePersons(Person person)
    {
        getPersonDb().update(person);
    }

    public void deletePerson(String id)
    {
        getPersonDb().delete(id);
    }

    public Person getPerson(String personId)
    {
        return getPersonDb().get(personId);
    }

    private PersonDb getPersonDb()
    {
        return personDb;
    }

    // -----[Product]-----

    public List<Product> getProducts() { return this.productDb.getAll();}

    public Product getProduct(int id) { return  this.productDb.get(id); }

    public void addProduct(Product newProduct)
    {
        this.productDb.add(newProduct);
    }
}
