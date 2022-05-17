package com.shareclub.TwitterCloneBackendJava.s3;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class S3 {

    private final static Region region = Region.US_EAST_2;

    private final static String bucketName = "share-club-test-bucket";
    private final static String key = "test-key";

    private final static String test_file_path = "C:\\Users\\redth\\Desktop\\me.jpg";

    private Bucket bucket;

    private S3Client s3;

    public S3 () {
        this.s3 = S3Client.builder().region(region).build();

        if (doesBucketExist()){
            log.info("Bucket Exits; Getting Bucket...");
            this.bucket = getBucket(bucketName);
        }
        else {
            bucketSetup(s3, bucketName, region);
            this.bucket = getBucket(bucketName);
        }

    }

    private boolean doesBucketExist() {
        for (Bucket b : s3.listBuckets().buckets()) {
            if (b.name().equals(bucketName)) {
                return true;
            }
        }

        return false;
    }

    private static void uploadFile () {

    }

    private byte[] getFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bytesArray;
    }

    public String TestUpload () {
        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .metadata(metadata)
                    .build();

            PutObjectResponse response = s3.putObject(request, RequestBody.fromBytes(getFile(test_file_path)));

            return response.eTag();
        }catch (S3Exception e) {
            log.error(e.toString());
        }

        return "Error";
    }

    private BufferedImage TestDownload () {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            byte[] response = s3.getObject(request).readAllBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(response);
            BufferedImage image = ImageIO.read(bis);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new BufferedImage(10, 10, 1);
    }

    private Bucket getBucket(String bucket) {

        Bucket return_bucket = null;

        List<Bucket> bucketList = s3.listBuckets().buckets();

        for (Bucket b : bucketList) {
            if (b.name().equals(bucket)){
                return_bucket = b;
            }
        }

        return return_bucket;

    }

    private void bucketSetup(S3Client client, String bucketName, Region region){
        try {

            Bucket b = null;
             client.createBucket(CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(
                            CreateBucketConfiguration.builder()
                                    .locationConstraint(region.id())
                                    .build())
                    .build());
            System.out.println("Creating bucket: " + bucketName);
            client.waiter().waitUntilBucketExists(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            System.out.println(bucketName + " is ready");
            System.out.println("----------------");
        }catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

}
