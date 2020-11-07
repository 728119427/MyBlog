package com.seven.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO implements Comparable<TypeDTO>{
    private Long id;
    private String name;
    private Integer priority;

    @Override
    public int compareTo(TypeDTO typeDTO) {
        int num1=  typeDTO.priority - this.priority ;
        int num2 = num1==0?this.name.compareTo(typeDTO.name):num1;
        return num2;
    }
}
