package com.eappcat.demo.sign;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private int age;
    private Gender gender;
}
