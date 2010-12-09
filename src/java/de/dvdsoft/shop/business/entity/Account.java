package de.dvdsoft.shop.business.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/*
INSERT INTO APP.ACCOUNT (USERNAME, PASSWORD) VALUES ('duke', 'duke');
INSERT INTO APP.ACCOUNT (USERNAME, PASSWORD) VALUES ('hugo', 'hugo');
 */

/**
 *
 * @author mbien
 */
@Entity
@Table(name = "Account")
@NamedQueries({
    @NamedQuery(name=Account.BY_NAME, query="Select a from Account a where a.userName like :name and a.password like :password")
})
public class Account implements Serializable {
    
    public final static String PREFIX = "de.dvdsoft.shop.Account";
    public final static String BY_NAME = PREFIX+".byName";
    
    @Id
    @Size(min=4,max=20)
    private String userName = "guest";
    
    @Size(min=4,max=64)
    private String password;
    
    @OneToMany
    private List<Reservation> reservations;
    
    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public void reset() {
        this.userName = "guest";
        this.password = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return getClass().getName()+"["+userName+"]";
    }
    
    
}
