package system;

import java.util.Comparator;

import post.Post;

/**
 * Comparator Class for popular post.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class ComparatorPopularPost implements Comparator<Post> {
	
	/**
	 * @param popularPost - Popular post.
	 * @param post - Post.
	 * @return 1 if post post has more comments than the popularPost, if there is a tie,
	 * 1 if post is more recent than the popularPost.
	 */
	public int compare(Post post, Post popularPost) {
		if(popularPost == null || post.getNumComments() > popularPost.getNumComments()) return 1;
		if(post.getNumComments() == popularPost.getNumComments()) { 
			if(post.getAuthorId().compareTo(popularPost.getAuthorId()) < 0) return 1;
			if(post.getAuthorId().compareTo(popularPost.getAuthorId()) == 0)
				if(post.getIdPost() > popularPost.getIdPost()) return 1;	
		}
		return -1;
	}
	
}
