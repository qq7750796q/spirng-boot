package com.zyl.design.domain;

import com.zyl.design.enums.TravelScopeEnum;

/**
 * Created by z1761 on 2018/10/11.
 */
public class Taxi {

    private TravelScopeEnum travelScope;

    public void setTravelScope(TravelScopeEnum travelScope) {
        this.travelScope = travelScope;
    }

    public TravelScopeEnum getTravelScope() {
        return this.travelScope;
    }

}
