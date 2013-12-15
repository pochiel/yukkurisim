package yukkurisim;
import gamestatus.Const_Value;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * 画像を一度に読み込み、保持します。
 * 対象の画像へのゲッタを提供します。
 * @author pochiel
 *
 */
public class ImageLoader extends Thread {
	private volatile HashMap<Integer,BufferedImage[]> imagemap = new HashMap<Integer,BufferedImage[]>();
	private yukkurisim_main owner;					//オーナーGameインスタンスへの参照
	private  Const_Value 定数 = new Const_Value();
	private volatile static ImageLoader myself;
	
	/**
	 * コンストラクタ
	 * @param own
	 */
	public ImageLoader(yukkurisim_main own)
	{
		owner = own;
		//LoadImage();	// 画像のロード
	}
	
	@Override
	public void run() {
		LoadImage();	// 画像のロード開始
	}
	
	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized ImageLoader Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new ImageLoader(own);
		}

		return myself;
	}

	
	public synchronized void LoadImage()
	{
		setBufferedImage(定数.画像番号_ゆっくりれいむ歩く,owner.getImages("image/reimuwalk.gif",12,1));
		setBufferedImage(定数.画像番号_ゆっくりれいむ待つ,owner.getImages("image/reimuwait.gif",6,1));
		setBufferedImage(定数.画像番号_ゆっくりれいむ食べる,owner.getImages("image/reimueat.gif",9,1 ));
		setBufferedImage(定数.画像番号_ゆっくりれいむ病死,owner.getImages("image/reumudesease.gif",1,1 ));
		setBufferedImage(定数.画像番号_ゆっくりれいむ死亡,owner.getImages("image/reimudead.gif",8,1 ));
		setBufferedImage(定数.画像番号_ゆっくりれいむ歩く_反転,reversImage(getBufferedImage(定数.画像番号_ゆっくりれいむ歩く)));
		setBufferedImage(定数.画像番号_ゆっくりれいむ待つ_反転,reversImage(getBufferedImage(定数.画像番号_ゆっくりれいむ待つ)));
		setBufferedImage(定数.画像番号_ゆっくりれいむ食べる_反転,reversImage(getBufferedImage(定数.画像番号_ゆっくりれいむ食べる)));
		setBufferedImage(定数.画像番号_ゆっくりれいむ病死_反転,reversImage(getBufferedImage(定数.画像番号_ゆっくりれいむ病死)));
		setBufferedImage(定数.画像番号_ゆっくりれいむ死亡_反転,reversImage(getBufferedImage(定数.画像番号_ゆっくりれいむ死亡)));

		setBufferedImage(定数.画像番号_カーソル,owner.getImages("image/cursor.gif", 4, 1));
		setBufferedImage(定数.画像番号_タイル0,owner.getImages("image/mapchip/paneldefault.gif",1,1));
		setBufferedImage(定数.画像番号_家1,owner.getImages("image/mapchip/home1.gif", 1, 1));
		setBufferedImage(定数.画像番号_家2,owner.getImages("image/mapchip/home2.gif", 1, 1));
		setBufferedImage(定数.画像番号_家3,owner.getImages("image/mapchip/home3.gif", 1, 1));
		setBufferedImage(定数.画像番号_家4,owner.getImages("image/mapchip/home4.gif", 1, 1));
		setBufferedImage(定数.画像番号_家5,owner.getImages("image/mapchip/home5.gif", 1, 1));
		setBufferedImage(定数.画像番号_家6,owner.getImages("image/mapchip/home6.gif", 1, 1));
		setBufferedImage(定数.画像番号_草原1,owner.getImages("image/mapchip/sougen1.gif",1,1));
		setBufferedImage(定数.画像番号_草原2,owner.getImages("image/mapchip/sougen2.gif",1,1));
		setBufferedImage(定数.画像番号_川1,owner.getImages("image/mapchip/river1.gif",1,1));
		setBufferedImage(定数.画像番号_川2,owner.getImages("image/mapchip/river2.gif",1,1));
		setBufferedImage(定数.画像番号_エントランス,owner.getImages("image/mapchip/enterance.gif",1,1));
		setBufferedImage(定数.画像番号_木,owner.getImages("image/mapchip/wood1.gif",1,1));
		setBufferedImage(定数.画像番号_農地,owner.getImages("image/mapchip/farm.gif",1,1));
		
		//************************ Widget系ここから ***********************************//
		setBufferedImage(定数.画像番号_チェックボックスオン,owner.getImages("image/widget/checkT.GIF", 1, 1));
		setBufferedImage(定数.画像番号_チェックボックスオフ,owner.getImages("image/widget/checkF.GIF", 1, 1));
		setBufferedImage(定数.画像番号_ダイアログウィンドウ,owner.getImages("image/widget/dialogwindow.GIF", 1, 1));
		setBufferedImage(定数.画像番号_メッセージウィンドウ,owner.getImages("image/widget/MessageWindow.gif", 1, 1));
		setBufferedImage(定数.画像番号_ビルドウインドウ,owner.getImages("image/widget/window_build.GIF",1,1));
		setBufferedImage(定数.画像番号_タイマ,owner.getImages("image/widget/timer.gif",1,1));
		setBufferedImage(定数.画像番号_タイマ_文字盤,owner.getImages("image/widget/timer_circle.gif",1,1));
		setBufferedImage(定数.画像番号_メニュ,owner.getImages("image/widget/menu.gif",1,1));
		setBufferedImage(定数.画像番号_チェックボタン,owner.getImages("image/widget/check_b.gif",1,1));
		setBufferedImage(定数.画像番号_ショップボタン,owner.getImages("image/widget/shop_b.gif",1,1));
		setBufferedImage(定数.画像番号_アイテムボタン,owner.getImages("image/widget/item_b.gif",1,1));
		setBufferedImage(定数.画像番号_トラップボタン,owner.getImages("image/widget/trap_b.gif",1,1));
		setBufferedImage(定数.画像番号_ビルドボタン,owner.getImages("image/widget/build_b.gif",1,1));
		setBufferedImage(定数.画像番号_クラッシュボタン,owner.getImages("image/widget/crash_b.gif",1,1));
		setBufferedImage(定数.画像番号_データボタン,owner.getImages("image/widget/data_b.gif",1,1));
		setBufferedImage(定数.画像番号_クイックボタン,owner.getImages("image/widget/quick_b.gif",1,1));
		setBufferedImage(定数.画像番号_パニッシュボタン,owner.getImages("image/widget/panish_b.gif",1,1));
		setBufferedImage(定数.画像番号_ラブボタン,owner.getImages("image/widget/love_b.gif",1,1));		
		setBufferedImage(定数.画像番号_木ボタン,owner.getImages("image/widget/window_build_B_01.GIF",1,1));
		setBufferedImage(定数.画像番号_家ボタン,owner.getImages("image/widget/window_build_B_02.GIF",1,1));
		setBufferedImage(定数.画像番号_農地ボタン,owner.getImages("image/widget/window_build_B_03.GIF",1,1));
		setBufferedImage(定数.画像番号_閉じるボタン,owner.getImages("image/widget/owaru.GIF",1,1));
		setBufferedImage(定数.画像番号_撤去ボタン,owner.getImages("image/widget/tekkyo.GIF",1,1));
		setBufferedImage(定数.画像番号_アクションウインドウ,owner.getImages("image/widget/window_action.GIF",1,1));
		setBufferedImage(定数.画像番号_ステータスウインドウ,owner.getImages("image/widget/window_status.GIF",1,1));
		setBufferedImage(定数.画像番号_アイテムウインドウ,owner.getImages("image/widget/window_ItemUse.GIF",1,1));
		setBufferedImage(定数.画像番号_ダイアログOK,owner.getImages("image/widget/DialogOK.GIF",1,1));
		setBufferedImage(定数.画像番号_アイテムハテナ,owner.getImages("item/hatena.gif",1,1));

	}
	
	private synchronized void setBufferedImage(int i,BufferedImage[] image)
	{
		if(imagemap.get(i)!=null){
			// もうすでになんか入ってる！
			System.out.println("error key→"+i);
			throw new IllegalArgumentException
            ("既存のキー値でイメージマップを上書きしようとしました。");
		}
		
		imagemap.put(i, image);
		
	}
	
	public synchronized BufferedImage[] getBufferedImage(int i)
	{
		//System.out.println("getBufferedImage("+i+")→"+(imagemap.get(i)==null?"null":imagemap.get(i)));
		if(imagemap.get(i)==null)
		{
			throw new IllegalArgumentException
			("存在しない画像をロードしようとしました。");
		}
		return imagemap.get(i);
	}
	
	/**
	 * 画像を反転したBufferedImage[]を作成します。
	 * @param img
	 * @return
	 */
	private synchronized BufferedImage[] reversImage(BufferedImage [] img)
	{
		AffineTransform at = AffineTransform.getScaleInstance(-1.0, 1.0);
		at.translate(-1*(img[0].getWidth()), 0);
		AffineTransformOp atOp = new AffineTransformOp(at, null);

		BufferedImage[] revimg = new BufferedImage[img.length];
		for(int i=0;i<img.length;i++)
		{
			revimg[i] = atOp.filter(img[i], null);
		}
		
		return revimg;
	}

	public HashMap getmap()
	{
		return imagemap;
	}
	
}
