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
 * Interface `ItemsRepository` menyediakan fungsi-fungsi untuk mengelola data `Item`.
 * Repository ini bertanggung jawab sebagai penghubung antara sumber data dan lapisan aplikasi,
 * memungkinkan akses ke data melalui operasi seperti mendapatkan, menambahkan, menghapus, dan memperbarui item.
 * Semua fungsi pengambilan data diimplementasikan dengan `Flow` untuk memungkinkan pembaruan data secara real-time.
 */

interface ItemsRepository {

    /*
     * Mengambil semua item dari sumber data sebagai aliran (Flow) daftar `Item`.
     * Setiap kali ada perubahan data dalam daftar, Flow akan mengirimkan pembaruan baru ke subscriber.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /*
     * Mengambil item tertentu berdasarkan ID dari sumber data, dan mengembalikan hasilnya sebagai aliran (Flow).
     * Flow mengirimkan data `Item` terbaru yang cocok dengan ID yang diberikan atau `null` jika item tidak ditemukan.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /*
     * Menyisipkan `Item` baru ke dalam sumber data secara asinkron.
     * Fungsi ini menambahkan entri item baru ke sumber data.
     */
    suspend fun insertItem(item: Item)

    /*
     * Menghapus `Item` tertentu dari sumber data secara asinkron.
     * Item yang dihapus adalah yang cocok dengan entri `Item` yang diberikan.
     */
    suspend fun deleteItem(item: Item)

    /*
     * Memperbarui data `Item` dalam sumber data secara asinkron.
     * Fungsi ini menggantikan data item yang ada dengan data item baru yang diberikan.
     */
    suspend fun updateItem(item: Item)
}
