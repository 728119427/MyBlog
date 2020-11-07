package com.seven.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO implements Comparable<TagDTO> {
    private Long id;
    private String name;
    private Integer priority;

    @Override
    public int compareTo(TagDTO tagDTO) {
        int num1=   tagDTO.priority- this.priority ;
        int num2 = num1==0?this.name.compareTo(tagDTO.name):num1;
        return num2;
    }
}
