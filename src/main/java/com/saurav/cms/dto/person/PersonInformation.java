package com.saurav.cms.dto.person;

import lombok.*;

@Data
@Builder
public class PersonInformation {
    private Long userId;
    private String fullName;
}
