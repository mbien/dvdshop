package de.dvdsoft.shop.business.boundary;

import de.dvdsoft.shop.business.entity.Account;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mbien
 */
@ManagedBean(name="loginService")
@SessionScoped
public class LoginService implements Serializable {
    
    @PersistenceContext
    EntityManager em;
    
    private boolean loggedIn;
    private Account account = new Account();
   

    private void login(Account account){
        loggedIn = findAccount(account) != null;
        if(!loggedIn) {
            logout();
        }
    }
    
    public Account findAccount(Account account) {
        System.out.println("findAccount: "+account);
        System.out.println(em);
        System.out.println(account.getPassword());
        
        Query query = this.em.createNamedQuery(Account.BY_NAME);
        System.out.println(query);
        query.setParameter("name", account.getUserName());
        query.setParameter("password", account.getPassword());
        
        List resultList = query.getResultList();
        System.out.println("found: "+resultList);
        return resultList.isEmpty() ? null : (Account)resultList.get(0);
    }
    
    
    public void login() {
        login(account);
    }
    
    public void logout() {
        System.out.println("logout: "+account);
        account.reset();
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
        
    public Account getAccount() {
        return account;
    }
    
}
