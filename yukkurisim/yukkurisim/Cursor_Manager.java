package yukkurisim;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamestatus.Const_Value;

/**
 * カーソルの表示と、スクロール関連の情報を管理する。
 * @author pochiel
 *
 */
public class Cursor_Manager extends ADV_SpriteGroup_base {

	private int RelPosX;		// スクロールで表示される位置の相対座標
	private int RelPosY;		// マウスで増やす
    private Mapchip_base	myCursor = null;	// カーソル
    private Widget_Base	myCustomer = null;	// カーソルの戻り先
    private int			myCursor_s;			// カーソル処理区分
    private int			CursorCount = 0;	// カーソル有効化までの時間
    private int 			CursorWidth;		// カーソル幅（家等のマルチマップオブジェクト設置用）
    private int			CursorHeight;		// カーソル高さ（家等のマルチマップオブジェクト設置用）

    protected yukkurisim_main owner;
	protected  Const_Value 定数 = new Const_Value();
	
	private static Cursor_Manager	myself = null;
	public Cursor_Manager(yukkurisim_main own,String s) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(own,"CURSOR");
		owner = own;
	}

	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized Cursor_Manager Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Cursor_Manager(own,"CURSOR");
		}

		return myself;
	}

	public void Check_Cursor_Position()
	{
		int mx = owner.getMouseX();
		int my = owner.getMouseY();
		
		if(mx<=定数.スクロール遊び)					// 画面左端で←スクロール
		{
			this.RelPosX+=2;
			if( this.RelPosX > 定数.スクロール最大範囲X )
			{
				this.RelPosX = 定数.スクロール最大範囲X;
			}
		}
		else if(mx>=定数.画面幅-定数.スクロール遊び)	// 画面右端で→スクロール
		{
			this.RelPosX-=2;
			if( this.RelPosX < 定数.スクロール最小範囲X )
			{
				this.RelPosX = 定数.スクロール最小範囲X;
			}
		}
		

		if(my<=定数.スクロール遊び)					// 画面上端で↑スクロール
		{
			this.RelPosY+=2;
			if( this.RelPosY > 定数.スクロール最大範囲Y )
			{
				this.RelPosY = 定数.スクロール最大範囲Y;
			}

		}
		else if(my>=定数.画面高さ-定数.スクロール遊び)	// 画面下端で↓スクロール
		{
			this.RelPosY-=2;
			if( this.RelPosY < 定数.スクロール最小範囲Y )
			{
				this.RelPosY = 定数.スクロール最小範囲Y;
			}
		}
	}
	
	public int Get_RelX()
	{
		return this.RelPosX;
	}
	
	public int Get_RelY()
	{
		return this.RelPosY;
	}
	/**
	 * カーソルの表示非表示を変更し、呼び出し元インスタンスのComeback_From_Cursor()を
	 * コールする。
	 * 
	 * @param customer 実行元のインスタンスへの参照
	 * @param t カーソルの表示非表示
	 * @param s 処理区分
	 */
	public void SetCursorActivate(Widget_Base customer,boolean t, int s,int width , int height)
	{
		CheckCursorInstanceisNotNull();
		
		if (customer==null)
		{
			// 実行元が存在しない（カーソルの表示だけ変更する）
			myCursor.setActive(t);
		}
		else
		{
			myCustomer = customer;
			myCursor_s = s;
			myCursor.setActive(t);
			CursorWidth = width;
			CursorHeight = height;			
		}
		CursorCount=20;
	}
	
    public void render(Graphics2D g) {
    	double dmy_x;
    	double dmy_y;
    	double bkmy_x=myCursor.my_x;
    	double bkmy_y=myCursor.my_y;
		
    	if( myCursor.isActive() )
    	{
    		for(int j=CursorHeight-1;j>=0;j--)
			{
				dmy_x=bkmy_x+(0.5*j);
				dmy_y=bkmy_y-j;
		    	if(bkmy_y%2!=0)
				{
					dmy_x+=0.5;		// y位置による横位置補正補正
				}
				
		    	for(int i=0;i<CursorWidth;i++)
		    	{	//高さ    		
					myCursor.location((int)(dmy_x), (int)dmy_y);
					myCursor.render(g);
					dmy_x+=0.5;
					dmy_y++;
		    	}			
			}
    	}
    }
    
	public void update(long elapsedTime)
	{
		CheckCursorInstanceisNotNull();

		if(myCursor.isActive())
		{	// カーソルがアクティブならば
			myCursor.setX(owner.getMouseX() - this.Get_RelX());			//座標をマウス位置に変更
			myCursor.setY(owner.getMouseY() - this.Get_RelY());
			myCursor.Set_Cell_From_Real_Location();		//実座標からセル座標を更新
			myCursor.setX(myCursor.Get_Pixel_x(myCursor.my_x, myCursor.my_y));
			myCursor.setY(myCursor.Get_Pixel_y(myCursor.my_x, myCursor.my_y));
			
			if( myCursor.my_x < 0 )
			{
				myCursor.my_x=0;
			}
			if( myCursor.my_y < 0 )
			{
				myCursor.my_y=0;
			}
			if( myCursor.my_x > 定数.画面横セル幅 )
			{
				myCursor.my_x=定数.画面横セル幅;
			}
			if( myCursor.my_y > 定数.画面縦セル幅 )
			{
				myCursor.my_y=定数.画面縦セル幅;
			}
	
			myCursor.update(elapsedTime);				//update
		}
		
		if(CursorCount!=0)
		{
			CursorCount--;
		}
		else
		{
			
			if(myCursor.isActive())
			{	
				if(myCursor.isClicked())
				{
					myCustomer.Comeback_From_Cursor(myCursor.my_x , myCursor.my_y , myCursor_s);
					SetCursorActivate(null,false,0,0,0);		// カーソル消去
				}				
			}
		}

		this.Check_Cursor_Position();
	}
	
	private void CheckCursorInstanceisNotNull()
	{
		/*
		 * （´ω｀）あらすじ
		 * ①.カーソルマネージャのコンストラクタでカーソル自体をnewする
		 * ②.Object_baseのコンストラクタでスクロール処理用にCursormanのGetInstanceをコール
		 * ③.CursormanのGetInstanceでカーソルマネージャのコンストラクタをコール
		 * ④.①に戻る
		 * 　↓
		 * StackOverflowError＼(^o^)／人生オワタ
		 * 
		 * しょうがないのでカーソルを表示しそうな箇所の頭にこの関数をコソーリと仕込む
		 * ゲェッ！きたねえ！汚物は消毒だぁーっ！
		 */
		if( myCursor==null )
		{
			System.out.println("＼(^o^)／人生オワタ");
			myCursor = new Mapchip_base
			(
					owner , 
					//owner.getImages("image/cursor.gif", 4, 1),
					ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_カーソル),
					0,
					0,
					0
			);
			myCursor.setActive(false);		// 初期非表示
			myCursor.Set_Scrollable(true);	// スクロール化
			myCursor.setAnimate(true);		// アニメーション化
			myCursor.setLoopAnim(true);		// アニメーションはループする
			myCursor.set_debug(100);				// デバッグ
			this.add(myCursor);
		}
	}
	
}
