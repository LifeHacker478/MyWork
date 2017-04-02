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
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;


public class Getfroms3 {
	private static String bucketName = "recommendedbucket"; 
	private static String key        = "users.txt";  
	private static AWSCredentials credentials = new AWSCredentials() {
			  public String getAWSSecretKey() { return "GKU8BoEdGNTFJoBQNjqEljE9cfaVwIANOMfDq+v1"; }
			  public String getAWSAccessKeyId() { return "AKIAJUTPRABHXJVTHRZA"; }
			};
        private static AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
       
        private static DynamoDBMapper mapper = new DynamoDBMapper(ddb);
	public static void main(String[] args) throws IOException {
             
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        //AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider("inhack2hire28"));
        try {
            System.out.println("Downloading an object");
            S3Object s3object = s3Client.getObject(new GetObjectRequest(
            		bucketName, key));
            System.out.println("Content-Type: "  + 
            		s3object.getObjectMetadata().getContentType());
            displayTextInputStream(s3object.getObjectContent());
            
           // Get a range of bytes from an object.
            
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(
            		bucketName, key);
            rangeObjectRequest.setRange(0, 10);
            S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
            
            System.out.println("Printing bytes retrieved.");
            displayTextInputStream(objectPortion.getObjectContent());
            
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
            		" means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means"+
            		" the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    private static void displayTextInputStream(InputStream input)
    throws IOException {
    	// Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new 
        		InputStreamReader(input));
        String line=reader.readLine();
        line=reader.readLine();
        while (true) {
             line = reader.readLine();
             String B = "";
              String A[] = new String[3];
		         int z = 0;
		         for(int i = 0; i<line.length(); ++i) {
		        	 char x = line.charAt(i);
                                 if(x==' ')
                                     continue;
                                 else if(x=='|'){
                                     if(!("".equals(B)))
                                     {
                                         A[z++] = B;
		        		 B = "";
                                     }
                                     continue;
                                 }
                                 B+=x;
                         }
                         if(!("".equals(B)))
                                     {
                                         A[z++] = B;
		        		 B = "";
                                     }
            System.out.println(A[0]+ " " +A[1] + " " + A[2]);
            if(A[0] == null || A[0].isEmpty() || A[1] == null || A[1].isEmpty() || A[2] == null || A[2].isEmpty())
                continue;
            System.out.println("    " + line);
            fillUser(A[0],A[1],A[2]);
        //System.out.println();
    }
    }
    private static void fillUser(String a, String b,String c) {
        DynamodbFill dynamodbFill = new DynamodbFill();
        dynamodbFill.setid(a);
        dynamodbFill.setlatitude(b);
        dynamodbFill.setlongitude(c);
        mapper.save(dynamodbFill);
    }
}