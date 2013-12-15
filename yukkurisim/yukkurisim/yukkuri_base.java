package	yukkurisim;
//JFC
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import gamestatus.Const_Value;
import gamestatus.GameInfoManager;
import gamestatus.GameTimer;
import gamestatus.SaveData;
import java.util.Random;
import java.util.Vector;

/****************************************************
 * ゆっくり等オブジェクトのベースクラスいずれ抽象化
 * @author ポチエル
 * @version 0.1
 */

public class yukkuri_base extends Object_base
{
	private int	y_birthday;		//ゆっくりの誕生日(何日目に生まれたか)
	private int	y_hp;			//ゆっくりのバイタリティ、なくなると死んでしまう。
	private int	y_grow_lv;		//ゆっくりの成長段階をあらわす。(0～2)
	private int	y_exp;			//ゆっくりの成長段階を変化させるパラメータ。
	private int	y_hungry;		//ゆっくりの空腹度。
	private int	y_sweety;		//ゆっくりを食べた時の甘さ。
	private int	y_happynes;		//可愛がると増える、虐待すると減る、糖度に反比例し、器用度・HPに比例する。
	private int	y_dex;			//幸福度と経験値と成長度に影響される。
	private int	y_cooperative;	//協調性、農業の成果に影響する
	private int	y_hunting;		//狩の能力、狩の成果に影響する
		
	private int	state;		//ゆっくり状態
	
	private int	y_id;		//
	private int	parent_id;	//
	private int[]	child_id;	//
	
	/**************　テンポラリ系　***************/
	private int	tmp_eat;	//ゆっくりの食事プログレス
	private int	debug_tmp;
	/**************　テンポラリ系　***************/

	BufferedImage[]	yukkuri_wait;			//待ち状態の画像
	BufferedImage[]	yukkuri_eat;			//食事状態の画像
	BufferedImage[]	yukkuri_dead_desease;	//病死状態の画像
	BufferedImage[]	yukkuri_dead;			//死亡状態の画像

	BufferedImage[]	yukkuri_img;

	public SaveData getSaveData()
	{
		SaveData mysave = new SaveData();
		mysave.birthday = this.y_birthday;
		mysave.cooper = this.y_cooperative;
		mysave.dex = this.y_dex;
		mysave.exp = this.y_exp;
		mysave.growlv = this.y_grow_lv;
		mysave.happy = this.y_happynes;
		mysave.hp = this.y_hp;
		mysave.hungry = this.y_hungry;
		mysave.hunt = this.y_hunting;
		mysave.my_x = this.my_x;
		mysave.my_y = this.my_y;
		mysave.state = this.GetState();
		mysave.sweety = this.y_sweety;
		mysave.syuzoku = this.Get_Type();
		mysave.itemflag = false;
		
		return mysave;
	}
	
	public void setSaveData(SaveData mysave)
	{
		 this.y_birthday	=	mysave.birthday ;
		 this.y_cooperative	=	mysave.cooper ;
		 this.y_dex	=	mysave.dex ;
		 this.y_exp	=	mysave.exp ;
		 this.y_grow_lv	=	mysave.growlv ;
		 this.y_happynes	=	mysave.happy ;
		 this.y_hp	=	mysave.hp ;
		 this.y_hungry	=	mysave.hungry ;
		 this.y_hunting	=	mysave.hunt ;
		 this.location(mysave.my_x, mysave.my_y);
		 this.setState(mysave.state) ;
		 this.y_sweety	=	mysave.sweety ;
	}
	
	public yukkuri_base(
			yukkurisim_main own
			)

	{
		super( own );		//親クラスのコンストラクタコール
		//this.owner = own;		//呼び出し元クラスへのポインタ
		debug_tmp = 0;		//■デバッグ
		initialize();		//初期化
	}
	
	/**
	 * セル位置情報を変更し、行き先変数に実座標を代入する。
	 * locationとMove_Toの違いは location→瞬間移動 Move_To→移動開始 でありたい。
	 */
	@Override
	public void location( int x , int y )
	{
		super.location(x, y);
	}
	
	
	/***
	 * クリック時のイベント記述
	 */
	@Override
	public void doClickEvent()
	{
		// ステータスウインドウ対象入力で待ち状態か？
		if( Widget_Manager.Get_Instance(owner).isStatusWait() )
		{
			// 自分のステータスウインドウを表示
			Widget_Manager.Get_Instance(owner).showStatusWidget(	this.Get_Type(),			// 種族
																	this.getHP(),				// 体力
																	this.Get_Cooperative(),		// こころ（協調性）
																	this.Get_Hunting(),			// ちから（狩り）
																	this.getSweety(),			// 甘さ
																	this.getHungry(),			// おなか
																	this.getBirthday());		// 誕生日
			Widget_Manager.Get_Instance(owner).setStatusWait(false);
		}
	}
	
