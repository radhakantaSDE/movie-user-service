package com.movie.app.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String emailId;
    private String password;

    private String firstName;
    private String lastName;
    private String mobile;
}
