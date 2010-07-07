package yukkurisim;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import yukkurisim.yukkurisim_main;

public class Mapchip_base extends Object_base {

	private int my_chiptype;		//チップの属性などに関する
	
	
	public Mapchip_base(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Mapchip_base(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Mapchip_base(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public Mapchip_base(yukkurisim_main own, BufferedImage[] arg0, double arg1, double arg2) {
		super(own, arg0, arg1, arg2);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	/**
	 * マップチップクラスの通常使用コンストラクタ。
	 * @param own
	 * @param arg_img
	 * @param arg_x
	 * @param arg_y
	 * @param arg_chiptype
	 */
	public Mapchip_base(	yukkurisim_main	own,
							BufferedImage[]	arg_img, 
							int 			arg_x, 
							int 			arg_y , 
							int 			arg_chiptype
						) 
	{
		super(own, arg_img);	//画像のみの親コンストラクタをコールする。
		this.SetCellLocation(arg_x, arg_y);
		this.SetChipType(arg_chiptype);
		
		this.initialize();
	}

	public void initialize()
	{
		this.setAnimate(false); 				// アニメーションしない。

	}
	/**
	 * マップチップのセル単位での位置を設定する.
	 * @param loc_x
	 * @param loc_y
	 */
	public void SetCellLocation(int loc_x , int loc_y)
	{
		this.my_x = loc_x;
		this.my_y = loc_y;
	}
	
	/**
	 * マップチップのタイプを設定する.
	 * @param Chiptype
	 */
	public void SetChipType( int Chiptype )
	{
		this.my_chiptype = Chiptype;
	}

	public void update(long elapsedTime)
	{
		// 横はそのままのサイズで、縦は半分(25）だけ
		if( this.my_y%2==1)
		{
			this.setLocation(this.my_x * 定数.マップチップ横幅+(定数.マップチップ横幅/2), this.my_y * (( 定数.マップチップ縦幅 / 2 )-3));			
		}
		else
		{
			this.setLocation(this.my_x * 定数.マップチップ横幅, this.my_y * (( 定数.マップチップ縦幅 / 2 )-3));
		}
			super.update(elapsedTime);
	}

	public int Get_Type() {
		return this.my_chiptype;
	}

	public void doClickEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public void render(Graphics2D g)
	{
		if(this.getImages()!=null)
		{
			super.render(g);
		}
	}
	
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	
}
