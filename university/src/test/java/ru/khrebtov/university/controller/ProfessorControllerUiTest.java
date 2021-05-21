package ru.khrebtov.university.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/init_db_test.sql")
@TestPropertySource("/db.properties")
class ProfessorControllerUiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get("/professors"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("//*[@id='th_name']").string("Name"))
                .andExpect(xpath("//*[@id='th_address']").string("Address"))
                .andExpect(xpath("//*[@id='th_phone']").string("Phone"))
                .andExpect(xpath("//*[@id='th_payment']").string("Payment"))
                .andExpect(xpath("//*[@id='th_actions']").string("Actions"))
                .andExpect(xpath("//*[@id='1']/a").string("Амосов В.П."))
                .andExpect(xpath("//*[@id='2']/a").string("Герберт А.А."));
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/professors/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("//*[@id='1']").string("Амосов В.П."))
                .andExpect(xpath("//*[@id='2']").doesNotExist());
    }

    @Test
    void newProfessor() throws Exception {
        this.mockMvc.perform(get("/professors/new"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("/html/body/div/div/div/form/label[1]").string("Enter name: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[2]").string("Enter address: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[3]").string("Enter phone number: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[4]").string("Enter payment: "));
    }

    @Test
    void create() throws Exception {
        MockHttpServletRequestBuilder postMethod = post("/professors")
                .param("name", "Новый профессор")
                .param("address", "Новая ул")
                .param("phone", "+7333")
                .param("payment", "332");

        this.mockMvc.perform(postMethod)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void update() throws Exception {
        MockHttpServletRequestBuilder putMethod = put("/professors")
                .param("id", "40")
                .param("name", "Обновленный профессор")
                .param("address", "Новая ул")
                .param("phone", "+7333")
                .param("payment", "332");

        this.mockMvc.perform(putMethod)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void edit() throws Exception {
        this.mockMvc.perform(get("/professors/1/edit"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("/html/body/div/div/div/form/label[1]").string("Enter name: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[2]").string("Enter address: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[3]").string("Enter phone number: "))
                .andExpect(xpath("/html/body/div/div/div/form/label[4]").string("Enter payment: "))
                .andExpect(xpath("//*[@id='name']").exists())
                .andExpect(xpath("//*[@id='address']").exists())
                .andExpect(xpath("//*[@id='phone']").exists())
                .andExpect(xpath("//*[@id='payment']").exists()
                );
    }

    @Test
    @Sql("/before_delete.sql")
    void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/professors/{id}","3"))
                .andDo(print()).
                andExpect(status().is3xxRedirection());
    }
}