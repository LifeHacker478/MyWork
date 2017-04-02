package hackathon;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Rating")
public class Rating {
      String user_id,venue_id,rating;
    
    @DynamoDBHashKey(attributeName = "user_id")
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
    
	@DynamoDBRangeKey(attributeName = "venue_id")
	public String getVenue_id() {
		return venue_id;
	}

	public void setVenue_id(String venue_id) {
		this.venue_id = venue_id;
	}
    
	@DynamoDBAttribute(attributeName = "rating")
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
