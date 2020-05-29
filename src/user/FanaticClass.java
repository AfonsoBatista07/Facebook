package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import post.Comment;
import post.Post;
import user.exceptions.InadequateStanceException;
import user.exceptions.InvalidCommentStanceException;

/**
* Implements a Fanatic User.
* @author Afonso Batista 57796
* @author Joao Jorge 57994
*/
public class FanaticClass extends UserClass implements Fanatic {
	
	private List<String> fanaticisms;
	private Map<String, String> tags;
	private int numFanaticisms;
	private static final String LOVES = "loves";
	private static final String HATES = "hates";
	
	/**
	 * Constructor of the FanaticClass, initializes variables.
	 * @param id - User Id
	 * @param numFanaticisms - Number of Fanaticisms
	 * @param hashTags - List of HashTags
	 */
	public FanaticClass(String id, int numFanaticisms, List<String> hashTags) {
		super(id, FANATIC);
		this.numFanaticisms = numFanaticisms;
		tags = new HashMap<String, String>();
		fanaticisms = new ArrayList<String>(numFanaticisms);
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
	
	public Iterator<String> getFanaticisms() {
		return fanaticisms.iterator();
	}
	
	public void newComment(Comment comment) {
		Post post = comment.getPost();
		String fanaticism = getCommonHashTag(post);
		if(fanaticism == null) throw new InvalidCommentStanceException();
			if(loves(fanaticism)) {
				if((!post.isHonest() && comment.isPositive()) || (post.isHonest() && !comment.isPositive())) throw new InvalidCommentStanceException();
			}
				if(hates(fanaticism)) {
					if((!post.isHonest() && !comment.isPositive()) || (post.isHonest() && comment.isPositive())) throw new InvalidCommentStanceException();
			}
		super.newComment(comment);
	}
	
	/**
	 * @param post - Post
	 * @return The first HashTag the User is fanatic about that is also present on the Posts HashTags
	 */
	private String getCommonHashTag(Post post) {
		Iterator<String> fan = fanaticisms.iterator();
		while(fan.hasNext()) {
			String fanaticism = fan.next();
			Iterator<String> it = post.getHashTags();
			while (it.hasNext()) {
				String tag = it.next();
				if(tag.equals(fanaticism)) return fanaticism;
			}
		}
		return null;
	}
	
	/**
	 * Copies only the HashTags from the given List to another list.
	 * @param hashTags - List of HashTags with Loves and Hates
	 */
	private void listOnlyTags(List<String> hashTags) {
		for(int i = 0; i < numFanaticisms; i++) {
			String tag = hashTags.get(1 + 2*i);
			fanaticisms.add(tag);
		}
	}
	
	/**
	 * Copies the HashTags from the given list into a Map,
	 * with the HashTag as the key and Loves/Hates as the value.
	 * @param hashTags - - List of HashTags with Loves and Hates
	 */
	private void separateTags(List<String> hashTags) {
		Iterator<String> it = hashTags.iterator();
		for(int i = 0; i < numFanaticisms; i++){
			String value = it.next();
			String tag = it.next();
			tags.put(tag, value);
		}
	}
	
	/**
	 * Verifies is the given HashTag is Loved by the User.
	 * @param love - HashTag
	 * @return True if the User loves the HashTag. False if not.
	 */
	private boolean loves(String love) {
		return tags.get(love).equals(LOVES);
	}
	
	/**
	 * Verifies if the given HashTag is Hated by the User.
	 * @param hate - HashTag
	 * @return True if the User hates the HashTag. False if not.
	 */
	private boolean hates(String hate) {
		return tags.get(hate).equals(HATES);
	}
	
	/**
	 * Verifies if the User has a given fanaticism.
	 * @param fanaticism - fanaticism
	 * @return True if the User is fanatic about it. False if not.
	 */
	private boolean hasFanaticism(String fanaticism) {
		if(tags.get(fanaticism) == null) return false;
		else return true;
	}
}
