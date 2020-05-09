package system;
import java.util.LinkedList;

import post.Post;
import post.PostClass;

public class PostCollectionClass implements PostCollection{
	private LinkedList<Post> posts;
	
	public PostCollectionClass() {
		posts =  new LinkedList<Post>();
	}
	
	
	@Override
	public void newPost(String userId, int numHashTags, String[] hashtags, String type, String message) {
		posts.addLast(new PostClass(userId, posts.size()+1, numHashTags, hashtags, type, message));
		
	}

	@Override
	public void newComment(String idComment, String idPost, String stance, String comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		return posts.size();
	}

	@Override
	public Post getPost(String id, String idPost) {
		// TODO Auto-generated method stub
		return null;
	}

}
