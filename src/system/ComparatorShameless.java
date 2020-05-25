package system;

import java.util.Comparator;
import java.util.LinkedList;

import user.User;

public class ComparatorShameless implements Comparator<User>{
	
	private LinkedList<User> topLiars;
	
	public ComparatorShameless() {
		super();
		topLiars = new LinkedList<User>();
	}
	@Override
	public int compare(User user, User shameless) {
		if(shameless == null || user.getNumberOfLies() > shameless.getNumberOfLies()) {
			topLiars.clear();
			return 1;
		}
		if (user.getNumberOfLies() == shameless.getNumberOfLies()) {
			if(!topLiars.contains(user)) topLiars.add(user);
			if(!topLiars.contains(shameless)) topLiars.add(shameless);
			int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
			for( User liar : topLiars) {
				int liarSum = liar.getTotalNumberComments() + liar.getNumberPosts();
				if(liarSum < shamelessSum) shameless = liar;
			}
			int userSum = user.getTotalNumberComments() + user.getNumberPosts();	
			if(userSum < shamelessSum) return 1;
			if(userSum == shamelessSum && user.getId().compareTo(shameless.getId()) > 0) return 1;
		}
		return -1;
	}

}
