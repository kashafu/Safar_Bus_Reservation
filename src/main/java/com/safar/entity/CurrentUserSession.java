package com.safar.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@DynamoDBTable(tableName = "currentUserSession")
public class CurrentUserSession {

    @DynamoDBHashKey(attributeName = "userID")
    private Integer userID;

    @DynamoDBAttribute(attributeName = "uuid")
    private String uuid;

    @DynamoDBAttribute(attributeName = "time")
    private LocalDateTime time;
}
