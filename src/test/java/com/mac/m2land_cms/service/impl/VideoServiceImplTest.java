package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.VideoNotFoundException;
import com.mac.m2land_cms.model.dto.*;
import com.mac.m2land_cms.model.entity.Video;
import com.mac.m2land_cms.repository.UserRepository;
import com.mac.m2land_cms.repository.VideoRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class VideoServiceImplTest {
    @Mock
    private VideoRepository videoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private PageUtils pageUtils;

    @Mock
    private PageUtils pageUtilsMock;

    @InjectMocks
    private VideoServiceImpl videoService;


    @Before
    public void setUp() {
        when(messageUtils.getMessage(any(), any())).thenReturn("Error message");
    }

    @Test
    public void findAll() {
        LocalDateTime dateTime = LocalDateTime.now();

        //Actual
        Pageable sizePagination = PageRequest.of(0, 5);
        Video video = new Video();
        video.setId(1l);
        video.setVideoLink("an1233.com");
        video.setCreatedDate(dateTime);

        List<Video> videoList = new ArrayList<>();
        videoList.add(video);
        Page<Video> videoPageActual = new PageImpl<>(videoList, sizePagination, videoList.size());
        PageResDto<VideoDto> pageResDtoActual = pageUtils.convertPageEntityToDTO(videoPageActual, VideoDto.class);

        //Expected
        ContentPageDto contentPageDto = new ContentPageDto(1,1,5,0);
        VideoDto videoDto = new VideoDto(1l, dateTime, "an1233.com");
        List<VideoDto> videoDtos = new ArrayList<>();
        videoDtos.add(videoDto);
        PageResDto<VideoDto> pageResDto = new PageResDto<>(videoDtos, contentPageDto);

        when(videoRepository.findAll(sizePagination)).thenReturn(videoPageActual);
        when(pageUtilsMock.convertPageEntityToDTO(videoPageActual, VideoDto.class)).thenReturn(pageResDtoActual);
        videoService.findAll(sizePagination);

        assertEquals(pageResDto, pageResDtoActual);
    }
    @Test
    public void addVideo() {
        String link = "andong123.com";
        Video video = new Video();
        video.setVideoLink(link);
        video.setCreatedDate(LocalDateTime.now());
        videoService.addVideo(video.getVideoLink());
    }

    @Test
    public void deleteVideo() {
        Long videoId = 1l;
        String link = "andong123.com";
        Video video = new Video();
        video.setId(1l);
        video.setVideoLink(link);
        video.setCreatedDate(LocalDateTime.now());
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));
        videoService.deleteVideo(videoId);
    }

    @Test(expected = VideoNotFoundException.class)
    public void deleteVideoNotFound() {
        Long videoId = 123l;
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());
        videoService.deleteVideo(videoId);
    }
}
