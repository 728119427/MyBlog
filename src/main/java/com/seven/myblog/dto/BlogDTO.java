package com.seven.myblog.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BlogDTO {

    private String title;
    private Long typeId;
    private boolean recommend;
    private Long blogId;
    private Long tagId;
}
