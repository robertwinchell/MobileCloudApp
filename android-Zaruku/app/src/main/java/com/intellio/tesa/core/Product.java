package com.intellio.tesa.core;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by stone_000 on 7/29/2014.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -6641292855569752036L;

    private String id;
    private Double price;
    private Date date_created;
    private Date date_updated;
    private String[] receipts;


    private String problem_description;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(Date date_purchase) {
        this.date_purchase = date_purchase;
    }

    public Date getDate_warranty() {
        return date_warranty;
    }

    public void setDate_warranty(Date date_warranty) {
        this.date_warranty = date_warranty;
    }

    private Date date_purchase;
    private Date date_warranty;
    private String name;
    private String content;
    private String objectId;

    public String getTitle() {
        return name;
    }

    public void setTitle(final String title) {
        this.name = title;
    }

    public String getContent() {
        return problem_description;
    }

    public void setContent(final String content) {
        this.problem_description = content;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(final String objectId) {
        this.objectId = objectId;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public String[] getReceipts() {
        return receipts;
    }

    public void setReceipts(String[] receipts) {
        this.receipts = receipts;
    }

    public String getProblem_description() {
        return problem_description;
    }

    public void setProblem_description(String problem_description) {
        this.problem_description = problem_description;
    }
}
