# Parkir Tertib - Aplikasi Penataan Parkir Liar

Aplikasi Android untuk penegakan hukum parkir dan manajemen lokasi parkir resmi di Kota Bandung.

## 🚗 Fitur Utama

### 📊 Dashboard Penegakan
- Statistik pelanggaran parkir
- Monitoring lokasi parkir resmi
- Ringkasan operasi razia
- Data real-time pelanggaran

### 📍 Lokasi Parkir Resmi
- Daftar lokasi parkir resmi
- Informasi kapasitas dan ketersediaan
- Tarif parkir per jam
- Jam operasional
- Informasi kontak

### 🔔 Notifikasi Razia
- Pemberitahuan operasi razia
- Area terdampak
- Jadwal operasi
- Status razia aktif/selesai

### 💳 Pembayaran E-Parking
- Daftar pelanggaran yang belum dibayar
- Sistem pembayaran online
- Riwayat pembayaran
- Konfirmasi pembayaran

### 📸 Foto Bukti Pelanggaran
- Kamera terintegrasi
- Panduan foto yang jelas
- Upload bukti pelanggaran
- Multiple angle capture

## 🛠 Teknologi yang Digunakan

- **Kotlin** - Bahasa pemrograman utama
- **Jetpack Compose** - UI framework modern
- **Material Design 3** - Design system
- **Firebase** - Backend services
  - Firestore - Database
  - Authentication - User management
  - Storage - File storage
  - Messaging - Push notifications
- **Retrofit** - API communication
- **CameraX** - Camera functionality
- **Google Maps** - Location services

## 📱 Lokasi File Project

Project ini tersimpan di: `C:\Users\aseps\ParkirTertib\`

### Struktur Folder:
```
📂 C:\Users\aseps\ParkirTertib\
├── 📄 build.gradle (Project level)
├── 📄 settings.gradle
├── 📄 README.md
└── 📂 app/
    ├── 📄 build.gradle (App level)
    ├── 📄 google-services.json (Firebase config)
    └── 📂 src/main/
        ├── 📄 AndroidManifest.xml
        ├── 📂 java/com/parkirtertib/app/
        │   ├── 📄 MainActivity.kt
        │   ├── 📂 data/
        │   ├── 📂 ui/
        │   └── 📂 service/
        └── 📂 res/
            ├── 📂 values/
            ├── 📂 drawable/
            └── 📂 xml/
```

## 🚀 Cara Membuka di Android Studio

1. **Buka Android Studio**
2. **File → Open**
3. **Pilih folder:** `C:\Users\aseps\ParkirTertib`
4. **Klik OK**
5. **Tunggu Gradle sync selesai**
6. **Run aplikasi**

## 🔧 Setup Firebase

1. Buat project di [Firebase Console](https://console.firebase.google.com)
2. Tambahkan aplikasi Android dengan package: `com.parkirtertib.app`
3. Download `google-services.json`
4. Place file di: `C:\Users\aseps\ParkirTertib\app\`
5. Enable Firebase services yang diperlukan

## 📋 Permissions

Aplikasi memerlukan permissions:
- Internet
- Location (Fine & Coarse)
- Camera
- Storage
- Notifications

## 🎨 Design System

- **Primary:** Blue (#1976D2)
- **Secondary:** Green (#4CAF50)
- **Accent:** Orange (#FF9800)
- **Material Design 3** dengan tema custom

## 📊 Dataset Integration

Siap untuk integrasi dengan dataset "Operasi Derek Pelanggaran Parkir" dari Open Data Bandung.

## 🤝 Contact

- Email: contact@parkirtertib.com
- Project: Parkir Tertib Android App
