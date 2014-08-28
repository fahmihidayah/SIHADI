package com.example.expertsystem;

import java.util.ArrayList;
import java.util.Date;

import com.example.beans.DataSingleton;
import com.example.beans.Inferentor;
import com.example.beans.MainBeans;
import com.example.expertsystem.LoginActivity.EditServerDialog;
import com.example.model.ChatMessage;
import com.example.model.Conclution;
import com.example.model.Constants;
import com.example.model.MenuApp;
import com.example.model.Penyakit;
import com.example.model.Premis;
import com.example.response.ChatMessageResponse;
import com.framework.navigation_drawer_v1.MenuDrawer;
import com.framework.navigation_drawer_v1.NavigationDrawerActivity;
import com.google.gson.Gson;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends NavigationDrawerActivity implements Constants {

	private MainBeans mainBeans;
	private int currentFragment = 0;

	public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (currentFragment != 4) {
				Toast.makeText(getBaseContext(), "Ada pesan baru",
						Toast.LENGTH_LONG).show();
			}
			String message = intent.getStringExtra("message");
			ChatMessageResponse chatMessageResponse = new Gson().fromJson(
					message, ChatMessageResponse.class);
			DataSingleton.getInstance().getListChatMessage()
					.add(chatMessageResponse.getChatMessage());
			DataSingleton.getInstance().saveToFile(context);
			DataSingleton.getInstance().notifyObserverDataChange();

		}
	};

	@Override
	public Fragment getFragmentFromIndex(int i) {
		currentFragment = i;
		switch (i) {
		case 0:
			return new FragmentCaraPakai();
		case 1:
			return new FragmentJenisHama();
		case 2:
			return new FragmentCaraBertani();
		case 3:
			return new FragmentDiagnosa();
		case 4:
			return new FragmentGroup();
		case 5:
			return new FragmentDaftarPengguna();
		case 6:
			return new FragmentAbout();
		case 7:
			mainBeans.logout();
		default:
			return null;
		}
	}

	@Override
	public void setListDrawerMenu(ArrayList<MenuDrawer> listMenuDrawers) {
		listMenuDrawers.add(new MenuDrawer(R.drawable.icon_cara_pakai, "Cara Pakai"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.mantis_hama, "Jenis Hama"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.network,"Cara Bertanam"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.icon_sistem_pakar_dua,"Sistem Pakar"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.large_group, "Group"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.powered_by_google_dark, "Daftar Pengguna Aktif"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.icon_about, "Tentang"));
		listMenuDrawers.add(new MenuDrawer(R.drawable.icon_logout, "Log Out"));
	}

	@Override
	public void afterInitializeConfiguration(Bundle saveInstanceState) {
		mainBeans = new MainBeans(this);
		Inferentor inferentor = DataSingleton.getInstance().getInferentor();
		Penyakit penyakit = new Penyakit("Tikus");
		penyakit.setGambar(R.drawable.hamatikus);
		penyakit.setSaran("Kendalikan tikus pada awal musim tanam sebelum memasuki masa reproduksi. Kegiatan tersebut meliputi gropyok masal, sanitasi habitat, pemasangan TBS (Trap Barrier System) / Sistem Bubu Perangkap) dan LTBS (Linear Trap Barier Sistem");
		inferentor.addConclution(penyakit);
		inferentor.addPremisToConclution(new Penyakit("Tikus"), new Premis(
				"Tanaman Rusak Mulai Dari Tengah Petak Selanjutnya Ke Pinggir",
				false));

		penyakit = new Penyakit("Keong Mas");
		penyakit.setGambar(R.drawable.keong_mas);
		penyakit.setSaran(" Saat-saat penting untuk mengendalikan keong mas adalah pada 10 hari pertama untuk padi tanam pindah dan sebelum tanaman berumur 21 hari pada tabela (tanam benih secara langsung");
		inferentor.addConclution(penyakit);
		inferentor.addPremisToConclution(new Penyakit("Keong Mas"), new Premis(
				"Padi Muda Hancur Pada Saat Pertumbuhan Awal", false));

		penyakit = new Penyakit("Penggerek Batang");
		penyakit.setGambar(R.drawable.imszages);
		penyakit.setSaran(" Lindungi agen pangendalian hayati�Untuk melindungi musuh alami penggerek batang, jangan gunakan pestisida berspektrum luas, mis. methyl parathion. Sayat ujung helaian daun sebelum tanam pindah.�Telur-telur penggerek batang kuning diletakkan dekat ujung helaian daun. Dengan menyayat bibit sebelum tanam pindah, pengalihan telur dari persemaian ke sawah dapat dikurangi.Tanam belakangan (sedikit terlambat) untuk menghindari ngengat penggerek batang kuning. Varietas tahan�Beberapa varietas seperti PB36, PB32, IR66, dan IR77 mampu menghasilkan anakan baru sehingga mengkompensasi anakan yang mati. Jemur atau hamparkan jerami di bawah sinar matahari untuk membunuh larva yang terdapat di situ. Jaring larva penggerek batang pada daun yang mengapung dengan jaring. Olah dan genangi sawah setelah panen.");
		inferentor.addConclution(penyakit);
		inferentor
				.addPremisToConclution(new Penyakit("Penggerek Batang"),
						new Premis(
								"Ngengat Berubah Berwarna Kuning Atau Coklat",
								false));
		inferentor.addPremisToConclution(new Penyakit("Penggerek Batang"),
				new Premis("Ada Larva Pada Tanaman", false));

		penyakit = new Penyakit("Tungro");
		penyakit.setGambar(R.drawable.tungro);
		penyakit.setSaran(" Varietas tahan."
				+ " Penggunaan varietas tahan seperti TukadUnda, Tukad Balian, "
				+ "Tukad Petanu, Bondoyudo, dan Kalimas merupakan cara terbaik"
				+ " untuk mengendalikan tungro. Rotasi varietas penting untuk "
				+ "mengurangi gangguan ketahanan. Pembajakan di bawah sisa tunggul "
				+ "yang terinfeksi. Hal ini dilakukan untuk mengurangi sumber penyakit"
				+ " dan menghancurkan telur dan tempat penetasan wereng hijau."
				+ " Bajak segera setelah panen bila tanaman sebelumnya terkena penyakit."
				+ "Cabut dan bakar tanaman yang sakit. Ini perlu dilakukan kecuali bila "
				+ "serangan tungro sudah menyeluruh. Bila serangan sudah tinggi maka"
				+ " mungkin ada tanaman yang terinfeksi tungro tapi kelihatan sehat. "
				+ "Mencabut tanaman yang terinfeksi dapat mengganggu wereng hijau sehingga"
				+ " makin menyebarluaskan infeksi tungro.Tanam benih langsung (Tabela): "
				+ "Infeksi tungro biasanya lebih rendah pada tabela karena lebih tingginya "
				+ "populasi tanaman (bila dibandingkan tanam pindah). "
				+ "Dengan demikian wereng cenderung mencari dan makan serta "
				+ "menyerang tanaman yang lebih rendah populasinya.Waktu Tanam: Tanam padi "
				+ "saat populasi wereng hijau dan tungro rendah.");
		inferentor.addConclution(penyakit);
		new Premis("Pelepah Dan Helaian Daun Memendek", false);
		inferentor.addConclution(new Penyakit("Tungro"));
		inferentor.addPremisToConclution(new Penyakit("Tungro"), new Premis(
				"Daun Berwarna Kuning Sampai Kuning Oranye", false));
		inferentor.addPremisToConclution(new Penyakit("Tungro"), new Premis(
				"Daun Muda Strip Berwarna Hijau Pucat", false));
		inferentor.addPremisToConclution(new Penyakit("Tungro"), new Premis(
				"Daun Menguning Berkurang Bila Daun Yang Lebih Tua Terinfeksi",
				false));

		penyakit = new Penyakit("Hawar Bakteri");
		penyakit.setGambar(R.drawable.hawar_bakteri);
		penyakit.setSaran("Gunakan varietas tahan,Pemupukan menggunakan pupuk N tanpa P dan K, penanganan bibit secara baik waktu tanam pindah, pengairan dangkal pada persemaian, dan membuat drainase yang baik ketika genangan tinggi");
		inferentor.addConclution(penyakit);
		inferentor.addPremisToConclution(new Penyakit("Hawar Bakteri"),
				new Premis("Tanaman Yang Terinfeksi Kehilangan Areal Daun",
						false));
		inferentor.addPremisToConclution(new Penyakit("Hawar Bakteri"),
				new Premis("Hasil Gabah Yang Sedikit Dan Hampa", false));
		inferentor.addPremisToConclution(new Penyakit("Hawar Bakteri"),
				new Premis("Daun Berubah Hijau Keabu abuan Menggulung", false));
		inferentor.addPremisToConclution(new Penyakit("Hawar Bakteri"),
				new Premis("Daun Yang Menggulung, Mati ( Kering )", false));
	}

	@Override
	protected void onResume() {
		IntentFilter intentFilter = new IntentFilter(BROADCAST_CHAT);
		intentFilter.setPriority(100);
		this.registerReceiver(broadcastReceiver, intentFilter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(broadcastReceiver);
		
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		DataSingleton.getInstance().setActive(false);
		DataSingleton.getInstance().saveToFile(this);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_setting_server:
			EditServerDialog editServerDialog = new EditServerDialog();
			editServerDialog.show(getFragmentManager(), "edit_dialog");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressLint({ "ValidFragment", "NewApi" })
	public class EditServerDialog extends android.app.DialogFragment {

		private View rootViewDialog;
		private EditText editTextServerAddress;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Update Server Address");
			LayoutInflater layoutInflater = getActivity().getLayoutInflater();

			rootViewDialog = layoutInflater.inflate(
					R.layout.layot_setting_server_dialog, null);
			builder.setView(rootViewDialog);
			editTextServerAddress = (EditText) rootViewDialog
					.findViewById(R.id.editTextServerAddress);
			editTextServerAddress.setText(DataSingleton.getInstance()
					.getServerAddress());
			builder.setPositiveButton("SIMPAN",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							mainBeans.updateServerAddress(editTextServerAddress
									.getText().toString());
						}
					});
			return builder.create();
		}
	}

}
