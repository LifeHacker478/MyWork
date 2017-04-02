package hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;



public class DataPopulate {
//	private static String bucketName = "recommendedbucket"; 
//	private static String key  = "users.txt";  
	
	static AWSCredentials credentials = new AWSCredentials() {
		  public String getAWSSecretKey() { return "MUJrsVDZaW5DEep501p9wNyIc61YCRuqYoWk3RhI"; }
		  public String getAWSAccessKeyId() { return "AKIAIADBIPN3UUWYHAZA"; }
		};
	
	
	public static AmazonS3 clientBuilder() {
	
	
		
     AmazonS3 s3Client  = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
     
     return s3Client;
     
	}
	
	void fillUserf() {
		AmazonS3 s3Client = clientBuilder();
		 AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
	                .withRegion(Regions.AP_SOUTHEAST_1)
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .build();
		 DynamoDBMapper mapper = new DynamoDBMapper(ddb);
		 
		 
		 try {
			 S3Object s3object = s3Client.getObject(new GetObjectRequest(
					 "recommendedbucket", "users.txt"));
			 System.out.println("Content-Type: "  + 
	            		s3object.getObjectMetadata().getContentType());
			 InputStream input = s3object.getObjectContent();
			 BufferedReader reader = new BufferedReader(new 
		        		InputStreamReader(input));
			 int i = 0 , j = 0;
			 while (true) {
				 String line = reader.readLine();
				 if(j<2){j++;continue;}
				 String A = "",B = "", C = "";
				 i = 0;
				 for(;i < line.length();++i) {
					if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
					break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 A += line.charAt(i);
					
					 }
					 else {
						 break;
					 }
				 }
				 for(;i < line.length();++i) {
						if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
						break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 B += line.charAt(i);
					 }
					 else {
						 break;
					 }
				 }
				 for(;i < line.length();++i) {
						if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
						break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 C += line.charAt(i);
					 }
					 else {
						 break;
					 }
				 }
				// System.out.println(line);
				 System.out.println(A + " " + B + " " + C); 
				 User U = new User();
				 U.setId(A);
				 U.setLatitude(B);
				 U.setLongitude(C);
				 try {
				   mapper.save(U);
				 }
				 catch(Exception E) {
						System.out.println(E);
				 }
				 
		     }
			 
		 }
		catch(Exception E) {
			System.out.println(E);
		}
	}
	
	void fillSocialf() {
		AmazonS3 s3Client = clientBuilder();
		 AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
	                .withRegion(Regions.AP_SOUTHEAST_1)
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .build();
		 DynamoDBMapper mapper = new DynamoDBMapper(ddb);
		 
		 
		 try {
			 S3Object s3object = s3Client.getObject(new GetObjectRequest(
					 "recommendedbucket", "socialgraph.txt"));
			 System.out.println("Content-Type: "  + 
	            		s3object.getObjectMetadata().getContentType());
			 InputStream input = s3object.getObjectContent();
			 BufferedReader reader = new BufferedReader(new 
		        		InputStreamReader(input));
			 int i = 0 , j = 0;
			 while (true) {
				 String line = reader.readLine();
				 if(j<2){j++;continue;}
				 String A = "",B = "";
				 i = 0;
				 for(;i < line.length();++i) {
						if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
						break;
					 }
					 for(;i < line.length();++i) {
						 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
							 A += line.charAt(i);
						
						 }
						 else {
							 break;
						 }
					 }
					 for(;i < line.length();++i) {
							if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
							break;
					 }
					 for(;i < line.length();++i) {
						 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
							 B += line.charAt(i);
						 }
						 else {
							 break;
						 }
					 }
					 Social S = new Social();
					 S.setId1(A);
					 S.setId2(B);
					 System.out.println(line);
					// j++;
				//	if(j>10) break;
					 System.out.println(A + " " + B);
					 try {
					    mapper.save(S);
					 }
					 catch(Exception E){
						System.out.println(E); 
					 }
			 }
		 }
		 catch(Exception E){
			 System.out.println(E);
		 }
	}
	
	void fillratingf() {
		AmazonS3 s3Client = clientBuilder();
		 AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
	                .withRegion(Regions.AP_SOUTHEAST_1)
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .build();
		 DynamoDBMapper mapper = new DynamoDBMapper(ddb);
		 
		 
		 try {
			 S3Object s3object = s3Client.getObject(new GetObjectRequest(
					 "recommendedbucket", "ratings.txt"));
			 System.out.println("Content-Type: "  + 
	            		s3object.getObjectMetadata().getContentType());
			 InputStream input = s3object.getObjectContent();
			 BufferedReader reader = new BufferedReader(new 
		        		InputStreamReader(input));
			 int i = 0 , j = 0;
			 while (true) {
				 String line = reader.readLine();
				 if(j<2){j++;continue;}
				 String A = "",B = "", C = "";
				 i = 0;
				 for(;i < line.length();++i) {
					if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
					break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 A += line.charAt(i);
					
					 }
					 else {
						 break;
					 }
				 }
				 for(;i < line.length();++i) {
						if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
						break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 B += line.charAt(i);
					 }
					 else {
						 break;
					 }
				 }
				 for(;i < line.length();++i) {
						if(line.charAt(i) == ' ' || line.charAt(i) == '|') continue;
						break;
				 }
				 for(;i < line.length();++i) {
					 if(line.charAt(i) != ' ' && line.charAt(i) != '|') {
						 C += line.charAt(i);
					 }
					 else {
						 break;
					 }
				 }
				// System.out.println(line);
				 System.out.println(A + " " + B + " " + C); 
				 Rating U = new Rating();
				 U.setUser_id(A);
				 U.setVenue_id(B);
				 U.setRating(C);
				 try {
				   mapper.save(U);
				 }
				 catch(Exception E) {
						System.out.println(E);
				 }
				 
		     }
			 
		 }
		catch(Exception E) {
			System.out.println(E);
		}
	}
	
	
	public static void main(String args[])
	{
		DataPopulate z = new DataPopulate();
		z.fillratingf();
		z.fillSocialf();
		z.fillUserf();
		
	}
}
