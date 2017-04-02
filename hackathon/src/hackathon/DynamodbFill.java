/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackathon;

/**
 *
 * @author Ranjan Kumar
 */
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Users")
public class DynamodbFill {
    

private String id;
@DynamoDBHashKey(attributeName="id")

public String getid() {
	return id;
}

public void setid(String uid) {
	id = uid;
}

public String getlatitude() {
	return latitude;
}

public void setlatitude(String ulatitude) {
	latitude = ulatitude;
}

public String getlongitude() {
	return longitude;
}

public void setlongitude(String ulongitude) {
	longitude = ulongitude;
}



@DynamoDBAttribute(attributeName="latitude")
private String latitude;

@DynamoDBAttribute(attributeName="longitude")
private String longitude;

}