# Aplikasi Penyimpanan Barang

## Gambaran Umum
Aplikasi penyimpanan barang ini adalah aplikasi desktop berbasis Java yang memungkinkan pengguna untuk mengelola inventaris barang. Aplikasi ini menyediakan fitur untuk menambah, mengedit, menghapus, dan mencari barang dalam inventaris. Data disimpan secara persisten menggunakan serialisasi, memastikan bahwa inventaris disimpan dan dimuat secara otomatis.

---

## Fitur

### 1. **Menambah Barang**
Pengguna dapat menambah barang baru ke dalam inventaris dengan memberikan nama barang, jenis, jumlah, dan kode. Barang tersebut disimpan baik dalam memori maupun dalam file.

### 2. **Mengedit Barang**
Pengguna dapat mencari barang berdasarkan nama dan kode, kemudian memperbarui detailnya (nama, jenis, jumlah, dan kode).

### 3. **Menghapus Barang**
Pengguna dapat menghapus barang dari inventaris dengan memberikan nama barang. Jika barang ditemukan, maka barang tersebut akan dihapus dari memori dan file.

### 4. **Mencari Barang**
Pengguna dapat mencari barang berdasarkan nama. Jika ditemukan, detail barang (nama, jenis, jumlah, dan kode) akan ditampilkan.

### 5. **Penyimpanan Persisten**
Aplikasi ini menggunakan serialisasi untuk menyimpan dan memuat inventaris ke dan dari file (`inventory.dat`). Hal ini memastikan bahwa data tetap ada meskipun aplikasi ditutup.

---

## Komponen

### 1. **Kelas Item**
Kelas `Item` mewakili barang dalam inventaris dengan atribut berikut:
- `nama`: Nama barang
- `jenis`: Jenis barang
- `jumlah`: Jumlah barang
- `kode`: Kode barang

Kelas ini mengimplementasikan `Serializable` agar objek bisa disimpan ke dalam file.

### 2. **Kelas InventoryData**
Kelas `InventoryData` mengelola inventaris dan menyediakan metode untuk:
- Menambah barang (`addItem`)
- Mengambil daftar barang yang sudah diurutkan (`getItems`)
- Memperbarui barang (`updateItem`)
- Menghapus barang (`deleteItem`)
- Mencari barang berdasarkan nama (`findItem`)

Kelas ini menangani operasi file untuk menyimpan dan memuat data secara persisten menggunakan serialisasi.

### 3. **Panel AddItem**
Antarmuka pengguna grafis (GUI) untuk menambah barang baru ke inventaris. Pengguna dapat memasukkan detail barang dan mengklik "Tambah" untuk menyimpan barang.

### 4. **Panel EditItem**
Antarmuka pengguna grafis untuk mengedit barang yang sudah ada. Pengguna dapat mencari barang berdasarkan nama dan kode, memperbarui detailnya, dan menyimpan perubahan.

### 5. **Panel DeleteItem**
Antarmuka pengguna grafis untuk menghapus barang berdasarkan nama. Pengguna dapat mengklik "Hapus" untuk menghapus barang dari inventaris.

### 6. **Panel SearchItem**
Antarmuka pengguna grafis untuk mencari barang berdasarkan nama. Jika ditemukan, detail barang akan ditampilkan dalam area teks.

---

## Cara Menjalankan Aplikasi

### Prasyarat
- Java Development Kit (JDK) 8 atau lebih tinggi
- IDE seperti IntelliJ IDEA, Eclipse, atau NetBeans

### Langkah-langkah
1. Clone atau unduh kode sumber.
2. Buka proyek di IDE yang Anda pilih.
3. Kompilasi dan jalankan kelas utama (belum disediakan, namun harus mengintegrasikan panel GUI).
4. Gunakan GUI untuk menambah, mengedit, menghapus, dan mencari barang.

---

## Contoh Penggunaan

### Menambah Barang
1. Buka panel Tambah Barang.
2. Masukkan detail barang (nama, jenis, jumlah, dan kode).
3. Klik "Tambah" untuk menyimpan barang.

### Mengedit Barang
1. Buka panel Edit Barang.
2. Cari barang menggunakan nama dan kode.
3. Perbarui bidang yang diinginkan dan klik "Perbarui" untuk menyimpan perubahan.

### Menghapus Barang
1. Buka panel Hapus Barang.
2. Masukkan nama barang.
3. Klik "Hapus" untuk menghapus barang dari inventaris.

### Mencari Barang
1. Buka panel Cari Barang.
2. Masukkan nama barang.
3. Klik "Cari" untuk menampilkan detail barang.

---

## Persistensi Data
- Data inventaris disimpan ke dalam file bernama `inventory.dat` menggunakan serialisasi.
- Data dimuat secara otomatis saat aplikasi dimulai, memastikan barang yang telah disimpan sebelumnya tersedia.

---

## Pengembangan di Masa Depan
- Implementasi menu utama untuk menavigasi antara panel.
- Menambahkan validasi untuk input pengguna (misalnya, kolom tidak boleh kosong, angka yang valid).
- Dukungan untuk mencari berdasarkan kolom lain (misalnya, kode).
- Meningkatkan UI dengan framework desain modern seperti JavaFX.
- Menambahkan fungsionalitas ekspor/impor inventaris dalam format CSV atau JSON.

---

## Penulis
Program ini dikembangkan untuk mendemonstrasikan konsep dasar manajemen inventaris dalam Java, termasuk pengembangan GUI dan penanganan file.

---

## Lisensi
Proyek ini bersifat open-source dan dapat digunakan secara bebas untuk pembelajaran atau modifikasi.
