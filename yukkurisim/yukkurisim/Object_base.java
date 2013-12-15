package yukkurisim;

import java.awt.image.BufferedImage;
import gamestatus.Const_Value;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.io.Serializable;

import com.golden.gamedev.object.AnimatedSprite;
//import com.golden.gamedev.Game;
import yukkurisim.yukkurisim_main;

public abstract class Object_base extends AnimatedSprite implements Serializable {
	
	protected yukkurisim_main owner;					//オーナーGameインスタンスへの参照
	protected  Const_Value 定数 = new Const_Value();
	protected double myId;					// オブジェクトID
	private boolean visible = true;			// 可視属性
	private boolean clickable = true;			// クリック可能
	private boolean TimerDependency = true;	// Timerに従属するか
	protected Cursor_Manager	OwnCursor;		// カーソルマネージャ
	protected boolean isScrollable;			// スクロール可能
	protected Physical_Law_Facade PhLaw;		// 物理法則クラスへの参照
	
	protected float alpha;
	protected int nowFadingSwitch;		// 1→フェードイン 2→フェードアウト
	
	int	my_x;		//セル上のX位置
	int	my_y;		//セル上のY位置
	int old_my_x;	//セル上のX位置、一つ前
	int old_my_y;	//セル上のY位置、一つ前
	
	
	
	/************* デバッグ関連ココから 必ず消去すること！ ************/
	private int debug;		//デバッグ用識別変数
	public void set_debug(int a)
	{
		this.debug = a;
	}
	public int get_debug()
	{
		return this.debug;
	}
	/************* デバッグ関連ココマデ 必ず消去すること！ ************/

	public void Set_Dependency(boolean t)
	{
		this.TimerDependency = t;
	}
	
	public boolean Get_Dependency()
	{
		return this.TimerDependency;
	}
	
	
	protected int		go2x;
	protected int		go2y;
	protected boolean recovering;				// 現在立ち入り禁止区域から退避中
	
	public int Get_my_y()
	{
		return this.my_y;
	}
	
	public int Get_my_x()
	{
		return this.my_x;
	}
	
	/**
	 * オーナーへの参照のみを引数に取るコンストラクタ.
	 * @param own
	 */
	public Object_base(yukkurisim_main own) {
		super(null,0,0);
		this.owner = own;
    	initResources();
	}
	/**
	 * <p>
	 * オーナへの参照・画像を引数に取るコンストラクタ。<br>
	 * 通常はこのコンストラクタを用いる。
	 * </p>
	 * @param own
	 * @param arg0
	 */
	public Object_base(yukkurisim_main own , BufferedImage[] arg0) {
		super(arg0);
		this.owner = own;
    	initResources();
	}

	/**
	 * <p>
	 * オーナへの参照とスプライトの初期座標を引数に取るコンストラクタ。<br>
	 * このコンストラクタはどうやって使ったらいいんだろう？
	 * </p>
	 * @param own
	 * @param arg0
	 * @param arg1
	 */
	public Object_base(yukkurisim_main own , double arg0, double arg1) {
		super(arg0, arg1);
		this.owner = own;
    	initResources();
	}

