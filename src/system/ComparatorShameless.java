package system;

import java.util.Comparator;

import user.User;

public class ComparatorShameless implements Comparator<User>{

	@Override
	public int compare(User user, User shameless) {
		int i = shameless.getNumberOfLies() - user.getNumberOfLies();
		if(i != 0) return i;

		int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
		int userSum = user.getTotalNumberComments() + user.getNumberPosts();
		
		i = userSum - shamelessSum;
		if(i != 0) return i;
		
		i = user.getId().compareTo(shameless.getId());
		if(i != 0) return i;

		return 0;
	}
}
