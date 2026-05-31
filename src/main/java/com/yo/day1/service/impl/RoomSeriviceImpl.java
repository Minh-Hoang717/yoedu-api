package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Room;
import com.yo.day1.dto.room.RoomResponse;
import com.yo.day1.dto.room.RoomUpsertRequest;
import com.yo.day1.repository.RoomRepository;
import com.yo.day1.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomSeriviceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    private RoomResponse map(Room room) {
        var result = mapper.map(room, RoomResponse.class);
        return result;
    }

    public List<RoomResponse>  findAll(){
        return roomRepository.findAll().stream()
        .map(r -> map(r))
                .toList();
    }

    public Optional<RoomResponse> findById(Long id){
        return roomRepository.findById(id).map(this::map);
    }

    public RoomResponse save(RoomUpsertRequest req){
        Room room = mapper.map(req,Room.class);
        Room respone = roomRepository.save(room);
        return map(respone);
    }

    public RoomResponse save(Long id, RoomUpsertRequest req){
        Room room = mapper.map(req,Room.class);
        room.setId(id);
        Room respone = roomRepository.save(room);
        return map(respone);
    }
}
