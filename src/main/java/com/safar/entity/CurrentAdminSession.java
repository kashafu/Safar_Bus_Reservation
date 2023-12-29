package com.safar.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "currentAdminSession")
public class CurrentAdminSession {

    @DynamoDBHashKey(attributeName = "adminID")
    private Integer adminID;

    @DynamoDBAttribute(attributeName = "aid")
    private String aid;

    @DynamoDBAttribute(attributeName = "time")
    private LocalDateTime time;
}