package mapper;

import java.util.List;

import entity.user;

public interface userMapper {
	
	user getUserAndPassword(String username);
	
	String getContacts(String username);

}
