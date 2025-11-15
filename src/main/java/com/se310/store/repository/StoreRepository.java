package com.se310.store.repository;

import com.se310.store.data.DataManager;
import com.se310.store.model.Store;

import java.util.*;

/**
 * Store Repository implements Repository Pattern for store data access layer
 * Uses DataManager for persistent storage
 *
 * This repository is completely database-agnostic - it has no knowledge of SQL,
 * ResultSets, or SQLExceptions. All database-specific logic is encapsulated in DataManager.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-11-06
 */
public class StoreRepository {

    //TODO: Implement Store persistence layer using Repository Pattern

    private final DataManager dataManager;

    public StoreRepository(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}