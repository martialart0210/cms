package com.mac.m2land_cms.service.impl;

import com.mac.m2land_cms.Exceptions.InventoryNotFoundException;
import com.mac.m2land_cms.model.response.InventoryResponse;
import com.mac.m2land_cms.repository.InventoryRepository;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * The Class InventoryServiceImplTest.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private MessageUtils messageUtils;

    @InjectMocks
    private InventoryServiceImpl inventoryService;


    @Before
    public  void setUp(){
        when(messageUtils.getMessage(any(),any())).thenReturn("Error message");
    }

    @Test
    public void getInventoryByUserIdSuccess(){
        Long idUser = 123L;
        List<Optional<Object>> mockItemList = new ArrayList<>();
        Object[] item1 = {1L, "Item 1", 10L};
        Object[] item2 = {2L, "Item 2", 5L};
        mockItemList.add(Optional.of(item1));
        mockItemList.add(Optional.of(item2));
        when(inventoryRepository.getItemByUserId(idUser)).thenReturn(mockItemList);
        List<InventoryResponse> result = inventoryService.getInventoryByUserId(idUser);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals(10L, result.get(0).getCount().longValue());
    }


    @Test(expected = InventoryNotFoundException.class)
    public void getInventoryByUserIdNotFound(){
        Long idUser = 123L;
        when(inventoryRepository.getItemByUserId(idUser)).thenReturn(new ArrayList<>());
        inventoryService.getInventoryByUserId(idUser);
    }

    @Test
    public void testMapToInventoryResponse_WithValidInput_ShouldReturnInventoryResponse() {
        Object[] item = {1L, "Item 1", 10L};

        InventoryResponse result = inventoryService.mapToInventoryResponse(Optional.of(item));

        assertEquals(1L, result.getId().longValue());
        assertEquals("Item 1", result.getName());
        assertEquals(10L, result.getCount().longValue());
    }

    @Test
    public void testMapToInventoryResponse_WithInvalidInput_ShouldReturnNull() {
        Object[] invalidItem = {};

        InventoryResponse result = inventoryService.mapToInventoryResponse(Optional.of(invalidItem));

        assertEquals(null, result);
    }
}
