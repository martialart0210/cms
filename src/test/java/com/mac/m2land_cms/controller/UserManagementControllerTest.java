package com.mac.m2land_cms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mac.m2land_cms.model.dto.*;
import com.mac.m2land_cms.service.inf.UserManagementService;
import com.mac.m2land_cms.utils.MessageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The Class UserManagementControllerTest.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserManagementControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private UserManagementController userManagementController;

    @Mock
    private MessageUtils messageUtils;

    @Mock
    private UserManagementService userManagementService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userManagementController).build();
    }

    @Test
    public void getAll() throws Exception {
        Pageable sizePagination = PageRequest.of(0, 5);
        PageResDto<AllUserManagementDto> responses = new PageResDto<>();
        when(userManagementService.findAll(sizePagination)).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/userManagement/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("size","5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(userManagementService,times(1)).findAll(sizePagination);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void addUser() throws Exception {
        UserCreateDto userCreateDto = new UserCreateDto("an", "testtest12345", "test123", "Admin");
        ObjectMapper MAPPER = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/userManagement/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(userCreateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userManagementService,times(1)).addUser(userCreateDto);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void updateUser() throws Exception {
        UserEditDto userEditDto = new UserEditDto(1l,"an", "testtest12345", "test123", "Admin");
        ObjectMapper MAPPER = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.put("/userManagement/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(userEditDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userManagementService,times(1)).updateUser(userEditDto);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/userManagement/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userManagementService,times(1)).deleteUser(1l);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void getSuspension() throws Exception {
        Pageable sizePagination = PageRequest.of(0, 5);
        PageResDto<UserSuspensionDto> responses = new PageResDto<>();
        when(userManagementService.findAllSuspension(sizePagination)).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/userManagement/get-suspension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("size","5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(userManagementService,times(1)).findAllSuspension(sizePagination);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void findAllAccess() throws Exception {
        Pageable sizePagination = PageRequest.of(0, 5);
        PageResDto<AllAccessUsersDto> responses = new PageResDto<>();
        when(userManagementService.findAllAccess(sizePagination)).thenReturn(responses);

        mockMvc.perform(MockMvcRequestBuilders.get("/userManagement/getAllAccess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page","0")
                        .param("size","5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(responses));
        verify(userManagementService,times(1)).findAllAccess(sizePagination);
        verifyNoMoreInteractions(userManagementService);
    }

    @Test
    public void addAccess() throws Exception {
        int maximum = 35;
        Long id = 1l;

        mockMvc.perform(MockMvcRequestBuilders.post("/userManagement/addAccess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("maximum", "35")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userManagementService,times(1)).addAccessUser(maximum, id);
        verifyNoMoreInteractions(userManagementService);
    }

}
