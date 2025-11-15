package com.se310.store.controller;

import com.se310.store.dto.StoreMapper;
import com.se310.store.dto.StoreMapper.StoreDTO;
import com.se310.store.model.Store;
import com.se310.store.model.StoreException;
import com.se310.store.service.StoreService;
import com.se310.store.servlet.BaseServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * REST API controller for Store operations
 * Implements full CRUD operations using DTO Pattern
 *
 * DTOs are used to:
 * - Simplify API responses by excluding complex nested collections
 * - Provide a clean separation between internal domain models and external API contracts
 * - Improve JSON serialization performance by excluding transient fields
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-11
 */
public class StoreController extends BaseServlet {

    //TODO: Implement Controller for Store operations, part of the MVC Pattern

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Handle GET requests - Returns StoreDTO objects
     * - GET /api/v1/stores (no parameters) - Get all stores
     * - GET /api/v1/stores/{storeId} - Get store by ID
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle POST requests - Create new store, returns StoreDTO
     * POST /api/v1/stores?storeId=xxx&name=xxx&address=xxx
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle PUT requests - Update existing store, returns StoreDTO
     * PUT /api/v1/stores/{storeId}?description=xxx&address=xxx
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    /**
     * Handle DELETE requests - Delete store
     * DELETE /api/v1/stores/{storeId}
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}