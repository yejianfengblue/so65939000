package com.example.so65939000sdrcustompathrel;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class So65939000SdrCustomPathRelApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @Test
    @SneakyThrows
    void getAll() {

        mockMvc.perform(get("/types"))
               .andExpect(status().isOk())
               .andDo(print());  // print response to log
    }

    @Test
    @SneakyThrows
    void create() {

        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        assertThat(deviceTypes).isEmpty();

        mockMvc.perform(post("/types")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{ \"name\":\"iphone1000\", \"description\":\"iphone2000\" }"))
               .andExpect(status().isCreated())
               .andDo(print());  // print response to log

        // verify
        deviceTypes = deviceTypeRepository.findAll();
        assertThat(deviceTypes).hasSize(1);
        DeviceType deviceType0 = deviceTypes.get(0);
        assertThat(deviceType0.getName()).isEqualTo("iphone1000");
        assertThat(deviceType0.getDescription()).isEqualTo("iphone2000");

        mockMvc.perform(get("/types"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("_embedded.types").isArray())
               .andExpect(jsonPath("_embedded.types", hasSize(1)))
               .andExpect(jsonPath("_embedded.types[0].name").value("iphone1000"))
               .andExpect(jsonPath("_embedded.types[0].description").value("iphone2000"))
               .andDo(print());  // print response to log
    }

}
