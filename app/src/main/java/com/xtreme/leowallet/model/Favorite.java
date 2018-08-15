
package com.xtreme.leowallet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite extends DashboardItem{

    @SerializedName("service")
    @Expose
    private Integer service;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("account1")
    @Expose
    private String account1;

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount1() {
        return account1;
    }

    public void setAccount1(String account1) {
        this.account1 = account1;
    }

    @Override
    public int getListItemType(){

        if (getName().trim().isEmpty()){
            return TYPE_WITHOUT_NAME;
        }else{
            return TYPE_WITH_NAME;
        }
    }

    @Override
    public String getItemService() {
        return String.valueOf(getService());
    }

    @Override
    public boolean isFavorite() {
        return true;
    }

    @Override
    public String getItemAccount() {
        return getAccount1();
    }

    @Override
    public String getItemImageFileName() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("service_");
        stringBuilder.append(getService());

        return  stringBuilder.toString();
    }

    @Override
    public String getItemName() {
        return getName();
    }
}
