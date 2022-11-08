package userDTO;

public class UserDTO {
    private String name;
    // more properties
    
    public UserDTO(String args[]) {
        this.name = args[0];
        // set rest of properties
    }
    
    public String getName() {
        return this.name;
    }
}
