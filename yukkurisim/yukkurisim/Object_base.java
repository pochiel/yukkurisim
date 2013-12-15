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
	
	protected yukkurisim_main owner;					//�I�[�i�[Game�C���X�^���X�ւ̎Q��
	protected  Const_Value �萔 = new Const_Value();
	protected double myId;					// �I�u�W�F�N�gID
	private boolean visible = true;			// ������
	private boolean clickable = true;			// �N���b�N�\
	private boolean TimerDependency = true;	// Timer�ɏ]�����邩
	protected Cursor_Manager	OwnCursor;		// �J�[�\���}�l�[�W��
	protected boolean isScrollable;			// �X�N���[���\
	protected Physical_Law_Facade PhLaw;		// �����@���N���X�ւ̎Q��
	
	protected float alpha;
	protected int nowFadingSwitch;		// 1���t�F�[�h�C�� 2���t�F�[�h�A�E�g
	
	int	my_x;		//�Z�����X�ʒu
	int	my_y;		//�Z�����Y�ʒu
	int old_my_x;	//�Z�����X�ʒu�A��O
	int old_my_y;	//�Z�����Y�ʒu�A��O
	
	
	
	/************* �f�o�b�O�֘A�R�R���� �K���������邱�ƁI ************/
	private int debug;		//�f�o�b�O�p���ʕϐ�
	public void set_debug(int a)
	{
		this.debug = a;
	}
	public int get_debug()
	{
		return this.debug;
	}
	/************* �f�o�b�O�֘A�R�R�}�f �K���������邱�ƁI ************/

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
	protected boolean recovering;				// ���ݗ�������֎~��悩��ޔ�
	
	public int Get_my_y()
	{
		return this.my_y;
	}
	
	public int Get_my_x()
	{
		return this.my_x;
	}
	
	/**
	 * �I�[�i�[�ւ̎Q�Ƃ݂̂������Ɏ��R���X�g���N�^.
	 * @param own
	 */
	public Object_base(yukkurisim_main own) {
		super(null,0,0);
		this.owner = own;
    	initResources();
	}
	/**
	 * <p>
	 * �I�[�i�ւ̎Q�ƁE�摜�������Ɏ��R���X�g���N�^�B<br>
	 * �ʏ�͂��̃R���X�g���N�^��p����B
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
	 * �I�[�i�ւ̎Q�ƂƃX�v���C�g�̏������W�������Ɏ��R���X�g���N�^�B<br>
	 * ���̃R���X�g���N�^�͂ǂ�����Ďg�����炢���񂾂낤�H
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
	 * �I�[�i�ւ̎Q�ƁE�摜�E�������W�������Ɏ��R���X�g���N�^.
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
		{	// �A�N�e�B�u�ɂȂ��Ă�̂ɃA���t�@�l��1�ȉ��̏ꍇ�t�F�[�h�C����
			this.alpha += 0.01f;
			if(this.alpha>1.0)
			{
				this.alpha = 1.0f;
			}
		}
		else if(this.getNowFadingSwitch()==2)
		{	//�t�F�[�h��
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
				// �X�N���[���\�Ȃ��
				// �����_�����O������ۂɃX�N���[�����΍��W�v�Z�������Ⴄ
				// ��΍��W�Ƒ��΍��W���d�ŊǗ�����K�v���Ȃ��Ȃ邽��
				double backupx = this.getX();								// ���̍��W��ޔ�
				double backupy = this.getY();
				
				this.setX( this.getX() + this.OwnCursor.Get_RelX() );		// ���΍��W���Z�b�g
				this.setY( this.getY() + this.OwnCursor.Get_RelY() );
		
				super.render(g);											// �����_�����O
		
				this.setX(backupx);											// ��΍��W�𕜌�
				this.setY(backupy);
			}
			else
			{
				super.render(g);
			}
		}
		else
		{
			// Composite��ޔ�
	        Composite comp = g.getComposite();
	        // �A���t�@�l
	        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	        
			if(this.isScrollable)
			{
				// �X�N���[���\�Ȃ��
				// �����_�����O������ۂɃX�N���[�����΍��W�v�Z�������Ⴄ
				// ��΍��W�Ƒ��΍��W���d�ŊǗ�����K�v���Ȃ��Ȃ邽��
				double backupx = this.getX();								// ���̍��W��ޔ�
				double backupy = this.getY();
				
				this.setX( this.getX() + this.OwnCursor.Get_RelX() );		// ���΍��W���Z�b�g
				this.setY( this.getY() + this.OwnCursor.Get_RelY() );
		
				super.render(g);											// �����_�����O
		
				this.setX(backupx);											// ��΍��W�𕜌�
				this.setY(backupy);
			}
			else
			{
				super.render(g);
			}
			
			// �ޔ�����composite�𕜋A
			g.setComposite(comp);
		}	
	}
	
	/**
	 * �}�E�X����������H
	 * @return
	 */
	public boolean isMouseRide()
	{
		if( this.isScrollable )
		{
			if(	(owner.getMouseX() > this.getX() + this.OwnCursor.Get_RelX()) && ( owner.getMouseX() < this.getX() + this.OwnCursor.Get_RelX()+ this.getWidth())&&	//x�������摜�͈͓̔� 
				(owner.getMouseY() > this.getY() + this.OwnCursor.Get_RelY()) && ( owner.getMouseY() < this.getY() + this.OwnCursor.Get_RelY()+ this.getHeight()) )	//y�������摜�͈͓̔�
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
			if(	(owner.getMouseX() > this.getX()) && ( owner.getMouseX() < this.getX() + this.getWidth())&&	//x�������摜�͈͓̔� 
				(owner.getMouseY() > this.getY()) && ( owner.getMouseY() < this.getY() + this.getHeight()) )	//y�������摜�͈͓̔�
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
	 * �N���b�N���ꂽ���H
	 * @return
	 */
	public boolean isClicked()
	{
		
		if( this.isScrollable )
		{
			if( owner.isMouseUp() )
			{
				if(	(owner.getMouseX() > this.getX() + this.OwnCursor.Get_RelX()) && ( owner.getMouseX() < this.getX() + this.OwnCursor.Get_RelX()+ this.getWidth())&&	//x�������摜�͈͓̔� 
					(owner.getMouseY() > this.getY() + this.OwnCursor.Get_RelY()) && ( owner.getMouseY() < this.getY() + this.OwnCursor.Get_RelY()+ this.getHeight()) )	//y�������摜�͈͓̔�
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
			// owner.click() �� owner.bsInput.isMousePressed() ���R�[�����Ă��邪�A�����MouseDown�̏u�ԂɃV�O�i�����E������
			// ���X���b�h�ŃA�b�v�f�[�g���ď����������Ă���\��������B
			// �������Aowner.bsInput.isMouseDown() �̓_�E���̊Ԓ�������true��Ԃ�������̂ŁA��������܂��Ȃ��B
			// ����āA�I�[�i���Ń}�E�X�̋������Ď����AisMouseDown�� true��false �ƕω������u�Ԃ�true�ƂȂ�d�g�݂ɂ���΂悢�B
			// owner.click() �ƈ���ē���X���b�h��ŃV�O�i���̃A�b�v�f�[�g���ۏႳ���̂ŁA�I�[�i��update���q��update��isclick�Ƃ����d�g�݂Ȃ��
			// �������������肦�Ȃ��B
			if( owner.isMouseUp() )
			{
				if(	(owner.getMouseX() > this.getX()) && ( owner.getMouseX() < this.getX() + this.getWidth())&&	//x�������摜�͈͓̔� 
					(owner.getMouseY() > this.getY()) && ( owner.getMouseY() < this.getY() + this.getHeight()) )	//y�������摜�͈͓̔�
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
	 * �Z�����W�̎����W��X���v�Z���ĕԂ��܂��B
	 */
	public int Get_Pixel_x(int my_x,int my_y){
		if( my_y%2 == 1)
		{
			return my_x * �萔.�}�b�v�`�b�v����+(�萔.�}�b�v�`�b�v����/2) ;
		}
		else
		{
			return my_x * �萔.�}�b�v�`�b�v����;
		}		
	}

	/**
	 * Get_Pixel_y
	 * �Z�����W�̎����W��Y���v�Z���ĕԂ��܂��B
	 */
	public int Get_Pixel_y(int my_x,int my_y){
			return	my_y * (( �萔.�}�b�v�`�b�v�c�� / 2 )-3);		// ���� 25y
	}

	/**
	 * �Z���ʒu���̕ύX�B
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
	 * ���݂̃I�u�W�F�N�g�̎����W��񂩂�Z���P�ʍ��W( my_x , my_y )���X�V����B
	 *
	 */
	public void Set_Cell_From_Real_Location()
	{
		int tmp_my_x = 0;
		int tmp_my_y = 0;
		int rx , ry;
		rx = (int) this.getX();
		ry = (int) this.getY();
		
		tmp_my_y = 2 * ry/(�萔.�}�b�v�`�b�v�c��-6);		//y�Z�����W���Z�o

		if ( tmp_my_y%2 == 1)
		{
			tmp_my_x = (rx/�萔.�}�b�v�`�b�v����)-(1/2);
		}
		else
		{
			tmp_my_x = rx/�萔.�}�b�v�`�b�v����;
		}
		
		if ( tmp_my_x >= �萔.��ʉ��Z���� )
		{
			tmp_my_x = �萔.��ʉ��Z���� - 1;
		}
		if ( tmp_my_y >= �萔.�}�b�v�`�b�v�c�� )
		{
			tmp_my_y = �萔.��ʏc�Z���� - 1;
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
		//�_�~�[
	}
	
	/***
	 * �����������擾����
	 * @param targetx �Ώۂ�x���W
	 * @param targety �Ώۂ�y���W
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
	 * �Y���I�u�W�F�N�g�̃N���b�N�C�x���g
	 *
	 */
	public abstract void doClickEvent();
	/*{
		//System.out.println("���s");
		//�_�~�[
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
		this.myId = this.owner.IdMan.GetNewId();				// ObjectId�t�^
    	this.OwnCursor = Cursor_Manager.Get_Instance(owner);	// �J�[�\���Ǘ��N���X
    	this.isScrollable = true;								// �X�N���[���\�t���O�̓f�t�H���gtrue
    	this.PhLaw = Physical_Law_Facade.Get_Instance(owner);	// �����@���N���X
    	this.old_my_x = 0;
    	this.old_my_y = 0;
    	this.recovering = false;
	}

	public double Get_Id()
	{
		return this.myId;
	}
	public abstract boolean isCharactor();		// �I�u�W�F�N�g���L�����N�^�ł��邩��Ԃ�
	public abstract void doOnMouseEvent();		//	�I�u�W�F�N�g��Ƀ}�E�X��������Ƃ��̃C�x���g���L�q����

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
		
		//this.my_child.setActive(s);		//��active�Ȏq�͎����ŃA�b�v�f�[�g����Ȃ�		
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
