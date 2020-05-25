package system;

import java.util.Comparator;

import post.Post;

public class ComparatorPopularPost implements Comparator<Post> {
	
	public int compare(Post post, Post popularPost) {
		if(popularPost == null || post.getNumComments() > popularPost.getNumComments()) return 1;
		if(post.getNumComments() == popularPost.getNumComments()) { 
			if(post.getAuthorId().compareTo(popularPost.getAuthorId()) < 0) return 1;
			if(post.getAuthorId().compareTo(popularPost.getAuthorId()) == 0)
				if(post.getIdPost() > popularPost.getIdPost()) return 1;	
		}
		return 0;
	}
	
}
