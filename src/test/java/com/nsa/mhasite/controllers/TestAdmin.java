package com.nsa.mhasite.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class TestAdmin {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnAdminPortal() throws Exception {
        this.mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Enter Volunteers name")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnVolunteerRegistration() throws Exception {
        this.mockMvc.perform(get("/admin/register")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Register a Volunteer")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnVolunteerApplicants() throws Exception {
        this.mockMvc.perform(get("/admin/applicants")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Applicants")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnCreateAdmins() throws Exception {
        this.mockMvc.perform(get("/admin/createAdmins")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Create an Administrator Account")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnAddReward() throws Exception {
        this.mockMvc.perform(get("/admin/rewards")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Reward Name")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnRewardSearch() throws Exception {
        this.mockMvc.perform(get("/admin/searchForRewards")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Enter Reward Name")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnStatistics() throws Exception {
        this.mockMvc.perform(get("/admin/rewards/statistics")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Reward Statistics Page")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnAddBusiness() throws Exception {
        this.mockMvc.perform(get("/admin/business")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Business Name")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnChangeBusiness() throws Exception {
        this.mockMvc.perform(get("/admin/change_business_info")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Change Business Email")));
    }

    @WithMockUser(authorities = "ROLE_ADMIN")
    @Test
    public void shouldReturnDeleteBusiness() throws Exception {
        this.mockMvc.perform(get("/admin/deletebusiness")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Business Name")));
    }
}
