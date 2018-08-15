package com.xtreme.leowallet.model;

public abstract class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_WITHOUT_NAME = 1;
    public static final int TYPE_WITH_NAME = 2;
    /*public static final int TYPE_INVOICE = 2;
    public static final int TYPE_FAVORITE = 3;
    public static final int TYPE_LAST = 4;*/

    abstract public int getListItemType();
}
