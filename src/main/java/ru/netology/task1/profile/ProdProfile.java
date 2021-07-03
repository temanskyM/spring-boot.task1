package ru.netology.task1.profile;

public class ProdProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}