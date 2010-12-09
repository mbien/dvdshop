package de.dvdsoft.shop.business.boundary;

import de.dvdsoft.shop.business.entity.Account;
import de.dvdsoft.shop.business.entity.Media;
import de.dvdsoft.shop.business.entity.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mbien
 */
@Stateless
public class ItemService {
    @PersistenceContext
    EntityManager em;
    
    public List<Media> findAll() {
        return em.createNamedQuery(Media.FIND_ALL).getResultList();
    }

    public void reserve(Account account, Media item) {
        Reservation reservation = new Reservation();
        reservation.setAccount(account);
        reservation.setMedium(item);
        reservation.setDateReserved(new Date());
        em.persist(reservation);
    }

    public void cancelReservation(Account account, Media item) {
        em.remove(getReservation(account, item));
    }

    public boolean isReserved(Account account, Media item) {
        return getReservation(account, item) != null;
    }

    private Reservation getReservation(Account account, Media item) {
        List<Reservation> list = em.createNamedQuery(Reservation.FIND_BY_USER)
                .setParameter("userName", account.getUserName())
                .getResultList();
        for (Reservation reservation : list) {
            if (reservation.getMedium().getId().equals(item.getId())) {
                return reservation;
            }
        }
        return null;
    }
    
    public int getReservationCount(Media item) {
        List<Reservation> list = em.createNamedQuery(Reservation.FOR_ITEM)
                .setParameter("id", item.getId())
                .getResultList();
        return list.size();
    }
    
    public boolean isAvailable(Media item) {
        return getReservationCount(item) < item.getAmount();
    }

    public ListDataModel findItems(String text) {
        System.out.println("search: "+text);
        List<Media> all = findAll();
        List<Media> results = new ArrayList<Media>();
        
        for (int i = 0; i < all.size(); i++) {
            
            Media media = all.get(i);
            
            if(    media.getTitle().toLowerCase().contains(text)
//                || media.getStudio().toLowerCase().contains(text)
                || media.getKind().name().toLowerCase().contains(text)
                    ) {
                results.add(media);
            }
        }
        return new ListDataModel(results);
    }
    
}
