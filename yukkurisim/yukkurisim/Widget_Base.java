package yukkurisim;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.FontManager;
/*******************************************
 * 
 * @author �|�`�G��
 *
 */
public class Widget_Base extends Object_base {

	private Widget_Base my_parent=null;
	protected FontManager FontMan=null;
	protected int Button_id	=	0;					// �{�^������
	private ADV_SpriteGroup_base my_child = new ADV_SpriteGroup_base(owner, "CHILD_WIDGET");
	
	private int Relative_x;
	private int Relative_y;
	private String mymessage;
	private Color fontColor;	// �t�H���g�J���[
	
	public Widget_Base(yukkurisim_main own) {
		super(own);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Base(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0, double x, double y) {
		super(own, arg0, x, y);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	/**********************************************************
	 * �ʏ�g�p����R���X�g���N�^.
	 * @param own
	 * @param arg0
	 * @param x
	 * @param y
	 */
	public Widget_Base(yukkurisim_main own, BufferedImage[] arg0, double x, double y , Widget_Base parent) {
		super(own, arg0, x, y);
		my_parent = parent;			//�ewidget�Q��
		this.setActive(false);		
		Set_Relative_Position(0,0);	//���΍��W������
	}
	
	public Widget_Base(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent)
	{
		//�����I�u�W�F�N�g�p�R���X�g���N�^
		super(own,x,y);
		my_parent = parent;			//�ewidget�Q��
		mymessage = message;
		this.setActive(false);		
		Set_Relative_Position(0,0);	//���΍��W������
		this.FontMan = new FontManager(owner,24);
		
	}
	
	@Override
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
		my_parent = parent;			//�ewidget�Q��
		this.setActive(false);		
		Set_Relative_Position(0,0);	//���΍��W������
		this.Button_id = mytype;
	}

	public Widget_Base(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent,
			int mytype)
	{
		//�����I�u�W�F�N�g�p�R���X�g���N�^
		super(own,x,y);
		my_parent = parent;			//�ewidget�Q��
		mymessage = message;
		this.setActive(false);		
		Set_Relative_Position(0,0);	//���΍��W������
		this.FontMan = new FontManager(owner,24);
		this.Button_id = mytype;
	}
	
	/****
	 * ���΍��W�����.
	 * @param x
	 * @param y
	 */
	public void Set_Relative_Position(int x , int y)
	{
		this.Relative_x = x;
		this.Relative_y = y;
		
		Convert_Abs_Position();
		//���΍��W���͂��Đ�΍��W�ɕϊ��B
	}
	
	/****
	 * 	���΍��W���΍��W�ɕϊ����i�[
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
	 * �I�u�W�F�N�g�ɕ�widget���}�E���g����B
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
	
	@Override
	public void Set_Scrollable(boolean s)
	{
		Sprite[] sprites = this.my_child.getSprites();
		
		for(int i=0; i<this.Get_Child_Size() ;i++)
		{
			if( sprites[i]!=null )		// null�łȂ����ׂẴX�v���C�g��
			{							// isScrollabe��ς���B�iactive�ł���K�v�͂Ȃ�
				((Object_base)sprites[i]).isScrollable = s;
			}
		}
		this.isScrollable = s;			// �������ς���
	}
	@Override
	public void setActive(boolean s)
	{
		super.setActive(s);
		
		Sprite[] sprites = this.my_child.getSprites();
		
		if( this.Get_Child_Size()>0 )
		{	//�������e�ł���A�P�l�ȏ�̎q������
			for(int i=0;i<this.my_child.getSize();i++)
			{
				sprites[i].setActive(s);
			}
		}	
	}
	@Override
	public void setActiveToFade(boolean s)
	{
		if(s)
		{	
			this.nowFadingSwitch = 1;
			this.alpha = 0.0f;
			this.setActive(s);
			Sprite[] sprites = this.my_child.getSprites();
			
			if( this.Get_Child_Size()>0 )
			{	//�������e�ł���A�P�l�ȏ�̎q������
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
			{	//�������e�ł���A�P�l�ȏ�̎q������
				for(int i=0;i<this.my_child.getSize();i++)
				{
					((Widget_Base)sprites[i]).setNowFadingSwitch(2);
				}
			}
		}
		
		//this.my_child.setActive(s);		//��active�Ȏq�͎����ŃA�b�v�f�[�g����Ȃ�		
	}

	@Override
	public void update(long elapsedTime)
	{
		if( this.isActive() )
		{
			if( this.getNowFadingSwitch()==1 )
			{	// �A�N�e�B�u�ɂȂ��Ă�̂ɃA���t�@�l��1�ȉ��̏ꍇ�t�F�[�h�C����
				this.alpha += 0.05f;
				if(this.alpha>1.0)
				{
					this.alpha = 1.0f;
				}
			}
			else if(this.getNowFadingSwitch()==2)
			{	//�t�F�[�h��
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
	
	@Override
	public void render(Graphics2D g)
	{

		// Composite��ޔ�
        //Composite comp = g.getComposite();
        // �A���t�@�l
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
					// Composite��ޔ�
			        Composite comp = g.getComposite();
			        // �A���t�@�l
			        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	
					Color tmp = FontMan.getFontColor();
					FontMan.setFontColor(fontColor);
					FontMan.drawString(g, mymessage, (int)this.getX(), (int)this.getY());
					FontMan.setFontColor(tmp);
					
					g.setComposite(comp);
				}
			}
		}
		
		// �ޔ�����composite�𕜋A
		//g.setComposite(comp);

	}

	@Override
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

	@Override
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
	
	@Override
	public int Get_Type() {
		return this.Button_id;
	}

	@Override
	public void doClickEvent()
	{
		System.out.println("widgetbase��doclickevent");

		if( this.getNowFadingSwitch()>0 )
		{	// �t�F�[�h�������͈�؂̑�����󂯕t���Ȃ�
			return;
		}
	}

	public Widget_Base Get_Root_Parent()
	{
		Widget_Base tmp = this;
		while(tmp.Get_Parent()!=null)
		{
			tmp = tmp.Get_Parent();	//null�ɓ��B����܂Őe�����ǂ�
		}
		return tmp;
	}
	
	public Widget_Base Get_Parent()
	{
		return this.my_parent;
	}
	
	public void Comeback_From_Cursor(int x,int y,int s)
	{
		//�_�~�[
	}

	public ADV_SpriteGroup_base getChild() {
		return my_child;
	}
	
	@Override
	public boolean isCharactor()
	{
		return false;	// ����̓L�����N�^�[�ł͂Ȃ�
	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public int getNowFadingSwitch() {
		return nowFadingSwitch;
	}

	@Override
	public void setNowFadingSwitch(int nowFadingSwitch) {
		this.nowFadingSwitch = nowFadingSwitch;
	}

	@Override
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	@Override
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
