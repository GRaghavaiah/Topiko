package com.Topiko.UserMasterModels;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="mobile" , nullable = false)
    private long mobile;

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="longitude" ,nullable = false)
    private String longitude;

    @Column(name="latitude" ,nullable = false)
    private String latitude;


    @Column(name="ip_address",nullable = false)
    private String ip_address;

    @Column(name="pincode",nullable = false)
    private String pincode;

    @Column(name="area",nullable = false)
    private String area;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name="country",nullable = false)
    private String country;

    @Column(name="password",nullable = false)
    private String password;
    @Column(name="address")
    private String address;

    @Column(name="OS")
    private String OS;

    @Column(name="email")
    private String email;

    @Column(name="notification_id")
    private String notification_id;

    @Column(name="notification_status")
    private int notification_status;



    @Column(name="sponser")
    private String sponser;

    @Column(name="referral_code")
    private String referral_code;

    @Column(name="is_into_business" , columnDefinition = "bigint default 0")
    private int is_into_business;


    @Column(name="created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String created_date;

    @Column(name="call_state" ,nullable = false)
    private int call_state;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public int getNotification_status() {
        return notification_status;
    }

    public void setNotification_status(int notification_status) {
        this.notification_status = notification_status;
    }

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public int getIs_into_business() {
        return is_into_business;
    }

    public void setIs_into_business(int is_into_business) {
        this.is_into_business = is_into_business;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getCall_state() {
        return call_state;
    }

    public void setCall_state(int call_state) {
        this.call_state = call_state;
    }

}
