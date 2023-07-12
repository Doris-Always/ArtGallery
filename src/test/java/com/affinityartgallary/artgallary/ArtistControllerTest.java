package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.controller.ArtistController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;


}
