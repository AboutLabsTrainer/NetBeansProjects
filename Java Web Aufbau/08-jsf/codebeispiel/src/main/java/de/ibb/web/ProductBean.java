package de.ibb.web;

import de.ibb.domain.Product;
import de.ibb.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * View-Scoped JSF-Bean. Hält Produktliste und Formular und delegiert Laden und Speichern an ProductService.
 */
@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    private ProductService service;

    private List<Product> products;
    private Product current = new Product();
    private String search;

    @PostConstruct
    public void init() {
        products = service.findAll();
        // Load product for editing if id parameter is present
        String idParam = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Long id = Long.parseLong(idParam);
                Product p = service.find(id);
                if (p != null) {
                    current = p;
                }
            } catch (NumberFormatException e) {
                // Invalid ID, ignore
            }
        }
    }

    public void search() {
        products = service.searchByName(search);
    }

    public String edit(Long id) {
        if (id != null) {
            Product p = service.find(id);
            if (p != null) {
                current = p;
                return "product-edit.xhtml?faces-redirect=true&id=" + id;
            }
        }
        current = new Product();
        return "product-edit.xhtml?faces-redirect=true";
    }

    public String save() {
        service.save(current);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "Product gespeichert"));
        products = service.findAll();
        current = new Product();
        return "product-list.xhtml?faces-redirect=true";
    }

    public String cancel() {
        current = new Product();
        return "product-list.xhtml?faces-redirect=true";
    }

    public void delete(Long id) {
        service.delete(id);
        products = service.findAll();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", "Product gelöscht"));
    }

    // getters/setters
    public List<Product> getProducts() { return products; }
    public Product getCurrent() { return current; }
    public void setCurrent(Product current) { this.current = current; }
    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }
}
