package com.xtreme.leowallet.model;

public abstract class DashboardItem extends ListItem{

    public abstract String getItemName();

    public abstract String getItemImageFileName();

    public abstract String getItemAccount();

    public boolean isFavorite(){
        return false;
    }

    public String getItemService(){
        return "Default service";
    }
}
