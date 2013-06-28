package fr.xebia.xke.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {

    public static final String SALES = "Sales";

    public static final String ENGINEER = "Engineer";

    public static final String TRADER = "Trader";

    @Column(nullable = false)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
