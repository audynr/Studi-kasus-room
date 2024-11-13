/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import android.content.Context

/*
 * Antarmuka `AppContainer` yang berfungsi sebagai container utama untuk dependency injection.
 * Ini menyediakan akses ke instance `ItemsRepository` untuk digunakan di berbagai bagian aplikasi.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

/*
 * Implementasi `AppContainer` yang memberikan instance `OfflineItemsRepository`.
 * `AppDataContainer` menggunakan dependency injection untuk menyediakan instance repository secara efisien.
 * Dengan menggunakan `lazy` initialization, instance `ItemsRepository` hanya akan dibuat saat pertama kali diakses.
 *
 * @property context Konteks aplikasi, digunakan untuk mengakses database.
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /*
     * Instance dari `ItemsRepository` yang diinisialisasi menggunakan `lazy`.
     * Repository ini menggunakan `OfflineItemsRepository` yang mengakses data dari `InventoryDatabase` melalui `itemDao()`.
     * Mengambil instance database melalui fungsi `getDatabase()` memastikan database hanya dibuat satu kali.
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}

