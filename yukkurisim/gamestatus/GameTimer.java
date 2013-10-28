package gamestatus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import yukkurisim.GameSaveLoader;
import yukkurisim.ImageLoader;
import yukkurisim.Object_base;
import yukkurisim.Other_Object;
import yukkurisim.yukkurisim_main;

import com.golden.gamedev.util.ImageUtil;

public class GameTimer extends Object_base{
	//private BufferedImage[] BackUpScrollingImage;
	private Object_base str_circle;
	/**
	 * 回転するゲームタイマ対応render
	 */
	public void render(Graphics2D g)
	{
		BufferedImage[] tempBuf = str_circle.getImages();
		tempBuf[0] = ImageUtil.rotate(ImageLoader.Get_Instance(this.owner).getBufferedImage(定数.画像番号_タイマ_文字盤)[0],(int)(((float) this.Times / 定数.一日の長さ)*360));
		str_circle.setImages(tempBuf);
		str_circle.render(g);
		super.render(g);
	}
	
	public GameTimer(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public GameTimer(yukkurisim_main own , BufferedImage[] arg0, double arg1, double arg2) {
		super(own,arg0,arg1,arg2);
		str_circle = new Other_Object(own,ImageLoader.Get_Instance(own).getBufferedImage(定数.画像番号_タイマ_文字盤).clone());
		str_circle.setLocation(3, 3);
		str_circle.Set_Scrollable(false);
	}
	
	public static GameTimer myself;
	/**
	 *  インスタンスを得る(親インスタンスと引き換え)
	 *  第二引数はイニシャライズ時のみ取得すればよい。
	 *  */
	public static synchronized GameTimer Get_Instance( yukkurisim_main own,int imageNumber )
	{
		if( myself == null)
		{
			myself = new GameTimer(own,ImageLoader.Get_Instance(own).getBufferedImage(imageNumber),0,0);
		}

		return myself;
	}

	private int Times;
	private int Days;
	private boolean isMoving;
	private boolean TimeKbnisChanged;
	private  Const_Value 定数 = new Const_Value();
		
	public void Initialize()
	{
		this.Reset_Timer();
		this.Days=1;
		this.TimeKbnisChanged=true;
	}
	
	public void Time_Inc()
	{
		// タイマは動いている？
		if( this.isMoving() )
		{
			// 育成パートなら時計を動かす
			if( PhLaw.Get_Part()==定数.パート_育成区分 )
			{
				this.Times++;
			}
			// 育成パートの終了判定
			if( (PhLaw.Get_Part()==定数.パート_育成区分)&&(this.Times > 定数.一日の長さ) )
			{
				// 育成パートのみ、ここで終了判定が行われる
				System.out.println("■"+Days+"日目が終了！！");
				this.Days++;
				
				/******* 育成パート終了時に自動でセーブを行う ********/
				GameSaveLoader iloader = new GameSaveLoader();
				iloader.SaveState(owner);
				/******* セーブ終了 *******/
				PhLaw.Change_Part();
			}
		}
	}
	
	
	public boolean Get_Time_kbn_Changed()
	{
		if(this.TimeKbnisChanged)
		{
			this.TimeKbnisChanged=false;
			return true;
		}
		return false;
	}
	
	/**
	 * タイマが動作しているかをbooleanで返す.
	 *
	 */
	public boolean isMoving()
	{
		return this.isMoving;
	}
	
	public void Reset_Timer()
	{
		this.Times=0;
	}
	
	public void Start_Timer()
	{
		this.isMoving=true;
	}
	
	public void Stop_Timer()
	{
		this.isMoving=false;
	}
	
	/**
	 * タイマの状態を任意のboolean値tとしてセットします
	 * @param t
	 */
	public void Set_Timer_State(boolean t)
	{
		this.isMoving=t;
	}
	public int Get_Times()
	{
		return this.Times;
	}
	
	public int Get_Days()
	{
		return this.Days;
	}

	public int Get_Type() {
		return 定数.TYPE_未使用;
	}

	public void doClickEvent() {
		// 使用しない
		
	}
	
	public boolean isCharactor()
	{
		return false;
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
