# Room-Word-Sample
Implementation from ROOM, MVVM + Live Data in the Simple App.

Pada aplikasi ini hanyalah contoh sederhana dari GET ALL Data, Delete All and One Data, GET Single Data pada ROOM

Catatan : 
1. Mengapa extends AndroidViewModel karena kita membutuhkan context atau application di construct,itu lebih baik daripada menggunakan ViewModel agar tidak terjadi kebocoran memori, dikarenakan ViewModel dirancang untuk hidup lebih lama dari aktivitas dan akan menyebabkan Kebocoran Memori.
