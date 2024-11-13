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
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
 * Kelas `InventoryDatabase` adalah representasi database Room untuk aplikasi inventaris.
 * Database ini berisi tabel `Item` yang didefinisikan oleh data class `Item` dan diakses
 * melalui `ItemDao`. `InventoryDatabase` adalah kelas abstrak yang memperluas `RoomDatabase`,
 * menyediakan fungsi akses data (DAO) untuk tabel yang ada dalam database.
 *
 * Komponen `companion object` dalam kelas ini menyediakan metode `getDatabase` yang memastikan
 * adanya satu instance database yang dapat diakses dari seluruh aplikasi. Database hanya dibuat
 * satu kali menggunakan pola Singleton untuk menghindari duplikasi instance.
 *
 * `@Volatile` menandakan bahwa variabel `Instance` dapat diakses secara konsisten di semua thread,
 * yang menjamin visibilitas dan mencegah pembentukan instance ganda.
 *
 * `getDatabase` menggunakan `synchronized` untuk memastikan bahwa hanya satu instance dari database
 * yang dibuat dalam aplikasi. Jika `Instance` sudah ada, metode akan mengembalikannya; jika tidak,
 * akan dibuat instance baru menggunakan `Room.databaseBuilder`.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Mendefinisikan metode untuk akses ke DAO `ItemDao`
    abstract fun itemDao(): ItemDao

    companion object {
        // Variabel `Instance` menampung satu-satunya instance dari database ini
        @Volatile
        private var Instance: InventoryDatabase? = null

        /*
         * Fungsi `getDatabase` mengembalikan instance dari `InventoryDatabase`.
         * Jika instance belum ada, fungsi ini akan membuat database baru
         * menggunakan `Room.databaseBuilder`, menyimpannya di `Instance`, dan
         * kemudian mengembalikannya. Fungsi ini memastikan bahwa hanya ada satu instance
         * database yang aktif sepanjang waktu aplikasi berjalan.
         */
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build().also { Instance = it }
            }
        }
    }
}
