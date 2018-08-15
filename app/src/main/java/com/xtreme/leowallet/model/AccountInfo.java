
package com.xtreme.leowallet.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfo {

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("invoices")
    @Expose
    private List<Invoice> invoices = null;
    @SerializedName("favorites")
    @Expose
    private List<Favorite> favorites = null;
    @SerializedName("last")
    @Expose
    private List<Last> last = null;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Last> getLast() {
        return last;
    }

    public void setLast(List<Last> last) {
        this.last = last;
    }

}
