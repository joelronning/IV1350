package model;

import userDTO.UserDTO;

class User {
    private String name;
    // more properties
    
    public User(UserDTO userData) {
        this.name = userData.getName();
        // set other properties
    }
}
