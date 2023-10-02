package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.VideoNotFoundException;
import com.mac.m2land_cms.model.dto.PageResDto;
import com.mac.m2land_cms.model.dto.VideoDto;
import com.mac.m2land_cms.model.entity.Video;
import com.mac.m2land_cms.repository.VideoRepository;
import com.mac.m2land_cms.service.inf.VideoService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import com.mac.m2land_cms.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The class VideoServiceImpl.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);
    private VideoRepository videoRepository;
    private PageUtils pageUtils;
    private MessageUtils messageUtils;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, PageUtils pageUtils, MessageUtils messageUtils) {
        this.videoRepository = videoRepository;
        this.pageUtils = pageUtils;
        this.messageUtils = messageUtils;
    }

    /**
     * findAll
     * @param pageable
     * @return PageResDto<VideoDto>
     */
    @Override
    public PageResDto<VideoDto> findAll(Pageable pageable) {
        Page<Video> list = videoRepository.findAll(pageable);
        PageResDto<VideoDto> page = pageUtils.convertPageEntityToDTO(list, VideoDto.class);
        return page;
    }

    /**
     * addVideo
     * @param link
     */
    @Override
    public void addVideo(String link) {
        Video video = new Video();
        video.setVideoLink(link);
        video.setCreatedDate(LocalDateTime.now());
        videoRepository.save(video);
    }

    /**
     * deleteVideo
     * @param id
     */
    @Override
    public void deleteVideo(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()) {
            videoRepository.deleteById(id);
        } else {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER041, null));
            throw new VideoNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER041, null));
        }

    }
}
