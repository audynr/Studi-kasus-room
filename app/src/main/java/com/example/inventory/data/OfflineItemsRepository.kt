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

import kotlinx.coroutines.flow.Flow

/*
 * Kelas `OfflineItemsRepository` yang mengimplementasikan antarmuka `ItemsRepository`.
 * Repository ini bertugas untuk mengelola operasi basis data secara offline menggunakan `ItemDao` sebagai sumber data utama.
 * Menggunakan `Flow` untuk menyediakan akses data secara asinkron dan real-time.
 *
 * @property itemDao Instance dari `ItemDao` yang menyediakan akses ke operasi database.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /*
     * Mengambil semua item dalam database dan mengembalikannya sebagai aliran data (`Flow`).
     * Data akan disusun secara terurut berdasarkan nama item.
     *
     * @return `Flow` berisi daftar semua `Item`.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /*
     * Mengambil satu item dari database yang sesuai dengan ID yang diberikan.
     *
     * @param id ID dari item yang ingin diambil.
     * @return `Flow` berisi item yang sesuai dengan ID, atau `null` jika tidak ditemukan.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /*
     * Menyisipkan item baru ke dalam database.
     *
     * @param item Item yang akan ditambahkan.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /*
     * Menghapus item dari database.
     *
     * @param item Item yang akan dihapus.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /*
     * Memperbarui data item yang ada dalam database.
     *
     * @param item Item dengan data terbaru yang akan disimpan.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
