package system;

import java.util.Comparator;

import post.Post;

/**
 * Comparator Class to sort posts.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class ComparatorSortPosts implements Comparator<Post> {

	/**
	 * Sort posts by reverse number of comments per post and, when ties occur,
	 * by alphabetic order of author id, followed by decreasing id for the post.
	 */
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