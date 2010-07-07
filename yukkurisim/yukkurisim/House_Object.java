package yukkurisim;

import gamestatus.SaveData;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.golden.gamedev.object.Sprite;

public class House_Object extends Object_base {
	private ADV_SpriteGroup_base	MyGroup;
	private House_Object MyObject[];
	public boolean isParent;
	
	public void Initialize(BufferedImage[] img)
	{
		if(img==null)
		{	// マスターオブジェクトの処理
			this.isParent=true;
			double t=0;
			this.MyGroup = new ADV_SpriteGroup_base(owner,"HOUSE_GROUP");

			if(my_y%2!=0)
			{
				t+=0.5;		// y位置による横位置補正補正
			}
			
			// 自分自身の画像を作成、登録
			//this.setImages(owner.getImages("image/mapchip/home3.gif", 1, 1));
			this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家3));
			//this.location((int)(my_x+t),my_y-2);
			System.out.println("this.myx->"+my_x+"   this.my_y->"+my_y);
			MyGroup.add(this);
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home1.gif", 1, 1),(int)(my_x+t+0.5),my_y+1));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home2.gif", 1, 1),(int)(my_x+t+1),my_y+2));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home4.gif", 1, 1),(int)(my_x+t+0.5),my_y-1));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home5.gif", 1, 1),(int)(my_x+t+1),my_y));
			//MyGroup.add(new House_Object(owner,owner.getImages("image/mapchip/home6.gif", 1, 1),(int)(my_x+t+1.5),my_y+1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家1),(int)(my_x+t+0.5),my_y+1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家2),(int)(my_x+t+1),my_y+2));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家4),(int)(my_x+t+0.5),my_y-1));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家5),(int)(my_x+t+1),my_y));
			MyGroup.add(new House_Object(owner,ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_家6),(int)(my_x+t+1.5),my_y+1));
			
			this.setAnimate(false); 				// アニメーションしない
			this.setLoopAnim(false);				// アニメーションをループさせない
			this.setActiveToFade(true);
			
			this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-定数.マップチップ横幅));
			this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-定数.マップチップ縦幅));
					

		}
		else
		{
			this.isParent = false;
			this.setImages(img);
			this.setAnimate(false); 				// アニメーションしない
			this.setLoopAnim(false);				// アニメーションをループさせない
			this.setActiveToFade(true);
			
			this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-定数.マップチップ横幅));
			this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-定数.マップチップ縦幅));
					
			PhLaw.Get_ObjectManager().add(this, 定数.TYPE_家object);
		}
		
	}
	
	/* 子イメージオブジェクトを作成するコンストラクタ */
	public House_Object(yukkurisim_main own, BufferedImage[] arg0, int x, int y) {
		super(own, arg0);
		location(x,y);
		Initialize(arg0);
	}

	public House_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1, double arg2) {
		super(own, arg0, arg1, arg2);
		//Initialize(arg0);
	}

	public House_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		//Initialize();
	}

	/* マスターオブジェクトを作成するコンストラクタ */
	public House_Object(yukkurisim_main own, int arg0, int arg1) {
		super(own);
		location(arg0,arg1);
		Initialize(null);
		System.out.println("■ハウスオブジェクトデバッグ");
		System.out.println("■myx→"+this.my_x);
		System.out.println("■myx→"+this.my_y);
	}

	public House_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		//Initialize(null);
	}

	public House_Object(yukkurisim_main own) {
		super(own);
		//Initialize();
	}

	@Override
	public int Get_Type() {
		return 定数.TYPE_家object;
	}

	@Override
	public void doClickEvent() {
		// TODO 自動生成されたメソッド・スタブ

	}
	
	/* 親オブジェクトだけが描画を行う */
	public void render(Graphics2D g)
	{
		// 子オブジェクトの描画はマップオブジェクトマネージャにまかせる
		super.render(g);
	}
	
	public SaveData getSaveData()
	{
		SaveData mysave = new SaveData();
		mysave.my_x = this.my_x;
		mysave.my_y = this.my_y;
		mysave.syuzoku = this.Get_Type();
		mysave.itemflag = false;
		return mysave;
	}
	
	public void setSaveData(SaveData mysave)
	{
		this.my_x	=	mysave.my_x ;
		this.my_y	=	mysave.my_y ;
	}
	
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	/**
	 * 既に設置してあるオブジェクトが無い？
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAbleToBuild(int x,int y)
	{
		if( this.PhLaw.Get_ObjectManager().Get_obj_type(x, y)>定数.TYPE_object_キャラクタと建築物番号敷居値 )
		{
			return false;
		}
		return true;
	}
}
