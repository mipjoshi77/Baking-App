package com.example.recipecookbook.idlingresource;

public class IdlingResourceUtils {

    private static SimpleIdlingResource idlingResource;

    public static SimpleIdlingResource getIdlingResource() {
        //Check that the simpleIdlingResource instance is not null
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }


    public static void setIdlingResourceState(boolean isIdle) {
        //Check that the simpleIdlingResource is not null
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }

        idlingResource.setIdleState(isIdle);
    }
}
