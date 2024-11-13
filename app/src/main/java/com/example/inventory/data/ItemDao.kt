package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
 * Interface `ItemDao` adalah Data Access Object (DAO) untuk mengelola operasi database terkait entitas `Item`.
 * DAO ini memfasilitasi interaksi dengan tabel "items" di database Room, memungkinkan operasi CRUD (Create, Read, Update, Delete).
 *
 * Setiap fungsi dijalankan dalam coroutine atau sebagai aliran (Flow) untuk mendukung operasi asinkron yang lebih efisien.
 */

@Dao
interface ItemDao {

    /*
     * Memasukkan objek `Item` baru ke dalam tabel "items".
     * Jika terdapat konflik (misalnya ID item yang sama), operasi ini akan diabaikan.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /*
     * Memperbarui data dari objek `Item` yang sudah ada di tabel.
     * ID item yang diberikan harus cocok dengan ID item yang akan diperbarui.
     */
    @Update
    suspend fun update(item: Item)

    /*
     * Menghapus entri `Item` tertentu dari tabel "items".
     * Entri yang dihapus adalah yang cocok dengan objek item yang diberikan.
     */
    @Delete
    suspend fun delete(item: Item)

    /*
     * Mengambil entri `Item` berdasarkan ID item tertentu dan mengembalikannya sebagai aliran data (Flow).
     * Aliran ini akan diperbarui setiap kali ada perubahan pada entri dengan ID yang sama.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /*
     * Mengambil semua entri `Item` dalam tabel "items" dan mengurutkannya berdasarkan nama dalam urutan naik.
     * Mengembalikan hasil sebagai aliran data (Flow) yang terus memperbarui setiap ada perubahan data.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
