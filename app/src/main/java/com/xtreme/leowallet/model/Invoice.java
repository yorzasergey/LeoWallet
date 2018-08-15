
package com.xtreme.leowallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice extends DashboardItem{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("account")
    @Expose
    private String account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public int getListItemType(){
        return TYPE_WITH_NAME;
    }

    @Override
    public String getItemAccount() {
        return getAccount();
    }

    @Override
    public String getItemImageFileName() {

        return "service_default";
    }

    @Override
    public String getItemName() {
        return getName();
    }
}
