package com.example.gacha_kelompok;

public class Student {
    String name;
    String email;
    String nim;
    String phone;

    public Student(String name, String email, String nim, String phone) {
        this.name = name;
        this.email = email;
        this.nim = nim;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
