package system;

import java.util.Comparator;

import user.User;

public class ComparatorTopPoster implements Comparator<User> {
	
	public int compare(User user, User topPoster) {
		if(topPoster == null || user.getNumberPosts() > topPoster.getNumberPosts()) return 1;
		else if(user.getNumberPosts() == topPoster.getNumberPosts()) {
			if(user.getTotalNumberComments() > topPoster.getTotalNumberComments()) return 1;
			else if(user.getTotalNumberComments() == topPoster.getTotalNumberComments())
				if(user.getId().compareTo(topPoster.getId()) < 0) return 1;
		}
		return -1;
	}
}
