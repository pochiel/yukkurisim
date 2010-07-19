package yukkurisim;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import subwindow.Widget_Build;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.FontManager;
/*******************************************
 * 
 * @author ポチエル
 *
 */
public class Widget_Base extends Object_base {

	private Widget_Base my_parent=null;
	protected FontManager FontMan=null;
	protected int Button_id	=	0;					// ボタン識別
	private ADV_SpriteGroup_base my_child = new ADV_SpriteGroup_base(owner, "CHILD_WIDGET");
	
	private int Relative_x;
	private int Relative_y;
	private String mymessage;
	private Color fontColor;	// フォントカラー
	
	public Widget_Base(yukkurisim_main own) {
		super(own);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Base(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0, double x, double y) {
		super(own, arg0, x, y);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	/**********************************************************
	 * 通常使用するコンストラクタ.
	 * @param own
	 * @param arg0
	 * @param x
	 * @param y
	 */
	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0, double x, double y , Widget_Base parent) {
		super(own, arg0, x, y);
		my_parent = parent;			//親widget参照
		this.setActive(false);		
		Set_Relative_Position(0,0);	//相対座標初期化
	}
	
	public Widget_Base(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent)
	{
		//文字オブジェクト用コンストラクタ
		super(own,x,y);
		my_parent = parent;			//親widget参照
		mymessage = message;
		this.setActive(false);		
		Set_Relative_Position(0,0);	//相対座標初期化
		this.FontMan = new FontManager(owner,24);
		
	}
	
	public void initResources()
	{
		super.initResources();
		this.alpha = 1.0f;
		this.nowFadingSwitch = 0;
		this.fontColor = Color.black;
	}
	
	public void SetFontsize(int size)
	{
		this.FontMan.SetFontSize(size);
	}
	
	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0, int x, int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y);
		my_parent = parent;			//親widget参照
		this.setActive(false);		
		Set_Relative_Position(0,0);	//相対座標初期化
		this.Button_id = mytype;
	}

	public Widget_Base(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent,
			int mytype)
	{
		//文字オブジェクト用コンストラクタ
		super(own,x,y);
		my_parent = parent;			//親widget参照
		mymessage = message;
		this.setActive(false);		
		Set_Relative_Position(0,0);	//相対座標初期化
		this.FontMan = new FontManager(owner,24);
		this.Button_id = mytype;
	}
	
	/****
	 * 相対座標を入力.
	 * @param x
	 * @param y
	 */
	public void Set_Relative_Position(int x , int y)
	{
		this.Relative_x = x;
		this.Relative_y = y;
		
		Convert_Abs_Position();
		//相対座標入力して絶対座標に変換。
	}
	
	/****
	 * 	相対座標を絶対座標に変換し格納
	 */
	private void Convert_Abs_Position()
	{
		if( my_parent != null)
		{
			if( (my_parent.Relative_x==0) && (my_parent.Relative_y==0) )
			{
				this.setX( my_parent.getX() + this.Relative_x);
				this.setY( my_parent.getY() + this.Relative_y);
			}
			else
			{
				my_parent.Convert_Abs_Position();
				this.setX( my_parent.getX() + this.Relative_x);
				this.setY( my_parent.getY() + this.Relative_y);
			}
		}
	}
	/**********************************************************
	 * オブジェクトに別widgetをマウントする。
	 * @param child
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean Mount_widget( Widget_Base child ,int x , int y )
	{
		this.my_child.add(child);
		this.my_child.setBackground(owner.background);
		child.Set_Relative_Position(x, y);
		
		return true;
	}
	
	public boolean UnMount_widget( int n )
	{
		//dumy
		this.my_child.clear();
		return true;
	}
	
	public int Get_Child_Size()
	{
		return this.my_child.getSize();
	}
	
	public void Set_Scrollable(boolean s)
	{
		Sprite[] sprites = this.my_child.getSprites();
		
		for(int i=0; i<this.Get_Child_Size() ;i++)
		{
			if( sprites[i]!=null )		// nullでないすべてのスプライトの
			{							// isScrollabeを変える。（activeである必要はない
				((Object_base)sprites[i]).isScrollable = s;
			}
		}
		this.isScrollable = s;			// 自分も変える
	}
	public void setActive(boolean s)
	{
		super.setActive(s);
		
		Sprite[] sprites = this.my_child.getSprites();
		
		if( this.Get_Child_Size()>0 )
		{	//自分が親であり、１人以上の子がいる
			for(int i=0;i<this.my_child.getSize();i++)
			{
				sprites[i].setActive(s);
			}
		}	
	}
	public void setActiveToFade(boolean s)
	{
		if(s)
		{	
			this.nowFadingSwitch = 1;
			this.alpha = 0.0f;
			this.setActive(s);
			Sprite[] sprites = this.my_child.getSprites();
			
			if( this.Get_Child_Size()>0 )
			{	//自分が親であり、１人以上の子がいる
				for(int i=0;i<this.my_child.getSize();i++)
				{
					((Widget_Base)sprites[i]).setNowFadingSwitch(1);
					((Widget_Base)sprites[i]).setAlpha(0.0f);
					this.setActive(s);
				}
			}
			
		}
		else
		{
			this.nowFadingSwitch = 2;

			Sprite[] sprites = this.my_child.getSprites();
			
			if( this.Get_Child_Size()>0 )
			{	//自分が親であり、１人以上の子がいる
				for(int i=0;i<this.my_child.getSize();i++)
				{
					((Widget_Base)sprites[i]).setNowFadingSwitch(2);
				}
			}
		}
		
		//this.my_child.setActive(s);		//非activeな子は自動でアップデートされない		
	}

	public void update(long elapsedTime)
	{
		if( this.isActive() )
		{
			if( this.getNowFadingSwitch()==1 )
			{	// アクティブになってるのにアルファ値が1以下の場合フェードイン中
				this.alpha += 0.05f;
				if(this.alpha>1.0)
				{
					this.alpha = 1.0f;
				}
			}
			else if(this.getNowFadingSwitch()==2)
			{	//フェード中
				this.alpha -= 0.05f;
				if(this.alpha<0.0)
				{
					this.alpha = 0.0f;
					this.setActive(false);
				}
			}
			super.update(elapsedTime);
			this.my_child.update(elapsedTime);
		}
	}
	
	public void render(Graphics2D g)
	{

		// Compositeを退避
        //Composite comp = g.getComposite();
        // アルファ値
        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		if( this.isActive() )
		{
			if(FontMan==null)
			{
				super.render(g);
				this.my_child.render(g);
			}
			else{
				if(owner.GameIsFading())
				{
					Color tmp = FontMan.getFontColor();
					FontMan.setFontColor(fontColor);
					FontMan.drawString(g, mymessage, (int)this.getX(), (int)this.getY());
					FontMan.setFontColor(tmp);
				}
				else
				{
					// Compositeを退避
			        Composite comp = g.getComposite();
			        // アルファ値
			        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	
					Color tmp = FontMan.getFontColor();
					FontMan.setFontColor(fontColor);
					FontMan.drawString(g, mymessage, (int)this.getX(), (int)this.getY());
					FontMan.setFontColor(tmp);
					
					g.setComposite(comp);
				}
			}
		}
		
		// 退避したcompositeを復帰
		//g.setComposite(comp);

	}

	public int getWidth()
	{
		if(mymessage==null)
		{
			return super.getWidth();
		}
		else
		{
			return FontMan.getWidth(this.mymessage);
		}
	}

	public int getHeight()
	{
		if(mymessage==null)
		{
			return super.getHeight();
		}
		else
		{
			return FontMan.getHeight(this.mymessage);
		}
	}

	public void Set_Mymessage(String s)
	{
		this.mymessage = s;
	}
	
	public int Get_Type() {
		return this.Button_id;
	}

	public void doClickEvent()
	{
		System.out.println("widgetbaseのdoclickevent");

		if( this.getAlpha() < 1.0f )
		{	// フェード処理中は一切の操作を受け付けない
			return;
		}
	}

	public Widget_Base Get_Root_Parent()
	{
		Widget_Base tmp = this;
		while(tmp.Get_Parent()!=null)
		{
			tmp = tmp.Get_Parent();	//nullに到達するまで親をたどる
		}
		return tmp;
	}
	
	public Widget_Base Get_Parent()
	{
		return this.my_parent;
	}
	
	public void Comeback_From_Cursor(int x,int y,int s)
	{
		//ダミー
	}

	public ADV_SpriteGroup_base getChild() {
		return my_child;
	}
	
	public boolean isCharactor()
	{
		return false;	// これはキャラクターではない
	}

	@Override
	public void doOnMouseEvent() {
		// TODO 自動生成されたメソッド・スタブ
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

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color c) {
		fontColor = c;
	}


}
