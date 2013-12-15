package yukkurisim;

import java.awt.image.BufferedImage;

/* その他のオブジェクト。　単に画像を表示したりしたい時に。 */
/* 原則スプライトタイプは未使用となる */
public class Other_Object extends Object_base {

	public Other_Object(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Other_Object(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Other_Object(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Other_Object(yukkurisim_main own, BufferedImage[] arg0, double arg1,
			double arg2) {
		super(own, arg0, arg1, arg2);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int Get_Type() {
		return 定数.TYPE_未使用;
	}

	@Override
	public void doClickEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