	/**
	 * 育成パートにて、狩りが指定された時の動作パターン。
	 *
	 */
	public void doKari(long elapsedTime)
	{
		if(this.state < 定数.死亡 )
		{
			// 空腹度減算処理
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//空腹度が0になったら死ぬ
				this.setState(定数.死亡);
			}
			else	///////////// 以下、平常時処理 ///////////////
			{
				if( GameTimer.Get_Instance(owner, 0).Get_Times() == 定数.一日の長さ/2)
				{	// get_instanceの二つめの引数はダミー
					//アイテム取得処理
					KariAction.Get_Instance(owner).getItemChallenge(this);
					System.out.println("タイマ動作→"+GameTimer.Get_Instance(owner, 0).Get_Times());
					this.setState(定数.狩り後待機中);
				}
				else if( this.state == 定数.農業後待機中 )
				{
					//今のところ動かない
				}
			}
			
		}
		// 死亡していなければここまで、以下は死亡したときの処理
		else
		{
			//最終フレームならばそこでとめる。
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActive(false);
			}
		}
		//UpdateImage();
		
	}
	
	/**
	 * 育成パートにて、農業が指定された時の動作パターン。
	 *
	 */
	public void doFarm(long elapsedTime)
	{
		if(this.state < 定数.死亡 )
		{
			// 空腹度減算処理
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//空腹度が0になったら死ぬ
				//this.setImages(yukkuri_dead);
				this.setAnimate(true); 					// アニメーションする
				this.setLoopAnim(false);					// アニメーションをループさせない
				this.getAnimationTimer().setDelay(130);		// 13/1000（8コマ/sec) で表示
				this.setState(定数.死亡);
			}
			else	///////////// 以下、平常時処理 ///////////////
			{
				if(this.state == 定数.移動中)
				{
					//this.moveTo( elapsedTime , go2x, go2y, 0.05);
					PhLaw.MoveTo(this, elapsedTime , go2x, go2y, 0.05);		// 物理判断後
																			// 移動可能ならば動く					
					if( (this.getX() == go2x)&&(this.getY() == go2y) )
					{
						debug_tmp = 2;	//■デバッグ
						//this.setImages(yukkuri_wait);
						this.setState(定数.農業中);
						if(this.recovering)
						{
							
							this.recovering = false;
						}
					}
				}
				else if( this.state == 定数.待機中 )
				{
					// 農地リストベクタを取得
					Vector<Object_base> farms = Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager().getFarmVector();
			
					// 対象となる農地を一つランダムに決定
					Random sai = new Random();
					int t = sai.nextInt(farms.size());
					Farm_Object targetfarm = (Farm_Object)farms.get(t);
					
					System.out.println("ゆっくり番号→"+this.myId+"  目的→"+targetfarm.my_x+","+targetfarm.my_y+"  総数→"+farms.size());
					
					this.Move_To(targetfarm.my_x, targetfarm.my_y);
		        	this.setState(定数.移動中);
				}
				else if( this.state == 定数.農業中 )
				{
					// 一日の半分の時間、農業に従事させることで農業を達成したとみなす。
					// すなわち、農地に到達できなかったゆっくりはそもそも農業を達成したとみなさない。
					
					if( GameTimer.Get_Instance(owner, 0).Get_Times() == 定数.一日の長さ/2)
					{	// get_instanceの二つめの引数はダミー
						//アイテム取得処理
						FarmAction.Get_Instance(owner).getItemChallenge(this);
						System.out.println("タイマ動作→"+GameTimer.Get_Instance(owner, 0).Get_Times());
						this.setState(定数.農業後待機中);
					}
				}
				else if( this.state == 定数.農業後待機中 )
				{
					//今のところ動かない
				}
			}
		}
		// 死亡していなければここまで、以下は死亡したときの処理
		else
		{
			//最終フレームならばそこでとめる。
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActiveToFade(false);
			}
		}
		UpdateImage();
	}
	
	/**
	 * 育成パートにて、遊んでいいよといわれた時の動作パターン。
	 *
	 */
	public void doPlay(long elapsedTime)
	{
		if ( this.state < 定数.死亡 )
		{
			// 空腹度減算処理
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//空腹度が0になったら死ぬ
				//this.setImages(yukkuri_dead);
				this.setAnimate(true); 					// アニメーションする
				this.setLoopAnim(false);					// アニメーションをループさせない
				this.getAnimationTimer().setDelay(130);		// 13/1000（8コマ/sec) で表示
				this.setState(定数.死亡);
			}
			else	///////////// 以下、平常時処理 ///////////////
			{
				////// おなかがへったよ ここから ////////
				if( (this.y_hungry < 4000)&&(!(this.recovering)))	//ただし、リカバリ中はごはんを食べにいけない
				{
					for(int i = -2;i<3;i++)
					{
						for(int j = -2;j<3;j++)
						{
							if
								(
									(this.my_x + i < 0)||(this.my_y + j < 0)||
									(this.my_x + i >= 定数.画面横セル幅)||
									(this.my_y + j >= 定数.画面縦セル幅)
								)	//探査範囲が画面外になる場合何もしない。
							{
									//何もしない
							}
							else
							{
								if ( PhLaw.Get_ObjectManager().Get_obj_type(this.my_x + i, this.my_y + j) == 定数.TYPE_木object )
								{//木を見つけた！
									if( this.Get_Range(
											this.Get_Pixel_x(this.my_x + i, this.my_y + j) + (定数.マップチップ横幅/2),
											this.Get_Pixel_y(this.my_x + i, this.my_y + j) + (定数.マップチップ縦幅/2) )
										<= 定数.マップチップ横幅 )		// 対象マップセルの中心点から自身の中心点までの距離が
																		// マップチップ横幅より小さい。
									{//木の近くなので、食べる	
										if (PhLaw.Get_ObjectManager().Get_obj_pointer(this.my_x + i, this.my_y + j) == null )
										{
											System.out.println("■デバッグ情報");
											System.out.println("this.my_x=" + this.my_x );
											System.out.println("this.my_y=" + this.my_y );
											System.out.println("i=" + i );
											System.out.println("j=" + j );
										}
										this.y_hungry += ((Tree_Object)PhLaw.Get_ObjectManager().Get_obj_pointer(this.my_x + i, this.my_y + j)).Get_Food();
										System.out.println("■食事発生");
										//this.setImages(yukkuri_eat);
										this.setState(定数.食事中);
										if( this.y_hungry>10000 )
										{
											this.y_hungry=10000;
										}
										break;
									}
									else
									{	//近づく（後で書き直す）
										this.Move_To(this.my_x + i+1, this.my_y+j+1);
										this.setState(定数.移動中);
										break;
									}
									
								}
							}
						}
					}
				}
				
				if( this.state == 定数.食事中){
					this.tmp_eat--;
					if( this.tmp_eat == 0 )
					{
						this.tmp_eat = 100;
						debug_tmp = 1;		//■デバッグ
						//this.setImages(yukkuri_wait);
						this.setState(定数.待機中);
						if(this.recovering)
						{
							//そもそも、リカバリ中のやつが食事をすること自体おかしい。
							//System.out.println("0,"+myId+","+my_x+","+my_y+","+old_my_x+","+old_my_y+","+go2x+","+go2y+",recovering=0");
							this.recovering = false;
						}

					}
				}
		
				////// おなかがへったよ ここまで ////////		
				if(this.state == 定数.移動中)
				{
					//this.moveTo( elapsedTime , go2x, go2y, 0.05);
					PhLaw.MoveTo(this, elapsedTime , go2x, go2y, 0.05);		// 物理判断後
																			// 移動可能ならば動く					
					if( (this.getX() == go2x)&&(this.getY() == go2y) )
					{
						debug_tmp = 2;	//■デバッグ
						//this.setImages(yukkuri_wait);
						this.setState(定数.待機中);
						if(this.recovering)
						{
							//System.out.println("5,"+myId+","+my_x+","+my_y+","+old_my_x+","+old_my_y+","+go2x+","+go2y+",recovering=0");
							this.recovering = false;
						}
					}
				}
				if( this.state == 定数.待機中 )
				{
					Random sai = new Random();
					
					if( sai.nextInt( 100 ) == 1)
					{
						this.Move_To( sai.nextInt( 定数.画面横セル幅) ,sai.nextInt( 定数.画面縦セル幅 ));
			        	this.setState(定数.移動中);
					}
				}
			}	//平常時処理ここまで
		}		//死亡していなければ、ここまで。
		else
		{
			//最終フレームならばそこでとめる。
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActiveToFade(false);
			}
		}
		UpdateImage();		
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);	
		if(GameTimer.Get_Instance(owner, 定数.画像番号_タイマ).isMoving() )	// タイマが動作している間（タイマ動作時のupdate処理の有無は各objectで指定しないと、Fade処理が実行されなくナッチャウ
		{
			// this.Get_Type()の戻り値から100を引いているのは配列の頭に合わせるため。 種族定数は100から始まる。
			if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == 定数.アクション_狩り)
			{
				doKari(elapsedTime);
			}
			else if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == 定数.アクション_農業)
			{
				doFarm(elapsedTime);
			}
			else if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == 定数.アクション_遊ぶ)
			{
				doPlay(elapsedTime);
			}
		}
	}

	/**
	 * 現在の状態から最適な画像を選び、setimagesする。
	 *
	 */
	public void UpdateImage()
	{
		BufferedImage[] tmp = null;
		if(GetState()==定数.移動中)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ歩く_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ歩く);
			}
		}
		else if(GetState()==定数.食事中)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ食べる_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ食べる);
			}
			
		}
		else if(GetState()==定数.待機中)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
			}
			
		}
		else if(GetState()==定数.農業後待機中)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
			}
			
		}
		else if(GetState()==定数.農業中)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
			}
			
		}
		else if(GetState()==定数.死亡)
		{
			// 目的地が→にあるときは
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ死亡_反転);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ死亡);
			}
		}
		
		if(this.getImages()!=tmp)
		{
			this.setImages(tmp);
		}
	}
	
	public void setState(int ToState)
	{
		this.state = ToState;
	}

	@Override
	public void render(Graphics2D g)
	{
		if(Physical_Law_Facade.Get_Instance(owner).Get_Part()==定数.パート_育成区分)
		{	// 育成区分の時のみゆっくりを表示
			if(GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100)!=定数.アクション_狩り)
			{	// 狩りに行く種族だけは表示しない
				super.render(g);
			}
		}
	}
	
	/*************************
	 * セルポジション( my_x等 )を実座標に変換し、go2x go2yに格納します.
	 *
	 */
	public void Move_To( int x , int y )
	{
		this.go2x = this.Get_Pixel_x(x,y) + (定数.マップチップ横幅 - this.width)/2;
		this.go2y = this.Get_Pixel_y(x,y) + (定数.マップチップ縦幅 - this.height)/2;

	}

	/**
	 * 協調性を返す
	 * @return
	 */
	public int Get_Cooperative()
	{
		return this.y_cooperative;
	}
	
	/**
	 * 狩の能力を返す。
	 * @return
	 */
	public int Get_Hunting()
	{
		return this.y_hunting;
	}
	
	/**************************************************************************
	 * 初期化メソッド、通常オブジェクトの初期化はここをオーバライドする。
	 *
	 */
	public void initialize()
	{
		//yukkuri_img = this.owner.getImages("image/reimuwalk.gif",12,1);
	    //yukkuri_wait = this.owner.getImages("image/reimuwait.gif",6,1);
	    //yukkuri_eat = this.owner.getImages("image/reimueat.gif",9,1 );
	    //yukkuri_dead_desease = this.owner.getImages("image/reumudesease.gif",1,1 );
	    //yukkuri_dead = this.owner.getImages("image/reimudead.gif",8,1 );
		yukkuri_img = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ歩く);
	    yukkuri_wait = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
	    yukkuri_eat = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ食べる);
	    yukkuri_dead_desease = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ病死);
	    yukkuri_dead = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ死亡);
       	
	    y_birthday		= 0;
		y_hp			= 10;
		y_grow_lv		= 0;
		y_exp			= 0;
    	y_hungry		= 定数.一日の長さ*3;	// 空腹度は最初100
		y_sweety		= 10;
		y_happynes		= 0;
		y_dex			= 0;
		y_cooperative	= 0;		// 協調性
		y_hunting		= 0;		// 狩の能力
		
		
		//location( 10 , 10 , 10);

		this.setImages(yukkuri_wait);
		this.setAnimate(true); 				// アニメーションする
		this.setLoopAnim(true);				// アニメーションをループさせる
		
    	this.go2x = 0;
    	this.go2y = 0;
    	
    	tmp_eat = 100;
    	
    	
    	定数 = new Const_Value();
    	this.state = 定数.待機中;
    	
    	PhLaw.Get_ObjectManager().add(this, 定数.TYPE_ゆっくりobject);

	}

	@Override
	public int Get_Type() {
		return 定数.TYPE_ゆっくりobject;
	}

	public int GetState()
	{
		return this.state;
	}

	public int getHungry() {
		return y_hungry;
	}

	public void setHungry(int h) {
		if(h<=0)
		{
			this.setState(定数.死亡);
			y_hungry=0;
		}
		else
		{
			y_hungry=h;			
		}
	}

	public int getSweety() {
		return y_sweety;
	}
	
	public void setSweety(int s){
		if( s<0 )
		{
			y_sweety = 0;
		}
		else
		{
			y_sweety = s;
		}
	}

	public int getHP() {
		return y_hp;
	}
	
	public void setHP(int h)
	{
		if(h<=0)
		{
			this.setState(定数.死亡);
			y_hp = 0;
		}
		else
		{
			y_hp = h;
		}
	}

	public int getBirthday() {
		return y_birthday;
	}

	public void setBirthday(int y_birthday) {
		this.y_birthday = y_birthday;
	}

	@Override
	public boolean isCharactor()
	{
		return true;	// これはキャラクターである
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}