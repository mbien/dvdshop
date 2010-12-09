package de.dvdsoft.shop.presentation;

import de.dvdsoft.shop.business.boundary.ItemService;
import de.dvdsoft.shop.business.boundary.LoginService;
import de.dvdsoft.shop.business.entity.Account;
import de.dvdsoft.shop.business.entity.Media;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.enterprise.inject.Model;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author mbien
 */
@Model
public class Index {
    
    @EJB
    ItemService itemService;
    
    private Search search = new Search();
    
    private ListDataModel items = null;
    
 
    // login service is a managed bean
    private Account getAccount() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ValueExpression binding = application.getExpressionFactory().createValueExpression(facesContext.getELContext(), "#{loginService}", LoginService.class);
        LoginService service = (LoginService)binding.getValue(facesContext.getELContext());
        Account account = service.getAccount();
        return account;
    }
    
    public void find() {
        items = itemService.findItems(search.text);
    }
    
    public void reserveItem() {
        
        Account account = getAccount();
        Media item = (Media)items.getRowData();
        
        itemService.reserve(account, item);
    }
    
    public void cancelReservation() {
        
        Account account = getAccount();
        Media item = (Media)items.getRowData();
        
        itemService.cancelReservation(account, item);
    }

    public boolean isItemReserved() {
        
        Account account = getAccount();
        Media item = (Media)items.getRowData();

        return itemService.isReserved(account, item);
    }
    
    public boolean isAvailable() {
        Media item = (Media) items.getRowData();
        return itemService.isAvailable(item);
    }
    
    public boolean isReserveable() {
        return isAvailable() && !isItemReserved();
    }
    
    public boolean isNotAvailableAndNotReserved(){
        return !isAvailable() && !isItemReserved();
    }

    public DataModel getItems() {
        if(items == null) {
            items = new ListDataModel(itemService.findAll());
        }
        return items;
    }

    public Search getSearch() {
        return search;
    }
    
    public static class Search {
        
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
        
    }
    
}
