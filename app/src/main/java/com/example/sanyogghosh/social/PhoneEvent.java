package com.example.sanyogghosh.social;

/**
 * Created by Sanyog Ghosh on 19-07-2017.
 */
public class PhoneEvent {
    public String name;
    public String phone_office;
    public String phone_personal;



    public PhoneEvent(String name,String phone_office,String phone_personal)
    {
        this.name=name;
        this.phone_office=phone_office;
        this.phone_personal=phone_personal;


    }

    /**
     * Get the name of the Android version
     */
    public String phone_personal() {
        return phone_personal;
    }
    public String phone_office() {
        return phone_office;
    }
    public String  name() {
        return name;
    }

}
