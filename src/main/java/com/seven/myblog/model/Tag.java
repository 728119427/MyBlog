package com.seven.myblog.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Tag {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}