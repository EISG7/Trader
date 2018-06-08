package entity;


import javax.persistence.*;

@Entity
@Table
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String company;
    private String password;

    public Trader(String name, String password) {
        this.name = name;
        this.company = "MS";
        this.password = password;
    }

    public Trader(String name, String company, String password) {
        this.name = name;
        this.company = company;
        this.password = password;
    }

    public Trader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
