package subwindow;

import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Sprite;

import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.ImageLoader;
public class Widget_Status extends Widget_Base {
	private boolean Targetting;
	/** その他画像データ表示用画像データ参照 **/
	private BufferedImage[] reimu_image = null;		// れいむのイメージ
	private BufferedImage[] marisa_image = null;		// まりさのイメージ

	public Widget_Status(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_Status(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		this.SetFontsize(11);
		Initialize();
	}
	
	@Override
	public void doClickEvent()
	{
		super.doClickEvent();

		if(Button_id==定数.ボタンID_閉じるボタン)
		{
			this.Get_Root_Parent().setActiveToFade(false);
			Widget_Manager.Get_Instance(owner).GTimer.Start_Timer();
		}
	}

	public boolean isTargetting() {
		return Targetting;
	}

	public void setTargetting(boolean targetting) {
		Targetting = targetting;
	}
	
	@Override
	public void Initialize()
	{
		super.Initialize();
		// その他画像表示用画像データ初期化処理
		if(Button_id==定数.その他ID_画像表示)
		{
			//this.reimu_image = owner.getImages("image/reimuwait.gif", 1, 6);
			this.reimu_image = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
			this.setImages(reimu_image);
			
			this.setAnimate(true);				// アニメーションさせる
			this.setLoopAnim(true);			// ループさせる
		}
	}

	/*****************あいまい表示元文字列取得系関数ここから*****************/
	/*****************※未調整！ あいまい表示が整ったら個々を書き換えること**/
	public String getSyuzokuString( int syuzoku )
	{		
		if(syuzoku==定数.TYPE_ゆっくりobject)
		{
			return "れいむ";
		}
		else if(syuzoku==定数.TYPE_まりさobject)
		{
			return "まりさ";
		}
		return "NULL";
	}
	
	public String getTairyokuString( int tairyoku )
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
			if(	(tairyoku>=(定数.C_ステータス最大値*i/10))&&
				(tairyoku<(定数.C_ステータス最大値*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(tairyoku==定数.C_ステータス最大値)
		{
			return "みんなゆっくりしていってね！";
		}
		return "null";
		
	}
	
	public String getKokoroString( int kokoro )
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
			if(	(kokoro>=(定数.C_ステータス最大値*i/10))&&
				(kokoro<(定数.C_ステータス最大値*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(kokoro==定数.C_ステータス最大値)
		{
			return "みんなゆっくりしていってね！";
		}
		return "null";
		
	}
	
	public String getChikaraString( int chikara )
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
			if(	(chikara>=(定数.C_ステータス最大値*i/10))&&
				(chikara<(定数.C_ステータス最大値*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(chikara==定数.C_ステータス最大値)
		{
			return "みんなゆっくりしていってね！";
		}
		return "null";
		
	}
	
	public String getAmasaString( int amasa )
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
			if(	(amasa>=(定数.C_ステータス最大値*i/10))&&
				(amasa<(定数.C_ステータス最大値*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(amasa==定数.C_ステータス最大値)
		{
			return "みんなゆっくりしていってね！";
		}
		return "null";		
		
	}
	
	public String getOnakaString( int onaka )
	{
		String[] serifu={	"知性のかけらもない",
				"どう見てもゲス",
				"期待したら負け",
				"ぎりぎり動物",
				"こいぬ以下",
				"ゆっくりしてるね！",
				"やればできる",
				"愛を感じる",
				"ゆっくり大家族スペシャル",
				"あなたとがったいしたい"};

		for(int i=0;i<10;i++)
		{
			if(	(onaka>=(定数.MAX_HUNGRY_VALUE*i/10))&&
				(onaka<(定数.MAX_HUNGRY_VALUE*(i+1)/10)))
			{
				return serifu[i];
			}
		}
		if(onaka==定数.C_ステータス最大値)
		{
			return "みんなゆっくりしていってね！";
		}
		return "null";		
		
	}
	/*****************あいまい表示元文字列取得系関数ここまで*****************/
	
	/**
	 * ステータスウインドウに値を表示します。
	 * @param syuzoku
	 * @param tairyoku
	 * @param kokoro
	 * @param chikara
	 * @param amasa
	 * @param onaka
	 */
	public void showStatusWidget(	int syuzoku ,
									int tairyoku ,
									int kokoro ,
									int chikara ,
									int amasa ,
									int onaka ,
									int days)
	{
		if(this.Get_Parent()==null )
		{
			this.setActiveToFade(true);
			Sprite[] child = this.getChild().getSprites();
			Widget_Status tmp;
			for(int i=0;i<this.Get_Child_Size();i++)
			{
				tmp = (Widget_Status)child[i];
				if( tmp.Get_Type()==定数.ラベルID_ステータス_種族 )
				{
					tmp.Set_Mymessage(this.getSyuzokuString(syuzoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_体力) )
				{
					tmp.Set_Mymessage(this.getTairyokuString(tairyoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_こころ) )
				{
					tmp.Set_Mymessage(this.getTairyokuString(tairyoku));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_ちから) )
				{
					tmp.Set_Mymessage(this.getChikaraString(chikara));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_甘さ) )
				{
					tmp.Set_Mymessage(this.getAmasaString(amasa));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_おなか) )
				{
					tmp.Set_Mymessage(this.getOnakaString(onaka));
				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.その他ID_画像表示) )
				{
					if(syuzoku==定数.TYPE_ゆっくりobject)
					{
						tmp.setImages(tmp.reimu_image);
					}
					else if(syuzoku==定数.TYPE_まりさobject)
					{
						//画像差し替え処理をはさむ
					}

				}
				if( (tmp.isActive())&&(tmp.Get_Type()==定数.ラベルID_ステータス_日数) )
				{
					tmp.Set_Mymessage("生後 "+(Widget_Manager.Get_Instance(owner).GTimer.Get_Days()-days)+"日目");
				}
				
			}
		}
	}
}
