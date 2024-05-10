package ru.ananta.sales;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "arrival_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;
    @Column(name = "sale_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saleDate;

    public Sale() {

    }

    public Sale(BigDecimal amount, int productId, Date arrivalDate, Date saleDate) {
        this.amount = amount;
        this.productId = productId;
        this.arrivalDate = arrivalDate;
        this.saleDate = saleDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", amount=" + amount +
                ", productId=" + productId +
                ", arrivalDate=" + arrivalDate +
                ", saleDate=" + saleDate +
                '}';
    }
}
