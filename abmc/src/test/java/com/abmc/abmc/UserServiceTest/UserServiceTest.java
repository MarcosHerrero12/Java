package com.abmc.abmc.UserServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.abmc.abmc.Exceptions.ResourceNotFoundException;
import com.abmc.abmc.Persistence.UserMapper;
import com.abmc.abmc.Service.UserService;
import com.abmc.abmc.entities.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Users user1 = new Users(1, "Agustin","Herrero", "john@example.com");
        Users user2 = new Users(2, "Agustin", "Herrero","jane@example.com");
        when(userMapper.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<Users> users = userService.findAll();
        assertEquals(2, users.size());
        verify(userMapper, times(1)).findAll();
    }

    @Test
    public void testFindById_UserExists() {
        Users user = new Users(1, "Marcos","Herrero", "marcos@example.com");
        when(userMapper.findById(1)).thenReturn(user);

        Users foundUser = userService.findById(1);
        assertNotNull(foundUser);
        assertEquals("Marcos", foundUser.getName());
        verify(userMapper, times(1)).findById(1);
    }

    @Test
    public void testInsert() {
        Users user = new Users(1, "Marco", "Herrero","marcos@example.com");
        userService.insert(user);
        verify(userMapper, times(1)).insert(user);
    }

    @Test
    public void testDeleteById_UserExists() {
        Users user = new Users(1, "Marcos", "Herrero","agustin@example.com");
        when(userMapper.findById(1)).thenReturn(user);

        userService.deleteById(1);
        verify(userMapper, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteById_UserNotFound() {
        when(userMapper.findById(1)).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteById(1);
        });

        String expectedMessage = "Usuario no encontrado con ID: 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userMapper, times(1)).findById(1);
    }
}

