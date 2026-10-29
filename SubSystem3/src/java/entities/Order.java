/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aco
 */
@Entity
@Table(name = "\"ORDER\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
    @NamedQuery(name = "Order.findByIdOrder", query = "SELECT o FROM Order o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Order.findByIdUser", query = "SELECT o FROM Order o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "Order.findByTotalPrice", query = "SELECT o FROM Order o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Order.findByTime", query = "SELECT o FROM Order o WHERE o.time = :time"),
    @NamedQuery(name = "Order.findByAddress", query = "SELECT o FROM Order o WHERE o.address = :address"),
    @NamedQuery(name = "Order.findByIdCity", query = "SELECT o FROM Order o WHERE o.idCity = :idCity")})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOrder")
    private Integer idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private float totalPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdCity")
    private int idCity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder")
    private Collection<Suborder> suborderCollection;

    public Order() {
    }

    public Order(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Order(Integer idOrder, int idUser, int totalPrice, Date time, String address, int idCity) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.totalPrice = totalPrice;
        this.time = time;
        this.address = address;
        this.idCity = idCity;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @XmlTransient
    public Collection<Suborder> getSuborderCollection() {
        return suborderCollection;
    }

    public void setSuborderCollection(Collection<Suborder> suborderCollection) {
        this.suborderCollection = suborderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Order[ idOrder=" + idOrder + " ]";
    }
    
}
