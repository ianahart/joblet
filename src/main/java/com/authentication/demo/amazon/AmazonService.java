package com.authentication.demo.amazon;

import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AmazonService {
    private AmazonS3 s3client;

    @Value("${amazon.s3.default-bucket}")
    private String defaultRegion;

    @Value("${amazon.aws.access-key-id}")
    private String accessKeyId;

    @Value("${amazon.aws.access-key-secret}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-1")
                .withCredentials(getAwsCredentialPovider())
                .build();
    }

    private AWSCredentialsProvider getAwsCredentialPovider() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId,
                secretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String createFileName(String fileName) {
        UUID uuid = UUID.randomUUID();
        return uuid + fileName;
    }

    public void delete(String bucketName, String fileName) {
        this.s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }

    public String upload(String path,
            String fileName,
            MultipartFile file) {
        try {
            // 2MB
            if (file.getSize() > 2097152) {
                throw new AmazonServiceException("File size has exceeded the limit");
            }
            File myFile = convertMultiPartToFile(file);
            String newFileName = createFileName(fileName);

            this.s3client.putObject(path, newFileName, myFile);
            this.s3client.setObjectAcl(path, newFileName, CannedAccessControlList.PublicRead);
            myFile.delete();
            return newFileName;
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public Map<String, String> getPublicUrl(String bucketName, String fileName) {
        this.s3client.setObjectAcl(bucketName, fileName,
                CannedAccessControlList.PublicRead);
        URL url = this.s3client.getUrl(bucketName, fileName);
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("url", url.toString());
        hm.put("fileName", fileName);
        return hm;
    }

    public byte[] downloadFile(String bucketName, String fileName) {
        S3Object s3Object = this.s3client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
