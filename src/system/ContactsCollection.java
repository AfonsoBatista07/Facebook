package system;

import user.User;

public interface ContactsCollection {
	
	void addFriend(User user);
	User getFriend(String id);
	int size();
}
