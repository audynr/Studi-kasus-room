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

package com.example.inventory

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost



/*
 * Fungsi utama `InventoryApp` yang mewakili layar-layar dalam aplikasi.
 * Fungsi ini membuat instance `NavHostController` untuk navigasi antar layar dan memanggil `InventoryNavHost`
 * yang bertanggung jawab untuk mengatur rute-rute navigasi di dalam aplikasi.
 *
 * @param navController Instance `NavHostController` untuk mengelola navigasi di aplikasi,
 * default menggunakan `rememberNavController()`.
 */
@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}

/*
 * Fungsi `InventoryTopAppBar` membuat tampilan App Bar pada bagian atas aplikasi,
 * yang menampilkan judul serta ikon navigasi kembali jika diperlukan.
 *
 * @param title Judul yang akan ditampilkan pada App Bar.
 * @param canNavigateBack Boolean yang menentukan apakah ikon navigasi kembali akan ditampilkan.
 * @param modifier Modifier opsional untuk penyesuaian tampilan App Bar.
 * @param scrollBehavior Opsi perilaku scroll untuk App Bar, bisa berupa null jika tidak diperlukan.
 * @param navigateUp Lambda yang akan dipanggil saat tombol kembali ditekan.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },  // Menampilkan teks judul di tengah App Bar
        modifier = modifier,       // Modifier untuk kustomisasi tampilan
        scrollBehavior = scrollBehavior, // Mengatur perilaku scroll App Bar jika diperlukan
        navigationIcon = {
            if (canNavigateBack) {  // Menampilkan ikon kembali hanya jika `canNavigateBack` bernilai true
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,  // Ikon panah kembali
                        contentDescription = stringResource(string.back_button)  // Deskripsi untuk aksesibilitas
                    )
                }
            }
        }
    )
}
