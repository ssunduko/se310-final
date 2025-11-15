package com.se310.store.controller;

import com.se310.store.dto.UserMapper;
import com.se310.store.dto.UserMapper.UserDTO;
import com.se310.store.model.User;
import com.se310.store.service.AuthenticationService;
import com.se310.store.servlet.BaseServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * REST API controller for User operations
 * Implements full CRUD operations using DTO Pattern
 *
 * DTOs are used to:
 * - Hide sensitive information (passwords) from API responses
 * - Provide a clean separation between internal domain models and external API contracts
 * - Allow API responses to evolve independently from internal data structures
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-11
 */
public class UserController extends BaseServlet {

    //TODO: Implement Controller for User operations, part of the MVC Pattern

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Handle GET requests - Returns UserDTO objects (without passwords)
     * - GET /api/v1/users (no parameters) - Get all users
     * - GET /api/v1/users/{email} - Get user by email
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle POST requests - Register new user, returns UserDTO (without password)
     * POST /api/v1/users?email=xxx&password=xxx&name=xxx&role=xxx
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle PUT requests - Update user information, returns UserDTO (without password)
     * PUT /api/v1/users/{email}?password=xxx&name=xxx
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle DELETE requests - Delete user
     * DELETE /api/v1/users/{email}
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}