package com.saurav.cms.comms.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class ClaimResponse {
    private String username;
    private ArrayList<String> permission;
    private String role;
}
