package com.swap281.model.item;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Indexed;

@Entity
@Indexed
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", length = 256)
    public String title;

    @Column(name = "create_date")
    public Date createDate = new Date();

    @Column(name = "category_id")
    public int categoryId;

    @Column(name = "description")
    public String description;

    @Column(name = "price")
    public BigDecimal price;

    @Column(name = "user_id")
    public long user_id;

    @Column(name = "availability")
    public boolean availability = true;

    @Column(name = "trade_method_id")
    public long trade_method_id = 4;
    
    @Column(name = "condition_id")
    public long condition_id;

    @Column(name = "thumbnail")
    public byte[] thumbnail;

    public Item(String title, Date createDate, int categoryId, String description, BigDecimal price, long user_id,
            boolean availability, long trade_method_id, long condition_id, byte[] thumbnail) {
        this.title = title;
        this.createDate = createDate;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.user_id = user_id;
        this.availability = availability;
        this.trade_method_id = trade_method_id;
        this.condition_id = condition_id;
        this.thumbnail = thumbnail;
    }

    public Item() {

    }
}