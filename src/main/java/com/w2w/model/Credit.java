package com.w2w.model;


import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class Credit {
    int movieID;
    String title;
    List<Cast> casts = new ArrayList<>();
}
