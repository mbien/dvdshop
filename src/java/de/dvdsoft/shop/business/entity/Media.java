package de.dvdsoft.shop.business.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (1, 'Wonder Sisters', 'Good old times', 5, 0, 10);
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (2, 'Foo Bar', 'Buffer Overflow 2', 6, 1, 1);
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (3, 'Century Fox', 'The Storm', 10, 2, 10);
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (4, 'unknown', 'Heidi', -1, 3, 10);
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (5, 'Century Fox', 'Movie with Vampires', 2, 2, 10);
INSERT INTO APP.MEDIA (ID, STUDIO, TITLE, PRICE, KIND, AMOUNT) VALUES (8, 'Century Fox', 'Movie with Zombies', 1, 0, 11);
 */

/**
 * @author mbien
 */
@Entity
@Table(name = "Media")
@NamedQueries({
    @NamedQuery(name = Media.FIND_ALL, query = "SELECT m FROM Media m")
})
public class Media implements Serializable {
    
    public final static String PREFIX = "de.dvdsoft.shop.presentation.Media";
    public final static String FIND_ALL = PREFIX+".findAll";
 
    private static final long serialVersionUID = 1L;
    
    public static enum KIND {DVD, BLURAY, HDDVD, BETAMAX}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String studio;
    
    private int price;
    
    private int amount;
    
    private KIND kind;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KIND getKind() {
        return kind;
    }

    public void setKind(KIND kind) {
        this.kind = kind;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.dvdsoft.shop.business.login.entity.Media[ id=" + id + " ]";
    }

}