	/**
	 * オーナへの参照・画像・初期座標を引数に取るコンストラクタ.
	 * @param own
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public Object_base(yukkurisim_main own , BufferedImage[] arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
		this.owner = own;
    	initResources();
	}
	
	public void Set_Scrollable(boolean s)
	{
		isScrollable = s;
	}
	
	@Override
	public void update(long elapsedTime)
	{
		if( this.getNowFadingSwitch()==1 )
		{	// アクティブになってるのにアルファ値が1以下の場合フェードイン中
			this.alpha += 0.01f;
			if(this.alpha>1.0)
			{
				this.alpha = 1.0f;
			}
		}
		else if(this.getNowFadingSwitch()==2)
		{	//フェード中
			this.alpha -= 0.01f;
			if(this.alpha<0.0)
			{
				this.alpha = 0.0f;
				this.setActive(false);
			}
		}
		
		super.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g)
	{
		if( owner.GameIsFading() )
		{
			if(this.isScrollable)
			{
				// スクロール可能ならば
				// レンダリングをする際にスクロール相対座標計算をしちゃう
				// 絶対座標と相対座標を二重で管理する必要がなくなるため
				double backupx = this.getX();								// 元の座標を退避
				double backupy = this.getY();
				
				this.setX( this.getX() + this.OwnCursor.Get_RelX() );		// 相対座標をセット
				this.setY( this.getY() + this.OwnCursor.Get_RelY() );
		
				super.render(g);											// レンダリング
		
				this.setX(backupx);											// 絶対座標を復元
				this.setY(backupy);
			}
			else
			{
				super.render(g);
			}
		}
		else
		{
			// Compositeを退避
	        Composite comp = g.getComposite();
	        // アルファ値
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	        
			if(this.isScrollable)
			{
				// スクロール可能ならば
				// レンダリングをする際にスクロール相対座標計算をしちゃう
				// 絶対座標と相対座標を二重で管理する必要がなくなるため
				double backupx = this.getX();								// 元の座標を退避
				double backupy = this.getY();
				
				this.setX( this.getX() + this.OwnCursor.Get_RelX() );		// 相対座標をセット
				this.setY( this.getY() + this.OwnCursor.Get_RelY() );
		
				super.render(g);											// レンダリング
		
				this.setX(backupx);											// 絶対座標を復元
				this.setY(backupy);
			}
			else
			{
				super.render(g);
			}
			
			// 退避したcompositeを復帰
			g.setComposite(comp);
		}	
	}
	
	/**
	 * マウスが乗ったか？
	 * @return
	 */
	public boolean isMouseRide()
	{
		if( this.isScrollable )
		{
			if(	(owner.getMouseX() > this.getX() + this.OwnCursor.Get_RelX()) && ( owner.getMouseX() < this.getX() + this.OwnCursor.Get_RelX()+ this.getWidth())&&	//x方向が画像の範囲内 
				(owner.getMouseY() > this.getY() + this.OwnCursor.Get_RelY()) && ( owner.getMouseY() < this.getY() + this.OwnCursor.Get_RelY()+ this.getHeight()) )	//y方向が画像の範囲内
			{
				if( this.isActive() )
				{
					return true;
				}
			}
			return false;			
		}
		else
		{
			if(	(owner.getMouseX() > this.getX()) && ( owner.getMouseX() < this.getX() + this.getWidth())&&	//x方向が画像の範囲内 
				(owner.getMouseY() > this.getY()) && ( owner.getMouseY() < this.getY() + this.getHeight()) )	//y方向が画像の範囲内
			{
				if( this.isActive() )
				{
					return true;
				}
			}
			return false;
		}
		
	}

