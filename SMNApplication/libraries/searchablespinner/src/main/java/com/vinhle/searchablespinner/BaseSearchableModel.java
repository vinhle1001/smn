package com.vinhle.searchablespinner;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/8/2017.
 */

public abstract class BaseSearchableModel implements Serializable {

    public abstract Integer getId();

    public abstract String getDisplayText();

    public abstract String getSubTitle();

    @Override
    public String toString() {
        return getDisplayText();
    }
}
