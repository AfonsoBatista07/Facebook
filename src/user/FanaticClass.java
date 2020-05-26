package user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import post.Comment;
import post.Post;
import user.exceptions.InadequateStanceException;
import user.exceptions.InvalidCommentStanceException;

public class FanaticClass extends UserClass implements Fanatic{
	
	private List<String> fanaticisms;
	private Map<String, String> tags;
	private int numFanaticisms;
	private static final String LOVES = "loves";
	private static final String HATES = "hates";
	
	public FanaticClass(String id, int numFanaticisms, LinkedList<String> hashTags) {
		super(id, FANATIC);
		this.numFanaticisms = numFanaticisms;
		tags = new HashMap<String, String>();
		fanaticisms = new LinkedList<String>();
		separateTags(hashTags);
		listOnlyTags(hashTags);
	}
	
	public void newPost(Post post) {
		Iterator<String> it = post.getHashTags();
		while (it.hasNext()) {
			String tag = it.next();
			if((post.isHonest() && hates(tag)) || (!post.isHonest() && loves(tag)) || !hasFanaticism(tag) ) throw new InadequateStanceException();
		}
		super.newPost(post);
	}
	
	private void listOnlyTags(LinkedList<String> hashTags) {
		for(int i = 0; i < numFanaticisms; i++) {
			String tag = hashTags.get(1 + 2*i);
			fanaticisms.add(tag);
		}
	}
	
	public Iterator<String> getFanaticisms() {
		return fanaticisms.iterator();
	}
	
	public void newComment(Comment comment) {
		Post post = comment.getPost();
		boolean found = false;
		Iterator<String> fan = fanaticisms.iterator();
		while(fan.hasNext()) {
			String fanaticism = fan.next();
			Iterator<String> it = post.getHashTags(); 			// it ????????????
			while (it.hasNext() && found == false) {
				String tag = it.next();					//Separate and fix it later
				if(tag.equals(fanaticism)) {
					found = true;
					if(loves(fanaticism)) {
						if((!post.isHonest() && comment.isPositive()) || (post.isHonest() && !comment.isPositive())) throw new InvalidCommentStanceException();
					}
					if(hates(fanaticism)) {
						if((!post.isHonest() && !comment.isPositive()) || (post.isHonest() && comment.isPositive())) throw new InvalidCommentStanceException();
					}
				}
			}
		}
		super.newComment(comment);
	}
	
	private void separateTags(LinkedList<String> hashTags) {
		Iterator<String> it = hashTags.iterator();
		for(int i = 0; i < numFanaticisms; i++){
			String value = it.next();
			String tag = it.next();
			tags.put(tag, value);
		}
	}
	
	private boolean loves(String love) {
		return tags.get(love).equals(LOVES);
	}
	
	private boolean hates(String hate) {
		return tags.get(hate).equals(HATES);
	}
	
	private boolean hasFanaticism(String fanaticism) {
		if(tags.get(fanaticism) == null) return false;
		else return true;
	}
}
