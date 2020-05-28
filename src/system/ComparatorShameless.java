package system;

import java.util.Comparator;

import user.User;

public class ComparatorShameless implements Comparator<User>{

	@Override
	public int compare(User user, User shameless) {
		if(user.getNumberOfLies() < shameless.getNumberOfLies()) return 1;
		if(user.getNumberOfLies() > shameless.getNumberOfLies()) return -1;
		
		int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
		int userSum = user.getTotalNumberComments() + user.getNumberPosts();
			
		if(userSum > shamelessSum) return 1;
		if(userSum < shamelessSum) return -1;
		
		int i = user.getId().compareTo(shameless.getId());
		if(i != 0) return i;

		return 0;
	}

}
