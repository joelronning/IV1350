package controller;

import model.userDTO.UserDTO;

class Controller {
    public void registerUser(UserDTO user) {
        dbhandler.UserDB.storeUser(user);
        model.user.User(user);
    }
}
