package pembayaranspp.dandyakbar.aplikasipembayaranspp.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.dandyakbar.aplikasipembayaranspp.R;

import java.io.File;
import java.util.HashMap;

import pembayaranspp.dandyakbar.aplikasipembayaranspp.admin.AdminActivity;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.admintransaksi.ApprovalActivity;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.admintransaksi.RekapitulasiAdminActivity;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.akun.ListAkunActivity;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.helper.SessionManager;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.helper.utils.CekKoneksi;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.helper.utils.CustomProgressbar;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.kelas.MainKelas;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.periode.ListPeriodeActivity;
import pembayaranspp.dandyakbar.aplikasipembayaranspp.siswa.ListSemuaSiswaAdmin;

public class MainSuperAdmin extends AppCompatActivity {
    CustomProgressbar customProgress = CustomProgressbar.getInstance();
    CekKoneksi koneksi = new CekKoneksi();
    public SessionManager SessionManager;
    private AppCompatTextView text_nama;
    private AppCompatImageView img_logout;
    public static String iduser, username;
    public static String idsekolah = "0001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_super_admin);
        SessionManager = new SessionManager(MainSuperAdmin.this);
        SessionManager.checkLogin();
        HashMap<String, String> user = SessionManager.getUserDetails();
        iduser = user.get(SessionManager.KEY_ID);
        username = user.get(SessionManager.KEY_USERNAME);

        text_nama = findViewById(R.id.text_nama);
        img_logout = findViewById(R.id.img_logout);

        ActionButton();
        text_nama.setText("Halo " + username);
    }

    private void ActionButton() {
        img_logout.setOnClickListener(v -> logoutUser());
        findViewById(R.id.cv7).setOnClickListener(v -> startActivity(new Intent(MainSuperAdmin.this, AdminActivity.class)));
        findViewById(R.id.cv6).setOnClickListener(v -> startActivity(new Intent(MainSuperAdmin.this, RekapitulasiAdminActivity.class)));
    }

    private void logoutUser() {
        clearApplicationData();
        SessionManager.logoutUser();
        finishAffinity();
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            assert children != null;
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            int i = 0;
            assert children != null;
            while (i < children.length) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
                i++;
            }
        }

        assert dir != null;
        return dir.delete();
    }
}