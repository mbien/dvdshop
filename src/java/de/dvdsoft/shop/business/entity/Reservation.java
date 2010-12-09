package de.dvdsoft.shop.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mbien
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Reservation.FIND_BY_USER, query = "SELECT r FROM Reservation r WHERE r.account.userName = :userName"),
    @NamedQuery(name = Reservation.FOR_ITEM, query = "SELECT r FROM Reservation r WHERE r.medium.id = :id")
})
public class Reservation implements Serializable {
    
    public final static String PREFIX = "de.dvdsoft.shop.business.entity.Reservation";
    public final static String FIND_BY_USER = PREFIX+".findAll";
    public final static String FOR_ITEM = PREFIX+".forItem";
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn()
    private Account account;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn()
    private Media medium; 
    
    @Temporal(TemporalType.DATE)
    private Date dateReserved;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Media getMedium() {
        return medium;
    }

    public void setMedium(Media medium) {
        this.medium = medium;
    }

    public Date getDateReserved() {
        return dateReserved;
    }

    public void setDateReserved(Date dateReserved) {
        this.dateReserved = dateReserved;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.dvdsoft.shop.business.entity.Reservation[ id=" + id + " ]";
    }

}
