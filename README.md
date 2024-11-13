# UTS PBO 2 - Gusti Randa - IntelliJ IDEA Project - Sistem Informasi Izin Persetujuan Penyitaan Barang Bukti

**Personal Information:**
- Nama: Gusti Randa
- NPM: 2210010236
- Kelas: Reg 50 Pagi Banjarmasin

This project is an IntelliJ IDEA project that uses a MySQL database. The database schema and initial data are provided in the `pbo2_penyitaan.sql` file.

This project based from **Sistem Informasi Izin Persetujuan Penyitaan Barang Bukti Berbasis Web
Pada Pengadilan Negeri Tanjung Karang Kelas I A** Journal

and I use that for my final project in Pembangunan Berbasis Objek 2 (PBO 2) class.

## Features
- [x] Login
  - [x] Use Login Feature
- [x] Barang Bukti
  - [x] List Barang Bukti
  - [x] Create Barang Bukti
  - [x] Update Barang Bukti
  - [x] Delete Barang Bukti
- [x] Institusi
   - [x] List Institusi
   - [x] Create Institusi
   - [x] Update Institusi
   - [x] Delete Institusi

## Prerequisites

- IntelliJ IDEA
- MySQL Server
- JDK 17+

## Setup

1. **Clone the repository:**

   ```sh
   git clone https://github.com/vugn/pbo2_penyitaan
   cd pbo2_penyitaan
    ```
2. **Open the project in IntelliJ IDEA:**
   - Open IntelliJ IDEA.
   - Click on `File` -> `Open...`.
   - Select the `pbo2_penyitaan` directory and click `Open`.

3. **Create a new database:**
   - Open MySQL Workbench.
   - Connect to your MySQL server.
   - Open the `pbo2_penyitaan.sql` file.
   - Execute the script to create the database schema and initial data.

4. **Configure the database connection in IntelliJ IDEA:**
   - Go to File > Project Structure....
   - Select Modules > Dependencies.
   - Add the MySQL JDBC driver if not already added.
   - Configure the database connection in your project settings.

5. **Run the project:**
   - Go to Build > Build Project or use the shortcut Cmd+F9.
   - Run the project by selecting Run > Run... and choosing the main class.