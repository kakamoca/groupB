package jp.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileOperation {
	//※このコードの場合、それぞれ対応するfileフォルダが存在している必要があります。
	final String  filepath;
	public File f;
	CsvFileOperation(){
		this.filepath = "CDdata.csv";
		this.f = new File(filepath);
	}
	
	public boolean csvWriter(List<CD> list) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.f))){
			bw.write("#商品No,商品名,歌手名,ジャンル,金額,在庫数");
			bw.newLine();
			for(CD cd : list) {
				bw.write(cd.getItemNum() + "," + cd.getItemName() + "," 
			+ cd.getArtist() + "," + cd.getGenre() + "," + cd.getPrice() + "," + cd.getStock());
				bw.newLine();
			}
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean csvReader(List<CD> list) {
		int itemNum;
		String itemName;
		String artist;
		String genre;
		int money;
		int stock;
		int count = 0;
		if(fileExists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(this.f))){
				String line;
				while((line = br.readLine()) != null) {
					if(line.charAt(0) != '#') {
						String[] s = line.split(",");
						itemNum = Integer.parseInt(s[0]);
						itemName = s[1];
						artist = s[2];
						genre = s[3];
						money = Integer.parseInt(s[4]);
						stock = Integer.parseInt(s[5]);
						list.add(new CD(itemNum, itemName, artist, genre, money, stock));
						count++;
					}
				}
				return true;
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public boolean fileExists() {
		if(f.exists()) {
			return true;
		}else {
			return false;
		}
	}

}