package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.controller.ArtistController;
import com.affinityartgallary.artgallary.serviceImpImpl.ArtistServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ArtistServiceImpl artistService;

//    @Test
//    void testPostRequest() throws Exception {
//        mockMvc.perform(post("/addArtist"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/plain;charset=UTF-8"))
//                .
//    }



}
