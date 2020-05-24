package system;

import java.util.Comparator;

import post.Post;

public class ComparatorSortPosts implements Comparator<Post> {

	public int compare(Post a, Post b) {
		if(a.getNumComments() < b.getNumComments()) return 1;
		if(a.getNumComments() > b.getNumComments()) return -1;  // Passar os gets para apenas 1
		
		int i = a.getAuthorId().compareTo(b.getAuthorId());
		if(i != 0) return i;
		
		if(a.getIdPost() < b.getIdPost()) return 1;
		if(a.getIdPost() > b.getIdPost()) return -1;
		
		return 0;
		
	}
}