	/**
	 * クリックされたか？
	 * @return
	 */
	public boolean isClicked()
	{
		
		if( this.isScrollable )
		{
			if( owner.isMouseUp() )
			{
				if(	(owner.getMouseX() > this.getX() + this.OwnCursor.Get_RelX()) && ( owner.getMouseX() < this.getX() + this.OwnCursor.Get_RelX()+ this.getWidth())&&	//x方向が画像の範囲内 
					(owner.getMouseY() > this.getY() + this.OwnCursor.Get_RelY()) && ( owner.getMouseY() < this.getY() + this.OwnCursor.Get_RelY()+ this.getHeight()) )	//y方向が画像の範囲内
				{
					if( this.isActive() )
					{
						System.out.println("isclicked scrollable");
						return true;
					}
				}
			}
			return false;			
		}
		else
		{
			// owner.click() は owner.bsInput.isMousePressed() をコールしているが、これはMouseDownの瞬間にシグナルを拾うため
			// 他スレッドでアップデートして処理負けしている可能性がある。
			// しかし、owner.bsInput.isMouseDown() はダウンの間中ずっとtrueを返し続けるので、これもうまくない。
			// よって、オーナ側でマウスの挙動を監視し、isMouseDownが true→false と変化した瞬間にtrueとなる仕組みにすればよい。
			// owner.click() と違って同一スレッド上でシグナルのアップデートが保障されるので、オーナのupdate→子のupdate→isclickという仕組みならば
			// 処理負けもありえない。
			if( owner.isMouseUp() )
			{
				if(	(owner.getMouseX() > this.getX()) && ( owner.getMouseX() < this.getX() + this.getWidth())&&	//x方向が画像の範囲内 
					(owner.getMouseY() > this.getY()) && ( owner.getMouseY() < this.getY() + this.getHeight()) )	//y方向が画像の範囲内
				{
					if( this.isActive() )
					{
						System.out.println("owner.getMouseX() >"+owner.getMouseX());
						System.out.println("owner.getMouseY() >"+owner.getMouseY());
						System.out.println("this.getX() >"+this.getX());
						System.out.println("this.getY() >"+this.getY());			
						System.out.println("this.getWidth() >"+this.getWidth());
						System.out.println("this.getHeight() >"+this.getHeight());			
						
						System.out.println("isclicked not scrollable");
						return true;
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * Get_Pixel_x
	 * セル座標の実座標のXを計算して返します。
	 */
	public int Get_Pixel_x(int my_x,int my_y){
		if( my_y%2 == 1)
		{
			return my_x * 定数.マップチップ横幅+(定数.マップチップ横幅/2) ;
		}
		else
		{
			return my_x * 定数.マップチップ横幅;
		}		
	}

	/**
	 * Get_Pixel_y
	 * セル座標の実座標のYを計算して返します。
	 */
	public int Get_Pixel_y(int my_x,int my_y){
			return	my_y * (( 定数.マップチップ縦幅 / 2 )-3);		// 現状 25y
	}

	/**
	 * セル位置情報の変更。
	 * @param x
	 * @param y
	 */
	public	void location( int x , int y )//, int z )
	{
		my_x = x;
		my_y = y;
		this.setX(this.Get_Pixel_x(my_x, my_y));
		this.setY(this.Get_Pixel_y(my_x, my_y));
		
	}
	
    public void Initialize()
    {
    }
    
	/***
	 * 現在のオブジェクトの実座標情報からセル単位座標( my_x , my_y )を更新する。
	 *
	 */
	public void Set_Cell_From_Real_Location()
	{
		int tmp_my_x = 0;
		int tmp_my_y = 0;
		int rx , ry;
		rx = (int) this.getX();
		ry = (int) this.getY();
		
		tmp_my_y = 2 * ry/(定数.マップチップ縦幅-6);		//yセル座標を算出

		if ( tmp_my_y%2 == 1)
		{
			tmp_my_x = (rx/定数.マップチップ横幅)-(1/2);
		}
		else
		{
			tmp_my_x = rx/定数.マップチップ横幅;
		}
		
		if ( tmp_my_x >= 定数.画面横セル幅 )
		{
			tmp_my_x = 定数.画面横セル幅 - 1;
		}
		if ( tmp_my_y >= 定数.マップチップ縦幅 )
		{
			tmp_my_y = 定数.画面縦セル幅 - 1;
		}
		if ( tmp_my_x < 0 )
		{
			tmp_my_x = 0;
		}
		if ( tmp_my_x < 0 )
		{
			tmp_my_x = 0;
		}
		
		if( (my_x!=tmp_my_x) || (my_y!=tmp_my_y) )
		{
			old_my_x = my_x;
			old_my_y = my_y;
		}
		my_x = tmp_my_x;
		my_y = tmp_my_y;
	}
	
	public void Move_to(int x , int y)
	{
		//ダミー
	}
	
	/***
	 * 直線距離を取得する
	 * @param targetx 対象のx座標
	 * @param targety 対象のy座標
	 * @return
	 */
	public double Get_Range( int targetx , int targety)
	{
		int Delta_x , Delta_y;
		Delta_x = (int) this.getX() - targetx;
		Delta_y = (int) this.getY() - targety;
		
		return Math.sqrt((Delta_x * Delta_x)+(Delta_y * Delta_y));
	}
	
	/***
	 * 該当オブジェクトのクリックイベント
	 *
	 */
	public abstract void doClickEvent();
	/*{
		//System.out.println("失敗");
		//ダミー
	}*/
	
	public abstract int Get_Type();
	
	public void Set_Go2Location(int x,int y)
	{
		this.go2x = x;
		this.go2y = y;
	}
	
	public void initResources()
	{
		this.alpha = 1.0f;
		this.myId = this.owner.IdMan.GetNewId();				// ObjectId付与
    	this.OwnCursor = Cursor_Manager.Get_Instance(owner);	// カーソル管理クラス
    	this.isScrollable = true;								// スクロール可能フラグはデフォルトtrue
    	this.PhLaw = Physical_Law_Facade.Get_Instance(owner);	// 物理法則クラス
    	this.old_my_x = 0;
    	this.old_my_y = 0;
    	this.recovering = false;
	}

	public double Get_Id()
	{
		return this.myId;
	}
	public abstract boolean isCharactor();		// オブジェクトがキャラクタであるかを返す
	public abstract void doOnMouseEvent();		//	オブジェクト上にマウスが乗ったときのイベントを記述する

	public void setActiveToFade(boolean s)
	{
		if(s)
		{	
			this.nowFadingSwitch = 1;
			this.alpha = 0.0f;
			this.setActive(s);
			
		}
		else
		{
			this.nowFadingSwitch = 2;
		}
		
		//this.my_child.setActive(s);		//非activeな子は自動でアップデートされない		
	}
	public int getNowFadingSwitch() {
		return nowFadingSwitch;
	}

	public void setNowFadingSwitch(int nowFadingSwitch) {
		this.nowFadingSwitch = nowFadingSwitch;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getAlpha() {
		return alpha;
	}

}
