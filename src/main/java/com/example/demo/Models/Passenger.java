package com.example.demo;

public class Passenger {
    private Integer pass_id;
    private String name;
    private String adress;
    private String tel;

    public Passenger(int pass_id, String name, String adress, String tel) {
        this.pass_id = pass_id;
        this.name = name;
        this.adress = adress;
        this.tel = tel;
    }
    public Integer getId() { return pass_id;}
    public String getName() { return name;}
    public String getAdress() { return adress;}
    public String getTel() { return tel;}
}
