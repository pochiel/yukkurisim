package yukkurisim;

import gamestatus.SaveData;

import java.awt.image.BufferedImage;

/**
 * 農地オブジェクト。　これを接地しないと農業を選択することができない。
 * @author ポチエル
 *
 */
public class Farm_Object extends Object_base {

	public void Initialize()
	{
		this.setImages(ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_農地));
		
		this.setAnimate(false); 				// アニメーションしない
		this.setLoopAnim(false);				// アニメーションをループさせない
		
		this.setX(this.Get_Pixel_x(this.my_x,this.my_y)-(this.getWidth()-定数.マップチップ横幅));
		this.setY(this.Get_Pixel_y(this.my_x,this.my_y)-(this.getHeight()-定数.マップチップ縦幅));
		
		//PhLaw.Get_ObjectManager().add(this, this.Get_Type());		// add登録はbuildウインドウ側で行う
	}

	public Farm_Object(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Farm_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Farm_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	/** 通常使用するコンストラクタ
	 * @param own
	 */
	public Farm_Object(yukkurisim_main own , int x , int y) {
		super(own, x, y);
		location(x,y);		// 必ずInitializeの前にlocationする。
		Initialize();
	}
	
	public Farm_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1,
			double arg2) {
		super(own, arg0, arg1, arg2);
		Initialize();
	}

	@Override
	public int Get_Type() {
		// TODO 自動生成されたメソッド・スタブ
		return 定数.TYPE_農地object;
	}

	@Override
	public void doClickEvent() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ

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

	@Override
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}


}
