package com.example.e_wallet.dto;

import com.example.e_wallet.models.Phone;
import com.example.e_wallet.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String name;

    private Integer age;

    private String email;

    private Phone phone;


    public User toUser() {
        User user = new User();
        user.setName(this.getName());
        user.setAge(this.getAge());
        user.setEmail(this.getEmail());

        //TODO: Handle null pointer exceptions elegantly
        user.setPhone(this.getPhone().getCountryCode() + this.getPhone().getNumber());

        return user;
    }
}
