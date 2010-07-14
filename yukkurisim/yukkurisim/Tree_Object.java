/**
 * 
 */
package yukkurisim;

import gamestatus.Const_Value;
import gamestatus.SaveData;

import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * @author ポチエル
 *
 */
public class Tree_Object extends Object_base {

	public void Initialize()
	{
		//this.setImages(owner.getImages("image/wood1.gif",1,1));
		this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_木));
		
		this.setAnimate(false); 				// アニメーションしない
		this.setLoopAnim(false);				// アニメーションをループさせない
		
		this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-定数.マップチップ横幅));
		this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-定数.マップチップ縦幅));
		
		this.setActiveToFade(true);
				
		//PhLaw.Get_ObjectManager().add(this, 定数.TYPE_木object);	// add登録はbuildウインドウ側で行う

	}
	/**
	 * @param own
	 */
	public Tree_Object(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/** 通常使用するコンストラクタ
	 * @param own
	 */
	public Tree_Object(yukkurisim_main own , int x , int y) {
		super(own);
		location(x,y);// 必ずInitializeの前にlocationする。
		Initialize();
	}

	/**
	 * @param own
	 * @param arg0
	 */
	public Tree_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param own
	 * @param arg0
	 * @param arg1
	 */
	public Tree_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param own
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public Tree_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1,
			double arg2) {
		super(own, arg0, arg1, arg2);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public int Get_Food()
	{
		return 10000;		//10000返す
	}
	
	public void doClickEvent()
	{
		//何もしない
	}
	public int Get_Type() {
		return 定数.TYPE_木object;
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
