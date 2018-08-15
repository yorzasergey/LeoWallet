package com.xtreme.leowallet.model;

public class HeaderItem extends ListItem {

    private String mHeaderName;

    public HeaderItem(String headerName){
        mHeaderName = headerName;
    }

    @Override
    public int getListItemType() {
        return TYPE_HEADER;
    }

    public String getHeaderName() {
        return mHeaderName;
    }
}
