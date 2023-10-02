package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.InventoryNotFoundException;
import com.mac.m2land_cms.model.response.InventoryResponse;
import com.mac.m2land_cms.repository.InventoryRepository;
import com.mac.m2land_cms.service.inf.InventoryService;
import com.mac.m2land_cms.utils.CommonConstants;
import com.mac.m2land_cms.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Class InventoryServiceImpl.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final MessageUtils messageUtils;

    public InventoryServiceImpl(InventoryRepository repository, MessageUtils messageUtils) {
        this.repository = repository;
        this.messageUtils = messageUtils;
    }

    /**
     *
     * @param idUser
     * @return List<InventoryResponse>
     */
    @Override
    public List<InventoryResponse> getInventoryByUserId(Long idUser) {
        List<Optional<Object>> itemList = repository.getItemByUserId(idUser);
        if (itemList.isEmpty()) {
            log.error(messageUtils.getMessage(CommonConstants.MessageError.ER043, null));
            throw new InventoryNotFoundException(messageUtils.getMessage(CommonConstants.MessageError.ER043, null));
        }
        return itemList.stream()
                .map(this::mapToInventoryResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param optional
     * @return InventoryResponse
     */
    public InventoryResponse mapToInventoryResponse(Optional<Object> optional) {
        if (optional.isPresent()) {
            Object obj = optional.get();
            if (obj instanceof Object[]) {
                Object[] result = (Object[]) obj;
                if (result.length >= 3) {
                    Long id = (Long) result[0];
                    String name = (String) result[1];
                    Long count = (Long) result[2];

                    InventoryResponse response = new InventoryResponse();
                    response.setId(id);
                    response.setName(name);
                    response.setCount(count);
                    return response;
                }
            }
        }
        return null;
    }
}
