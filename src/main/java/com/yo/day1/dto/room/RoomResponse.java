package com.yo.day1.dto.room;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomResponse {
    private Long id;

    private  String roomCode;


    private String name;

    private int capacity;

    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
