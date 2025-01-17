package com.learn.userauthentication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "RoleTable")
public class Roles extends BaseModel {
    private String value;
}
