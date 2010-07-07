package yukkurisim;

import gamestatus.Const_Value;
import java.io.*;
import yukkurisim.yukkurisim_main;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class Physical_Law_Facade  extends CollisionGroup{

	private yukkurisim_main owner;					//オーナーGameインスタンスへの参照
	private  Const_Value 定数 = new Const_Value();
	
	/************************ パート管理関連 ここから *******************************/
	private int PartKbn;

	/**
	 * パートを移動する
	 *
	 */
	public void Change_Part()
	{
		/** 施策(建築)パート→施策(行動)パート **/
		if( this.PartKbn==定数.パート_施策_建築区分 )
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			this.PartKbn=定数.パート_施策_行動区分;
		}
		/** 施策(行動)パート→育成パート **/
		else if(this.PartKbn==定数.パート_施策_行動区分)
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			this.PartKbn=定数.パート_育成区分;
		}
		/** 育成パート→襲撃パート **/
		else if(this.PartKbn==定数.パート_育成区分)
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			//this.PartKbn=定数.パート_襲撃区分;		//現在のところ襲撃パートは無いのでおあずけ
			this.PartKbn=定数.パート_施策_建築区分;			
		}
		/** 襲撃パート→施策パート **/
		else if(this.PartKbn==定数.パート_襲撃区分)
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			this.PartKbn=定数.パート_施策_建築区分;
		}
	}
	
	/**
	 * 時間区分（昼or夜）をintで返す.
	 * @return
	 */
	public int Get_Part()
	{
		return this.PartKbn;
	}


	/************************ パート管理関連 ここまで *******************************/
	
	// 物理法則の補正対象となる管理対象はマップタイル、マップオブジェクト
	// そしてキャラクターオブジェクトの３種
	private MapTileManager 		MapTile;
	private MapObject_Manager 		MapObj;
	private ItemManager			ItemMan;
	
	// タイルマネージャのゲッタ
	public MapTileManager Get_TileManager()
	{
		return MapTile;
	}
	
	// オブジェクトマネージャのゲッタ
	public MapObject_Manager Get_ObjectManager()
	{
		return MapObj;
	}
	
	public ItemManager Get_ItemManager() {
		return ItemMan;
	}


	/* 唯一のコンストラクタ */
	public Physical_Law_Facade(yukkurisim_main own)
	{
		owner = own;
		this.PartKbn=定数.パート_施策_建築区分;		// 施策パートから始まる

	}
	
	/**
	 * 物理法則ファサードクラスで受け渡しを管理すべきマネージャをセットする
	 * @param t
	 * @param o
	 * @param i
	 */
	public void Set_Manager(MapTileManager t , MapObject_Manager o , ItemManager i)
	{
		this.MapObj = o;
		this.MapTile = t;
		this.ItemMan = i;
		this.setCollisionGroup(t.GetMapGroup(), o);
	}
	private static Physical_Law_Facade 	myself = null;

	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized Physical_Law_Facade Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Physical_Law_Facade(own);
		}

		return myself;
	}

	/* 何らかの運動を伴う思考について、意識的主体は物理法則にしたがって
	 * 運動をするのではなく、あくまで願望としてこういった運動を行いたい。
	 * という欲求の下然るべき運動を行い、それが物理法則によって補正される。
	 * したがって、作用側・反作用側・物理法則それぞれの概念が独立であることは
	 * 言うまでもないが、現象そのものについて言及すれば、作用側のきっかけは能動的であり
	 * 結果は受動的であるといえる。
	 * もう少し設計的に言うと、反作用側に作用を行うために直接作用させることができれば
	 * それに越したことは無いが、物理法則を経由させざるを得ない。　ということである。
	 * よって、運動可能なobjectが運動を行う事自体は */
	public boolean MoveTo(
							Object_base obj,
							long elapsedTime,
							double xs,
							double ys,
							double speed)
	{
		boolean tmp;
		tmp = obj.moveTo(elapsedTime, xs, ys, speed);
		obj.Set_Cell_From_Real_Location();			// 移動後のセル情報更新
				
		return tmp;
	}

	private Object_base MySprite1;
	private Object_base MySprite2;
	
	public void collided(Sprite arg0, Sprite arg1) 
	{
		// ぶつかったとき～♪
		MySprite1 = (Object_base)arg0;
		MySprite2 = (Object_base)arg1;
	
		// sprite1がゆっくりobject
		if( ( MySprite1.Get_Type()==定数.TYPE_ゆっくりobject ))
		{
			// 該当セルに入ってもいいですか？
			if( !MapTile.Check_able_to_Entry(MySprite2.my_x, MySprite2.my_y, MySprite1.Get_Type()))
			{
				// 入っちゃダメなら

				if(MapTile.Check_able_to_Entry(MySprite1.old_my_x, MySprite1.old_my_y, MySprite1.Get_Type()))
				{
					GetBack(MySprite1,MySprite2);
				}

			}
			else if(!MapObj.Check_able_to_Entry(MySprite2.my_x, MySprite2.my_y, MySprite1.Get_Type()))
			{
				if(MapTile.Check_able_to_Entry(MySprite1.old_my_x, MySprite1.old_my_y, MySprite1.Get_Type()))
				{
					GetBack(MySprite1,MySprite2);
				}
				
			}
			//MySprite1.Set_Cell_From_Real_Location();			// 移動後のセル情報更新

		}
		// sprite2がゆっくりobject
		if( ( MySprite2.Get_Type()==定数.TYPE_ゆっくりobject )&&(!(MySprite2.recovering)))
		{
			// 該当セルに入ってもいいですか？
			if( !MapTile.Check_able_to_Entry(MySprite1.my_x, MySprite1.my_y, MySprite2.Get_Type()))
			{
				// 入っちゃダメなら
				if(MapTile.Check_able_to_Entry(MySprite2.old_my_x, MySprite2.old_my_y, MySprite2.Get_Type()))
				{
					GetBack(MySprite2,MySprite1);
					// 戻ろうとしている先セルが進入可能であるならば
				   	// 戻る。（進入不可セルへ戻ろうとしてｶﾞｸｶﾞｸﾌﾞﾙﾌﾞﾙするバグ対策)
					/*MySprite2.go2x=(int)MySprite2.Get_Pixel_x(MySprite2.old_my_x, MySprite2.old_my_y);
					MySprite2.go2y=(int)MySprite2.Get_Pixel_y(MySprite2.old_my_x, MySprite2.old_my_y);

					System.out.println("2,"+MySprite2.myId+","+MySprite2.my_x+","+MySprite2.my_y+","+MySprite2.old_my_x+","+MySprite2.old_my_y+","+MySprite2.go2x+","+MySprite2.go2y+",recovering=1");

					MySprite2.old_my_x = MySprite1.my_x;		// ゆっくり自体が大きいため、障害物のかなり手前で衝突判定が入る
					MySprite2.old_my_y = MySprite1.my_y;		// その為、衝突時のold_x退避は障害物の座標を直で入力しないと
																// 障害に再び戻ろうとするのを禁止するルーチンが組めない。
					((yukkuri_base)MySprite2).setState(定数.移動中);
					MySprite2.recovering = true;*/
				}
			}
			else if(!MapObj.Check_able_to_Entry(MySprite1.my_x, MySprite1.my_y, MySprite2.Get_Type()))
			{
				// 入っちゃダメなら
				if(MapTile.Check_able_to_Entry(MySprite2.old_my_x, MySprite2.old_my_y, MySprite2.Get_Type()))
				{
					GetBack(MySprite2,MySprite1);	//戻る
				}
				
			}
		}

	}
	
	/** ゆっくりは引き返す 
	 *  第一引数をゆっくりになるように受け渡す。
	 * **/
	public void GetBack(Object_base arg0, Object_base arg1)
	{
		arg0.go2x=(int)arg0.Get_Pixel_x(arg0.old_my_x, arg0.old_my_y);
		arg0.go2y=(int)arg0.Get_Pixel_y(arg0.old_my_x, arg0.old_my_y);

		//System.out.println("2,"+arg0.myId+","+arg0.my_x+","+arg0.my_y+","+arg0.old_my_x+","+arg0.old_my_y+","+arg0.go2x+","+arg0.go2y+",recovering=1");

		arg0.old_my_x = arg1.my_x;					// ゆっくり自体が大きいため、障害物のかなり手前で衝突判定が入る
		arg0.old_my_y = arg1.my_y;					// その為、衝突時のold_x退避は障害物の座標を直で入力しないと
													// 障害に再び戻ろうとするのを禁止するルーチンが組めない。
		((yukkuri_base)arg0).setState(定数.移動中);
		arg0.recovering = true;
	}

}
