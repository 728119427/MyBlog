package com.seven.myblog.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tag {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}